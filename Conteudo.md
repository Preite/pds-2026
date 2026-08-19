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

🎬 Exercício 2 — App de Cinema
Cenário do Problema

Uma rede de cinemas local deseja criar um aplicativo para facilitar a vida de seus clientes. Através do app, o cliente deve poder ver a lista de filmes em cartaz, consultar os horários das sessões e comprar ingressos online, pagando com cartão de crédito. Ao chegar no cinema, o funcionário da entrada precisa de uma função no sistema para validar o ingresso do cliente, lendo um QR Code gerado no app após a compra.

Sua Missão
1. Identifique os atores que interagem com este sistema.

Atores:

Cliente
Funcionário da Entrada
Operadora de Cartão
 
2. Liste os casos de uso fundamentais para cada ator.
Ator: __Cliente________________________________

Visualizar filmes em cartaz
Consultar horários das sessões
Comprar ingresso
Realizar pagamento
Receber/gerar QR Code do ingresso

Ator: _____Funcionário da Entrada_____________________________

Validar ingresso
Ler QR Code

Ator: ____Operadora de Cartão______________________________
Processar pagamento

# 🏋️ Exercício 3 — App de Academia

##### Cenário do Problema

Uma academia quer oferecer um app para seus membros. O membro deve conseguir fazer o check-in na academia usando o app, visualizar sua ficha de treino pessoal e se inscrever nas aulas em grupo (como Spinning ou Zumba).

O instrutor da academia, por sua vez, precisa de uma área no sistema para montar e atualizar as fichas de treino de seus alunos.

Por fim, o sistema deve registrar a frequência dos membros nas aulas para gerar relatórios para a gerência.

---

##### Sua Missão

## 1. Identifique os **atores** que interagem com este sistema

Os atores identificados são:

- 👤 **Membro**
- 🏋️ **Instrutor**
- 👔 **Gerente**

---

## 2. Liste os **casos de uso** fundamentais para cada ator

### 👤 Membro

- Fazer check-in
- Visualizar ficha de treino
- Consultar aulas disponíveis
- Inscrever-se em aulas

### 🏋️ Instrutor

- Consultar alunos
- Montar ficha de treino
- Atualizar ficha de treino

### 👔 Gerente

- Consultar frequência
- Gerar relatório de frequência

---