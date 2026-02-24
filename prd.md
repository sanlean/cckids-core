# 📄 PRD.md

(Product Requirements Document – Regras de Negócio)

---

# 1. Objetivo do Produto

Sistema para gestão do ministério infantil de igreja, permitindo:

* Planejamento mensal de aulas (Escalas)
* Organização de turmas
* Gestão de professores
* Controle de check-in
* Registro de presença
* Upload de materiais
* Histórico e auditoria completa

O sistema deve refletir a realidade prática do ministério, permitindo flexibilidade operacional sem perder rastreabilidade.

---

# 2. Conceitos Fundamentais de Domínio

---

## 2.1 Aula (Entidade Central)

A Aula é a unidade principal do sistema.

Ela representa:

* Uma data específica
* Um culto específico
* Uma ou mais turmas
* Um conjunto de professores
* Um conjunto de alunos presentes
* Materiais e registros relacionados

A Aula também representa o que operacionalmente é chamado de "Escala".

Não existe entidade separada chamada Escala.

---

### 2.1.1 Propriedades da Aula

* id
* dateTime
* culto (ex: DOMINGO_MANHA, DOMINGO_NOITE, QUINTA)
* status (calculado)
* tipo (NORMAL | EVENTO)
* lista de Turmas associadas
* lista de Professores associados
* lista de Check-ins
* lista de Materiais
* criadoEm
* atualizadoEm

---

### 2.1.2 Status da Aula (Calculado)

O status não é editável manualmente.

Ele é derivado das regras:

* PLANNED → data futura
* WARNING → data passada e zero check-ins
* DONE → existe ao menos um check-in

O status é sempre recalculado dinamicamente.

---

## 2.2 Professor

Entidade global.

### Propriedades:

* id
* nome
* email
* status (PENDING | APPROVED)
* role (COORDINATOR | PROFESSOR)

---

## 2.3 Papel do Professor na Aula

Uma Aula pode ter múltiplos professores.

Cada vínculo possui:

* professorId
* aulaId
* papel (PROFESSOR | AUXILIAR)

Regra obrigatória:

* Toda Aula deve possuir no mínimo 1 PROFESSOR.
* Pode possuir N AUXILIARES.

---

## 2.4 Turma

Turma representa uma configuração organizacional de faixa etária.

### Propriedades:

* id
* nome
* faixaEtariaMin
* faixaEtariaMax
* ativa (boolean)

---

### Regras da Turma

* Uma Turma pode mudar sua faixa etária.
* Em dias específicos, duas ou mais turmas podem ser associadas à mesma Aula.
* Em determinados cultos (ex: domingo noite, quinta), pode existir apenas uma turma geral.
* Turma não vincula permanentemente alunos.

---

## 2.5 Aluno

Entidade global única.

### Propriedades:

* id
* nome
* dataNascimento (opcional)
* idadeManual (opcional)
* genero
* foto (opcional)
* infoResponsavel (opcional)
* ativo (boolean)

Regras:

* Um aluno só existe uma vez no sistema.
* Pode participar de qualquer Aula independentemente de faixa etária.
* Futuramente poderá haver processo de unificação de cadastros duplicados.

---

## 2.6 Check-in

Check-in representa a entrada de um aluno em uma Aula.

### Propriedades:

* id
* aulaId
* alunoId
* registradoPorProfessorId
* dataHora

---

### Regras de Check-in

* Não pode existir mais de um check-in do mesmo aluno na mesma Aula.
* Check-in pode ocorrer a partir de X minutos antes da Aula.
* Check-in pode ser feito retroativamente (sem limite de tempo).
* Check-in cria automaticamente vínculo de presença do aluno na Aula.

---

## 2.7 Materiais

Materiais são anexos relacionados à Aula.

* id
* aulaId
* enviadoPorProfessorId
* tipo (FOTO | PDF | OUTRO)
* dataHora

---

# 3. Geração de Aulas (Escalas)

* As Aulas são criadas com antecedência mensal.
* A criação é realizada via backend administrativo.
* Ao criar uma Aula já são definidos:

    * Data
    * Culto
    * Turmas
    * Professores (com papéis)

---

# 4. Aulas Avulsas e Eventos

O sistema permite:

* Aula avulsa
* Evento

Eventos:

* Não possuem recorrência
* Por enquanto seguem mesmas regras de Aula normal

---

# 5. Permissões

---

## 5.1 Professor

Pode:

* Visualizar todas as Aulas
* Visualizar todas as Turmas
* Visualizar todos os Alunos
* Realizar check-in em qualquer Aula
* Adicionar materiais em qualquer Aula
* Editar presença

Mesmo que não esteja vinculado oficialmente à Aula.

---

## 5.2 Coordenador

Além das permissões de Professor:

* Aprovar professores
* Gerenciar criação de Aulas
* Gerenciar Turmas
* Acessar relatórios globais

---

# 6. Auditoria (Obrigatória)

Todas as ações relevantes devem ser registradas:

* Criação de Aula
* Alteração de Aula
* Check-in
* Exclusão de check-in
* Upload de material
* Alteração de dados de aluno
* Alteração de dados de professor

Cada log deve conter:

* ação
* entidade afetada
* id da entidade
* professor responsável
* timestamp

Auditoria é obrigatória e não pode ser desativada.

---

# 7. Invariantes de Negócio (Não podem ser quebradas)

1. Toda Aula deve ter ao menos 1 PROFESSOR.
2. Um aluno não pode ter dois check-ins na mesma Aula.
3. Status da Aula é sempre derivado e nunca manual.
4. Todo aluno é entidade global única.
5. Toda ação deve ser auditável.
6. Professores só podem acessar o sistema após aprovação.

---

# 8. Flexibilidade Operacional

O sistema deve permitir:

* Professores atuarem fora da escala oficial.
* Check-in retroativo.
* Alteração de turmas em dias específicos.
* Aulas extraordinárias.

Sem travas rígidas, mas com auditoria.

---

# 9. Premissas Arquiteturais de Negócio

* O domínio é centrado na entidade Aula.
* Escala é apenas vocabulário operacional.
* Aluno não pertence a Turma permanentemente.
* Presença é consequência de Check-in.
* O sistema prioriza flexibilidade com rastreabilidade.
