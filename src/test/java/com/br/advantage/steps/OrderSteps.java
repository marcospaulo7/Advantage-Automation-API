package com.br.advantage.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import support.api.v1.order.OrderApi;
import support.enums.ColorEnum;
import support.model.UserAccount;

import java.util.Collections;
import java.util.List;

public class OrderSteps {

    List<String> data;
    private OrderApi orderApi;
    private Response response;
    private UserAccount.LoginDetails loginDetails;

    public OrderSteps() {
        orderApi = new OrderApi();
        loginDetails = UserAccount.LoginDetails.getINSTANCE();
    }


    @Quando("ele escolher um produto para adicionar no carrinho")
    public void eleAdicionarUmItemNoCarrinho(DataTable dataTable) {
        data = dataTable.asList();

        response = orderApi.addProductToSHopopingCart(
                loginDetails,
                Integer.parseInt(data.get(3)),
                ColorEnum.getColor(data.get(4)),
                Integer.parseInt(data.get(5))
        );
    }

    @Entao("o produto é adicionado no carrinho caso tenha em estoque")
    public void oProdutoÉAdicionadoNoCarrinho() {
        response
                .then()
                .body("productsInCart.productId",
                        Matchers.equalTo(Collections.singletonList(Integer.parseInt(data.get(3)))),
                        "productsInCart.exists", Matchers.equalTo(Collections.singletonList(true)),
                "userId", Matchers.is(Integer.parseInt(loginDetails.getAccountId())))
        ;

    }
}
