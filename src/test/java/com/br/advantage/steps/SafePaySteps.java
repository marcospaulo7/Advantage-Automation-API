package com.br.advantage.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import support.api.v1.order.OrderApi;
import support.api.v1.safepay.SafePayApi;
import support.enums.ColorEnum;
import support.model.SafePay;
import support.model.UserAccount;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SafePaySteps {

    String patternDate = "ddMMyyyy";
    private SafePayApi safePayApi;
    private OrderApi orderApi;
    private Response response;
    private UserAccount.LoginDetails loginDetails;
    private SafePay safePay;
    private UserAccount userAccount;

    public SafePaySteps() {
        safePayApi = new SafePayApi();
        orderApi = new OrderApi();
        loginDetails = UserAccount.LoginDetails.getINSTANCE();
        userAccount = UserAccount.getINSTANCE();

    }

    @Dado("o usuario queira fazer o pagamento de um item de seu carrinho")
    public void ousuarioqueirafazeropagamentodeumitemdeseucarrinho() {
        orderApi = new OrderApi();
        loginDetails = UserAccount.LoginDetails.getINSTANCE();
        response = orderApi.addProductToSHopopingCart(
                loginDetails,
                17,
                ColorEnum.getColor("BLACK"),
                1);
    }

    @Quando("inserir os dados para pagamento via SafePay")
    public void inserirOsDadosParaPagamentoViaSafePay(DataTable dataTable) {
        List<String> data = dataTable.asList();

        String dateInString = new SimpleDateFormat(patternDate).format(new Date());

        safePay = SafePay.builder()
                .SPUserName(userAccount.getLoginName())
                .SPTransactionDate(dateInString)
                .SPReceivingAmountValue(response.body().jsonPath().get("productsInCart[0].price"))
                .SPCustomerPhone(data.get(3))
                .SPPassword(userAccount.getPassword())
                .SPReceivingAmountCurrency(data.get(4))
                .SPReceivingCardAccountNumber(Integer.parseInt(data.get(5)))
                .build();
        response = safePayApi.doPaymentSafePay(safePay);
    }

    @Entao("é retornado uma mensagem com os dados da compra realizada")
    public void éRetornadoUmaMensagemComOsDadosDaCompraRealizada() {
        response
                .then().statusCode(201);
    }
}
