package com.br.advantage.steps;

import com.br.advantage.utils.Constants;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.hamcrest.Matchers;
import support.api.v1.order.OrderApi;
import support.model.UserAccount;

public class Hooks extends Constants {


    @BeforeStep
    public static void setup() {
        RestAssured.baseURI = DEFAULT_API_URL;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(APP_CONTENT_TYPE)
                .build();
        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .expectResponseTime(Matchers.lessThan(MAX_TIMEOUT))
                .build();
    }

    @Before
    public void setUp() {
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @After("@Safepay")
    public void clearShopCart() {
        OrderApi orderApi = new OrderApi();
        UserAccount.LoginDetails loginDetails = UserAccount.LoginDetails.getINSTANCE();
        System.out.println("clean shop cart created");
        orderApi.deleteUserShopCart(loginDetails);
    }

}
