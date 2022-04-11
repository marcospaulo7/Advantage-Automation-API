#language: pt
#encoding: UTF-8
#Author: Marcos Paulo
#Date: 11/04/2022
#version: 1.0

@AccountService
Funcionalidade: Cenarios contemplando a criação de uma nova conta de usuário, realizar Login e logout do mesmo


  @CreateNewUser
  Cenario: Retornar mensagem de sucesso com status true quando criar um novo usuario
    Dado um usuario que nao tenha cadastro
      | loginName | password | email        | firstName | lastName | phoneNumber | stateProvince | zipCode  | address    | allowOffers | aobUser | cityName | country |
      | marcosP   | Mudar123 | mp@email.com | marcos    | paulo    | 123456789   | SP            | 06516090 | ruaexemplo | true        | true    | barueri  | BRAZIL  |
    Quando preencher o cadastro corretamente
    Entao é retornado uma mensagem de sucesso
      | message                        | success |
      | New user created successfully. | true    |

  @CreateNewUserInvalidLoginOrPassword
  Cenario: Retornar mensagem de sucesso com status false quando tentar criar um novo usuario
  com login Name ou senha invalidos
    Dado um usuario que nao tenha cadastro
      | loginName | password        | email            | firstName | lastName | phoneNumber | stateProvince | zipCode  | address    | allowOffers | aobUser | cityName | country |
      | Marcos    | invalidpassword | marcos@email.com | marcos    | paulo    | 123456789   | SP            | 06516090 | ruaexemplo | true        | true    | barueri  | BRAZIL  |
    Quando preencher o cadastro com login Name ou senha invalidos
    Entao é retornado uma mensagem de erro
      | message                          | success |
      | Incorrect user name or password. | false   |

  @CreatenewUserWithExistingLogin
  Cenario: Retornar mensagem de sucesso com status false quando tentar criar um usuario ja existente
    Dado um usuario que ja tenha cadastro
      | loginName | password | email            | firstName | lastName | phoneNumber | stateProvince | zipCode  | address    | allowOffers | aobUser | cityName | country |
      | marcos    | Mudar123 | marcos@email.com | marcos    | paulo    | 123456789   | SP            | 06516090 | ruaexemplo | true        | true    | barueri  | BRAZIL  |
    Quando preencher o cadastro corretamente
    Entao é retornado uma mensagem de erro
      | message                  | success |
      | User name already exists | false   |

  @LoginWithRegisteredUser
  Cenario: Validar login com usuario cadastrado
    Dado que tenha um usuario com acesso ao sistema
      | loginName | password | email            |
      | marcos    | Mudar123 | marcos@email.com |
    Quando tentar acessar o sistema com login valido
    Entao sera retornado uma mensagem de sucesso
      | message          | success |
      | Login Successful | true    |
    E o userId e token  do usuario logado


  @LoginWithNonRegisteredUser
  Cenario: Validar login com usuario nao cadastrado
    Dado que tenha um usuario sem acesso ao sistema
      | loginName   | password        | email            |
      | invalidName | invalidpassword | marcos@email.com |
    Quando inserir os dados do usuario nao cadastrado
    Entao sera retornado uma mensagem de erro
      | message                          | success |
      | Incorrect user name or password. | false   |

  @LogouUser
  Cenario: Realizar logout com sucesso
    Dado que um usuario esteja logado
      | loginName | password | email            |
      | marcos    | Mudar123 | marcos@email.com |
    Quando realizar o logout do sistema
    Entao é retornado uma mensagem de sucesso
      | message           | success |
      | Logout Successful | true    |
