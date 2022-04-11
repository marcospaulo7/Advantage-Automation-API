package support.api.v1.order;

import io.restassured.response.Response;
import support.model.UserAccount;

import static io.restassured.RestAssured.given;

public class OrderApi {
    private static final String ADD_PRODUCT_TO_SHOPPING_CART_ENDPOINT = "order/api/v1/carts/{userId}/product" +
            "/{productId" +
            "}/color" +
            "/{color}";
    private static final String CLEAR_SHOPPING_CART_ENDPOINT = "order/api/v1/carts/{userId}";


    public Response addProductToSHopopingCart(UserAccount.LoginDetails loginDetails, int productId,
                                              String color, int quantity) {

        return given()
                .header("Authorization", "Bearer " + loginDetails.token)
                .pathParam("userId", loginDetails.getAccountId())
                .pathParam("productId", productId)
                .pathParam("color", color)
                .queryParam("hasWarranty", true)
                .queryParam("quantity", quantity)
                .when()
                .post(ADD_PRODUCT_TO_SHOPPING_CART_ENDPOINT);
    }


    public void deleteUserShopCart(UserAccount.LoginDetails loginDetails) {
        given()
                .header("Authorization", "Bearer " + loginDetails.getToken())
                .pathParam("userId", loginDetails.getAccountId())
                .when()
                .delete(CLEAR_SHOPPING_CART_ENDPOINT);

    }
}
