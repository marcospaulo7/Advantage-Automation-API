#language: pt
#encoding: UTF-8
#Author: Marcos Paulo
#Date: 11/04/2022
#version: 1.0


@Safepay
Funcionalidade: Fluxo contemplando compra de um item via metodo safepay

  @Safepay
  Cenario: Validar pagamento de compra via SafePay
    Dado o usuario queira fazer o pagamento de um item de seu carrinho
    Quando inserir os dados para pagamento via SafePay
      | Phone     | ReceivingAmountCurrency | ReceivingCardAccountNumber |
      | 912345678 | USD                     | 843200971                  |
    Entao é retornado uma mensagem com os dados da compra realizada

