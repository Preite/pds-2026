# 📚 Projeto e Desenvolvimento de Sistemas

## 📝 Exercícios — Diagramas de Casos de Uso

Este exercício tem como objetivo praticar:

- Identificação de atores
- Identificação de casos de uso
- Relacionamento entre atores e sistema
- `<<include>>`
- `<<extend>>`
- Construção de diagramas UML utilizando **PlantUML**

---

# 📖 Exercício 1 — App de Biblioteca

## 🎯 Cenário do Problema

A biblioteca da sua faculdade quer modernizar o seu funcionamento com um aplicativo simples.

O objetivo é permitir que os alunos possam:

- Pesquisar livros no acervo pelo celular;
- Verificar se um livro está disponível;
- Fazer a reserva de um livro;
- Retirá-lo posteriormente.

Além disso, o bibliotecário precisa de uma forma de gerenciar os empréstimos, registrando:

- A retirada dos livros;
- A devolução dos livros.

---

## 🔎 Sua missão

### 1. Identifique os atores

Liste os atores que interagem diretamente com o sistema.

**Atores:**

1. _Alunos_________________________________
2. _Bibliotecario_________________________________
3. __________________________________

---

### 2. Liste os casos de uso

#### Ator: _Alunos_____________________________

- __Verificar livros________________________________
- __Diponibilidade do mesmo________________________________
- __Fazer a reserva________________________________
- __________________________________

#### Ator: __Bibliotecario____________________________

- ____Registrar retirada de livro______________________________
- ____Registrar entrada e saida de livro_____________________________
- ____Gerencia emprstimos_____________________________
- __________________________________

---

### 3. Diagrama de Casos de Uso

@startuml

left to right direction

actor "Aluno" as Aluno
actor "Bibliotecário" as Bibliotecario

rectangle "Sistema da Biblioteca" {

    usecase "Pesquisar livros" as UC1
    usecase "Verificar disponibilidade" as UC2
    usecase "Reservar livro" as UC3

    usecase "Gerenciar empréstimos" as UC4
    usecase "Registrar retirada" as UC5
    usecase "Registrar devolução" as UC6
}

Aluno --> UC1
Aluno --> UC2
Aluno --> UC3

Bibliotecario --> UC4
Bibliotecario --> UC5
Bibliotecario --> UC6

UC3 ..> UC2 : <<include>>
UC4 ..> UC5 : <<include>>
UC4 ..> UC6 : <<include>>

@enduml