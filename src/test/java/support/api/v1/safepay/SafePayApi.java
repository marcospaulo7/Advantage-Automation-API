package support.api.v1.safepay;

import io.restassured.response.Response;
import support.model.SafePay;

import static io.restassured.RestAssured.given;

public class SafePayApi {
    private static final String PAYMENT_SAFEPAY_ENDPOINT = "SafePay/api/v1/payments/payment";

    public Response doPaymentSafePay(SafePay safePay) {
        return given()
                .body("{\n" +
                        "\"SPCustomerPhone\": \"" + safePay.getSPCustomerPhone() + "\",\n" +
                        "\"SPPassword\": \"" + safePay.getSPPassword() + "\",\n" +
                        "\"SPReceivingAmount.Currency\": \"" + safePay.getSPReceivingAmountCurrency() + "\",\n" +
                        "\"SPReceivingAmount.Value\":" + safePay.getSPReceivingAmountValue() + ",\n" +
                        "\"SPReceivingCard.AccountNumber\": " + safePay.getSPReceivingCardAccountNumber() + ",\n" +
                        "\"SPTransactionDate\": \"" + safePay.getSPTransactionDate() + "\",\n" +
                        "\"SPTransactionType\": \"" + safePay.getSPTransactionType() + "\",\n" +
                        "\"SPUserName\": \"" + safePay.getSPUserName() + "\"\n" +
                        "}")
                .when()
                .post(PAYMENT_SAFEPAY_ENDPOINT);
    }
}
