package support.api.v1.accountrest;

import io.restassured.response.Response;
import support.api.v1.order.OrderApi;
import support.model.UserAccount;
import java.util.HashMap;
import java.util.Map;

import static com.br.advantage.utils.Constants.TOKEN_DEFAULT_USER;
import static io.restassured.RestAssured.given;

public class AccountApi {
    private static final String DELETE_ENDPOINT = "accountservice/accountrest/api/v1/delete";
    private static final String REGISTER_ENDPOINT = "accountservice/accountrest/api/v1/register";
    private static final String LOGIN_ENDPOINT = "accountservice/accountrest/api/v1/login";
    private static final String LOGOUT_ENDPOINT = "accountservice/accountrest/api/v1/logout";

    private OrderApi orderApi;
    private UserAccount.LoginDetails loginDetails;
    private UserAccount userAccount;
    Map<String, String> loginPayload;
    public AccountApi() {
        orderApi = new OrderApi();
        loginDetails = UserAccount.LoginDetails.getINSTANCE();
        userAccount = UserAccount.getINSTANCE();
       loginPayload = new HashMap<>();

    }




    public Response createNewUser(UserAccount userAccount) {
        return given()
                .body(userAccount)
                .when()
                .post(REGISTER_ENDPOINT);
    }


    public Response userLoginAs(UserAccount userAccount) {
        loginPayload.put("email", userAccount.getEmail());
        loginPayload.put("loginPassword", userAccount.getPassword());
        loginPayload.put("loginUser", userAccount.getLoginName());
        return given()
                .body(loginPayload)
                .when()
                .post(LOGIN_ENDPOINT);
    }

    public Response userLogout(UserAccount.LoginDetails loginDetails) {
          return given()
                .body(loginDetails)
                .when()
                .post(LOGOUT_ENDPOINT);
    }


    public void deleteUser(UserAccount userAccount) {
        loginPayload.put("email", userAccount.getEmail());
        loginPayload.put("loginPassword", userAccount.getPassword());
        loginPayload.put("loginUser", userAccount.getLoginName());
        Response response = given()
                .body(loginPayload)
                .when()
                .post(LOGIN_ENDPOINT);

        Map<String, String> deletePayload = new HashMap<String, String>();
        deletePayload.put("accountId", response.body().jsonPath().get("statusMessage.userId").toString());

        given()
                .header("Authorization", "Bearer " + TOKEN_DEFAULT_USER)
                .body(deletePayload)
                .when()
                .delete(DELETE_ENDPOINT)
                .then();
    }

}


