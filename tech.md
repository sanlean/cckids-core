# Arquitetura Kotlin Multiplataforma – Core Library

Este documento define as regras arquiteturais obrigatórias para o módulo **shared** do projeto Kotlin Multiplataforma (KMP) de biblioteca core.

O objetivo é garantir:

* Consistência arquitetural
* Escalabilidade
* Independência de plataforma
* Testabilidade
* Preparação para Android, iOS, JS, Desktop e Native
* Compatibilidade futura com exportação para Web/React

Este módulo **não contém UI**.
Ele representa exclusivamente lógica de negócio, acesso a dados e comunicação externa.

---

# 1. Princípios Fundamentais

1. O módulo core **não deve depender de Android, iOS ou qualquer framework de UI**.
2. O core não conhece lifecycle de plataforma.
3. O core não expõe exceções técnicas.
4. O core não expõe DTOs externos.
5. Toda regra de negócio deve estar encapsulada em UseCases.
6. Toda operação suspensa deve retornar `Result<T>`.
7. Todo Repository deve usar `executeRequest`.

---

# 2. Estrutura de Camadas

| Camada      | Responsabilidade                             |
| ----------- | -------------------------------------------- |
| ViewModel   | Gerenciar estado e expor StateFlow           |
| UseCase     | Encapsular regra de negócio                  |
| Repository  | Acesso a API, banco ou persistência          |
| Adapter     | Implementação específica por target          |
| Environment | Fornecer dependências específicas por target |

Fluxo:

View → ViewModel → UseCase → Repository → Data Source

---

# 3. Modelos: DTO vs Domain

## Regra obrigatória

* DTO nunca sai do Repository.
* O domínio nunca depende do formato da API.
* Conversão acontece dentro do Repository.

---

## Exemplo de DTO

```kotlin
@Serializable
data class LoginResponseDto(
    val id: String,
    val name: String,
    val token: String
)
```

---

## Exemplo de Domain Model

```kotlin
data class User(
    val id: String,
    val name: String,
    val authToken: String
)
```

---

## Mapper obrigatório

```kotlin
fun LoginResponseDto.toDomain(): User =
    User(
        id = id,
        name = name,
        authToken = token
    )
```

---

# 4. Tratamento de Erros

## 4.1 DomainError

Todos os erros devem ser convertidos para `DomainError`.

```kotlin
sealed class DomainError {
    object NetworkUnavailable : DomainError()
    object Unauthorized : DomainError()
    object Forbidden : DomainError()
    object NotFound : DomainError()
    object Timeout : DomainError()
    object ServerError : DomainError()
    data class Validation(val message: String) : DomainError()
    data class Unknown(val cause: Throwable? = null) : DomainError()
}
```

---

## 4.2 Result Wrapper

```kotlin
sealed class Result<out T> {
    data class Success<T>(val data: T) : Result<T>()
    data class Failure(val error: DomainError) : Result<Nothing>()
}
```

---

## 4.3 Regra Obrigatória

* Repository nunca lança exceção.
* Repository sempre retorna `Result<T>`.
* ViewModel nunca recebe Throwable.
* UseCase nunca faz try/catch técnico.

---

# 5. RequestExecutor (Padrão Obrigatório)

Toda operação suspensa deve usar `executeRequest`.

```kotlin
suspend fun <T> executeRequest(
    dispatcher: CoroutineDispatcher,
    block: suspend () -> T
): Result<T> = withContext(dispatcher) {
    try {
        Result.Success(block())
    } catch (cancellation: CancellationException) {
        throw cancellation
    } catch (throwable: Throwable) {
        Result.Failure(throwable.toDomainError())
    }
}
```

Proibido usar try/catch manual em Repository.

---

# 6. Repository Pattern

## Regra

* Interface obrigatória
* Implementação concreta
* Dispatcher injetado
* Nunca trocar dispatcher manualmente
* Sempre usar executeRequest

---

## Exemplo

```kotlin
interface UserRepository {
    suspend fun login(request: LoginRequest): Result<User>
}
```

