# CCKids Core - Contratos de API

Este documento descreve os contratos de API propostos para o sistema CCKids. Todas as requisições devem seguir os padrões RESTful, utilizando JSON como formato de troca de dados.

## Sumário
- [Autenticação](#autenticação)
- [Aulas](#aulas)
- [Alunos](#alunos)
- [Professores](#professores)
- [Check-ins](#check-ins)
- [Materiais](#materiais)

---

## Autenticação

### Login
Realiza a autenticação do professor no sistema.

- **URL:** `/auth/login`
- **Método:** `POST`
- **Body:**
```json
{
  "email": "professor@igreja.com",
  "password": "senha_segura"
}
```
| Campo | Tipo | Obrigatório | Descrição |
| :--- | :--- | :--- | :--- |
| `email` | String | Sim | E-mail do professor |
| `password` | String | Sim | Senha de acesso |

- **Response (200 OK):**
```json
{
  "token": "jwt_token_aqui",
  "professor": {
    "id": "uuid-1",
    "nome": "João Silva",
    "email": "professor@igreja.com",
    "status": "APPROVED",
    "role": "PROFESSOR"
  }
}
```
| Campo | Tipo | Descrição |
| :--- | :--- | :--- |
| `token` | String | Token JWT para autenticação nas demais rotas |
| `professor` | Object | Dados do professor logado (ver [Professor](#professor-dto)) |

---

## Aulas

### Listar Aulas (Resumo)
Retorna uma lista simplificada de aulas para visualização em grade ou lista.

- **URL:** `/aulas`
- **Método:** `GET`
- **Headers:** `Authorization: Bearer <token>`
- **Query Parameters:**
  - `from` (opcional): Data inicial no formato ISO (ex: `2024-03-01T00:00:00`)
  - `to` (opcional): Data final no formato ISO (ex: `2024-03-31T23:59:59`)

- **Response (200 OK):**
```json
[
  {
    "id": "aula-123",
    "data_hora": "2024-03-10T09:00:00",
    "culto": "DOMINGO_MANHA",
    "tipo": "NORMAL",
    "status": "PLANNED",
    "qtd_turmas": 2,
    "qtd_professores": 3,
    "qtd_materiais": 1,
    "qtd_alunos": 15
  }
]
```
| Campo | Tipo | Descrição |
| :--- | :--- | :--- |
| `id` | String | ID único da aula |
| `data_hora` | String | Data e hora em formato ISO |
| `culto` | Enum | `DOMINGO_MANHA`, `DOMINGO_NOITE`, `QUINTA` |
| `tipo` | Enum | `NORMAL`, `EVENTO` |
| `status` | Enum | `PLANNED`, `WARNING`, `DONE` |
| `qtd_turmas` | Int | Quantidade de turmas vinculadas |
| `qtd_professores` | Int | Quantidade de professores vinculados |
| `qtd_materiais` | Int | Quantidade de materiais disponíveis |
| `qtd_alunos` | Int | Quantidade de alunos vinculados |

---

### Detalhes da Aula
Retorna todos os detalhes de uma aula específica, incluindo as listas completas de turmas, professores, materiais e alunos.

- **URL:** `/aulas/{id}`
- **Método:** `GET`
- **Headers:** `Authorization: Bearer <token>`

- **Response (200 OK):**
```json
{
  "id": "aula-123",
  "data_hora": "2024-03-10T09:00:00",
  "culto": "DOMINGO_MANHA",
  "tipo": "NORMAL",
  "status": "PLANNED",
  "turmas": [
    { "id": "t1", "nome": "Berçário", "faixa_etaria_min": 0, "faixa_etaria_max": 2, "ativa": true }
  ],
  "professores": [
    {
      "professor": { "id": "p1", "nome": "Maria", "email": "m@m.com", "status": "APPROVED", "role": "PROFESSOR" },
      "papel": "PROFESSOR"
    }
  ],
  "materiais": [
    { "id": "m1", "aula_id": "aula-123", "professor_id": "p1", "tipo": "PDF", "data_hora": "2024-03-09T10:00:00", "url": "http://..." }
  ],
  "alunos": [
    { "id": "a1", "nome": "Zezinho", "genero": "MASCULINO", "ativo": true }
  ]
}
```

---

## Alunos

### Pesquisar Alunos
Busca alunos com filtros opcionais.

- **URL:** `/alunos`
- **Método:** `GET`
- **Headers:** `Authorization: Bearer <token>`
- **Query Parameters:**
  - `nome` (opcional): Filtro por nome
  - `genero` (opcional): `MASCULINO`, `FEMININO`, `OUTRO`
  - `ativo` (opcional): `true` ou `false`
  - `turmaId` (opcional): ID da turma vinculada

- **Response (200 OK):**
```json
[
  {
    "id": "aluno-1",
    "nome": "Lucas Santos",
    "data_nascimento": "2018-05-20",
    "idade_manual": null,
    "genero": "MASCULINO",
    "foto_url": "http://...",
    "info_responsavel": "Mãe: Ana (99999-9999)",
    "ativo": true
  }
]
```

### Criar Aluno
Cadastra um novo aluno.

- **URL:** `/alunos`
- **Método:** `POST`
- **Headers:** `Authorization: Bearer <token>`
- **Body:**
```json
{
  "nome": "Lucas Santos",
  "genero": "MASCULINO",
  "data_nascimento": "2018-05-20",
  "idade_manual": null,
  "foto_url": null,
  "info_responsavel": "Pai: Pedro"
}
```
| Campo | Tipo | Obrigatório | Descrição |
| :--- | :--- | :--- | :--- |
| `nome` | String | Sim | Nome completo do aluno |
| `genero` | Enum | Sim | `MASCULINO`, `FEMININO`, `OUTRO` |
| `data_nascimento` | String | Não | Data no formato YYYY-MM-DD |
| `idade_manual` | Int | Não | Idade caso não tenha data de nascimento |
| `foto_url` | String | Não | URL ou Base64 da foto |
| `info_responsavel` | String | Não | Informações de contato dos responsáveis |

---

## Check-ins

### Realizar Check-in
Registra a presença de um aluno em uma aula.

- **URL:** `/checkins`
- **Método:** `POST`
- **Headers:** `Authorization: Bearer <token>`
- **Body:**
```json
{
  "aula_id": "aula-123",
  "aluno_id": "aluno-1",
  "professor_id": "prof-456",
  "data_hora": "2024-03-10T09:15:00"
}
```
| Campo | Tipo | Obrigatório | Descrição |
| :--- | :--- | :--- | :--- |
| `aula_id` | String | Sim | ID da aula |
| `aluno_id` | String | Sim | ID do aluno |
| `professor_id` | String | Sim | ID do professor que realizou o check-in |
| `data_hora` | String | Não | Data/hora do check-in (servidor usa o atual se nulo) |

---

## Materiais

### Upload de Material
Vincula um novo material a uma aula.

- **URL:** `/materiais`
- **Método:** `POST`
- **Headers:** `Authorization: Bearer <token>`
- **Body:**
```json
{
  "aula_id": "aula-123",
  "professor_id": "prof-456",
  "tipo": "PDF",
  "url": "https://storage.cckids.com/materiais/aula123.pdf"
}
```

### Download de Material (Base64)
Retorna o conteúdo binário do material codificado em Base64.

- **URL:** `/materiais/{id}/download`
- **Método:** `GET`
- **Headers:** `Authorization: Bearer <token>`

- **Response (200 OK):**
`"JVBERi0xLjQKJ..."` (String pura contendo o Base64)

---

## Objetos Comuns (DTOs)

### Professor DTO
```json
{
  "id": "String",
  "nome": "String",
  "email": "String",
  "status": "PENDING | APPROVED",
  "role": "COORDINATOR | PROFESSOR"
}
```

### Turma DTO
```json
{
  "id": "String",
  "nome": "String",
  "faixa_etaria_min": "Int",
  "faixa_etaria_max": "Int",
  "ativa": "Boolean"
}
```