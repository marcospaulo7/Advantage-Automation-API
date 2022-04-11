#language: pt
#encoding: UTF-8
#Author: Marcos Paulo
#Date: 11/04/2022
#version: 1.0


@Order
Funcionalidade: Cenarios contemplando a inserçao de um item no carrinho

  @AddProduct @Order
  Cenario: Validar inserção produto no carrinho
    Dado que um usuario esteja logado
      | loginName | password | email            |
      | marcos21  | Mudar123 | marcos@email.com |
    Quando ele escolher um produto para adicionar no carrinho
      | productId | color | quantity |
      | 17        | BLACK | 1        |
    Entao o produto é adicionado no carrinho caso tenha em estoque