```kotlin
class UserRepositoryImpl(
    private val client: HttpClient,
    private val dispatcher: CoroutineDispatcher
) : UserRepository {

    override suspend fun login(request: LoginRequest): Result<User> =
        executeRequest(dispatcher) {
            val dto = client.post("login") {
                setBody(request)
            }
            dto.toDomain()
        }
}
```

---

# 7. UseCases

## Regras

* ViewModel nunca acessa Repository direto.
* UseCase encapsula regra de negócio.
* Pode chamar um ou mais repositories.
* Não troca dispatcher.

---

## Exemplo

```kotlin
class LoginUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(request: LoginRequest): Result<User> {
        return repository.login(request)
    }
}
```

---

# 8. ViewModels

## Regras

* Não herdar de androidx.lifecycle.ViewModel
* Não usar viewModelScope
* Criar escopo próprio
* Expor apenas StateFlow
* Encerrar escopo manualmente

---

## Exemplo

```kotlin
class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val dispatcher: CoroutineDispatcher
) {

    private val job = SupervisorJob()
    private val scope = CoroutineScope(job + dispatcher)

    private val _state = MutableStateFlow<LoginState>(LoginState.Idle)
    val state: StateFlow<LoginState> = _state

    fun login(request: LoginRequest) {
        scope.launch {
            _state.value = LoginState.Loading
            val result = loginUseCase(request)
            _state.value = when (result) {
                is Result.Success -> LoginState.Success(result.data)
                is Result.Failure -> LoginState.Error(result.error)
            }
        }
    }

    fun clear() {
        job.cancel()
    }
}
```

---

# 9. Threading Policy

## Regras

* Dispatcher sempre injetado.
* Repository executa no dispatcher recebido.
* UseCase não altera dispatcher.
* ViewModel recebe dispatcher apropriado.
* Nunca usar Dispatchers.IO diretamente.

---

## ThreadingPolicy

```kotlin
interface ThreadingPolicy {
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
}
```

Cada target deve fornecer sua implementação.

---

# 10. Persistência

## 10.1 Preferences

* Usar Adapter expect/actual.
* Repository recebe Adapter via construtor.

---

## 10.2 Banco Relacional

* Usar SQLDelight.
* Repository recebe Database gerado.
* Nunca misturar API e DB no mesmo Repository.

---

# 11. Dependency Injection Manual

## 11.1 CoreEnvironment

O módulo core não cria dependências específicas de plataforma.

```kotlin
interface CoreEnvironment {
    val httpClient: HttpClient
    val database: AppDatabase
    val preferencesAdapter: PreferencesAdapter
    val threadingPolicy: ThreadingPolicy
}
```

Cada target implementa seu próprio Environment.

---

## 11.2 CoreModule

Responsável por montar o grafo de dependência.

```kotlin
class CoreModule(
    private val environment: CoreEnvironment
) {

    private val userRepository: UserRepository by lazy {
        UserRepositoryImpl(
            client = environment.httpClient,
            dispatcher = environment.threadingPolicy.io
        )
    }

    private val loginUseCase: LoginUseCase by lazy {
        LoginUseCase(userRepository)
    }

    fun provideLoginViewModel(): LoginViewModel {
        return LoginViewModel(
            loginUseCase = loginUseCase,
            dispatcher = environment.threadingPolicy.default
        )
    }
}
```

---

# 12. Testes

* Testes de domínio devem estar em commonTest.
* Repository pode ser testado com fake HttpClient.
* UseCase deve ser testado isoladamente.
* ViewModel deve ser testado usando TestDispatcher.

---

# 13. Proibições

* Proibido usar framework de DI.
* Proibido usar Dispatchers.IO diretamente.
* Proibido expor DTO fora do Repository.
* Proibido lançar exceção para camada superior.
* Proibido acessar Repository direto do ViewModel.
* Proibido usar código de plataforma no commonMain.

---

# 14. Objetivo Estratégico

Este core deve ser:

* Exportável para JS
* Consumível por Android e iOS
* Testável isoladamente
* Independente de UI
* Preparado para evolução futura (Web, Watch, Desktop)

---

Este documento deve ser seguido estritamente por desenvolvedores humanos e agentes de IA.