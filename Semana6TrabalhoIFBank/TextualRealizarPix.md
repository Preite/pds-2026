# Caso de Uso: Realizar Pix

**Ator principal:** Cliente
**Ator secundário:** Sistema BACEN
**Objetivo:** Permitir que o cliente realize uma transferência Pix para um destinatário utilizando uma chave Pix válida.

## Pré-condições

* O cliente deve estar autenticado e logado no aplicativo IFBank.
* O cliente deve possuir uma conta ativa no IFBank.
* A conta do cliente deve estar habilitada para realizar transferências Pix.

## Pós-condições

* O valor da transferência é debitado da conta do cliente.
* A transação Pix é registrada no extrato da conta.
* Um comprovante da operação é gerado e apresentado ao cliente.

## Fluxo Principal

1. O cliente acessa a área Pix do aplicativo.
2. O sistema apresenta a opção de realizar um Pix.
3. O cliente informa a chave Pix do destinatário.
4. O sistema envia a chave Pix para o Sistema BACEN para validação.
5. O Sistema BACEN verifica a existência da chave Pix.
6. O Sistema BACEN retorna os dados do destinatário ao IFBank.
7. O sistema apresenta os dados do destinatário para conferência do cliente.
8. O cliente informa o valor que deseja transferir.
9. O sistema verifica o saldo disponível na conta do cliente.
10. O sistema identifica que o saldo é suficiente para realizar a transferência.
11. O sistema apresenta os dados da operação para confirmação.
12. O cliente confirma a realização do Pix.
13. O sistema debita o valor da conta do cliente.
14. O sistema registra a transação no extrato.
15. O sistema gera o comprovante da transferência.
16. O sistema apresenta o comprovante ao cliente.
17. O caso de uso é encerrado com a transferência realizada com sucesso.

## Fluxos de Exceção

### [FE-01] Saldo Insuficiente

**A partir do passo 9 do fluxo principal:**

1. O sistema verifica o saldo disponível na conta.
2. O sistema identifica que o saldo é inferior ao valor informado pelo cliente.
3. O sistema não realiza o débito.
4. O sistema informa ao cliente que o saldo é insuficiente para realizar o Pix.
5. O cliente pode informar outro valor ou cancelar a operação.
6. Nenhuma transação é registrada no extrato.

### [FE-02] Chave Pix Inválida

**A partir do passo 4 do fluxo principal:**

1. O sistema envia a chave Pix informada para o Sistema BACEN.
2. O Sistema BACEN verifica a chave e informa que ela não existe ou é inválida.
3. O sistema informa ao cliente que a chave Pix não foi encontrada.
4. A transferência não pode prosseguir.
5. O cliente pode informar uma nova chave Pix ou cancelar a operação.
