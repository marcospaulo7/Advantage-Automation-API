package com.br.advantage.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import support.api.v1.accountrest.AccountApi;
import support.enums.AccountTypeEnum;
import support.enums.CountryEnum;
import support.model.UserAccount;

import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.hasKey;

public class AccountSteps {
    private final AccountApi accountApi;
    private UserAccount userAccount;
    private UserAccount.LoginDetails loginDetails;
    private Response response;


    public AccountSteps() {
        accountApi = new AccountApi();
        loginDetails = UserAccount.LoginDetails.getINSTANCE();
    }


    @After("@CreateNewUser")
    public void clearUser() {
        AccountApi accountApi = new AccountApi();
        accountApi.deleteUser(userAccount);
        System.out.println("delete user created");
    }

    @Dado("um usuario que {word} tenha cadastro")
    public void queQueiraRealizarUmNovoCadastro(String param, DataTable data) {
        for (Map<String, String> map : data.asMaps()) {
            userAccount = UserAccount.builder()
                    .accountTypeEnum(AccountTypeEnum.USER)
                    .email(map.get("email"))
                    .loginName(map.get("loginName"))
                    .password(map.get("password"))
                    .firstName(map.get("firstName"))
                    .lastName(map.get("lastName"))
                    .phoneNumber(map.get("phoneNumber"))
                    .allowOffersPromotion(true)
                    .aobUser(true)
                    .address(map.get("address"))
                    .stateProvince(map.get("stateProvince"))
                    .zipcode(map.get("zipCode"))
                    .cityName(map.get("cityName"))
                    .country(CountryEnum.getCountry(map.get("country")))
                    .build();
        }
    }

    @Quando("preencher o cadastro com login Name ou senha invalidos")
    @Quando("preencher o cadastro corretamente")
    public void preencherOCadastroComDadosCorretos() {
        response = accountApi.createNewUser(userAccount);
    }


    @Dado("que tenha um usuario {word} acesso ao sistema")
    public void queTenhaUmUsuarioCadastrado(String param, DataTable dataTable) {
        List<String> data = dataTable.asList();
        userAccount = UserAccount.builder()
                .loginName(data.get(3))
                .email(data.get(5))
                .password(data.get(4))
                .build();
    }


    @Quando("inserir os dados do usuario nao cadastrado")
    @Quando("tentar acessar o sistema com login valido")
    public void realizarOLoginComEssesDados() {
        response = accountApi.userLoginAs(userAccount);
    }

    @Entao("é retornado uma mensagem de {word}")
    public void deveSerRetornadoUmBodyDeStatus(String successParam, DataTable dataTable) {
        List<String> data = dataTable.asList();
        Boolean success = data.get(3).equals("true");
        response
                .then()
                .body("response.success", Matchers.is(success),
                        "response.reason", Matchers.is(data.get(2)))
        ;

    }

    @E("o userId e token  do usuario logado")
    public void oUserIdETokenDoUsuarioLogado() {
        response
                .then()
                .body("statusMessage", hasKey("userId"),
                        "statusMessage", hasKey("token")
                );
    }

    @Entao("sera retornado uma mensagem de {word}")
    public void seraRetornadoUmaMensagemDeErro(String successParam, DataTable dataTable) {
        List<String> data = dataTable.asList();
        Boolean success = data.get(3).equals("true");
        response
                .then()
                .body("statusMessage.success", Matchers.is(success),
                        "statusMessage.reason", Matchers.is(data.get(2)))
        ;

    }

    @Quando("realizar o logout do sistema")
    public void realizarOLogoutDoSistema() {
        response = accountApi.userLogout(loginDetails);
    }

    @Dado("que um usuario esteja logado")
    public void queEstejaLogado(DataTable dataTable) {
        List<String> data = dataTable.asList();
        userAccount = UserAccount.builder()
                .loginName(data.get(3))
                .email(data.get(5))
                .password(data.get(4))
                .build();


        response = accountApi.userLoginAs(userAccount);
        loginDetails.setToken(response.body().jsonPath().get("statusMessage.token").toString());
        loginDetails.setAccountId(response.body().jsonPath().get("statusMessage.userId").toString());
    }
}
