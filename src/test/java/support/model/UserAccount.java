package support.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import support.enums.AccountTypeEnum;
import support.enums.CountryEnum;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserAccount {
    private static UserAccount INSTANCE;

    @Builder.Default
    public AccountTypeEnum accountTypeEnum = AccountTypeEnum.USER;
    @Builder.Default
    public String firstName = "Marcos";
    @Builder.Default
    public String lastName = "paulo";
    @Builder.Default
    public String email = "marcos@email.com";
    @Builder.Default
    public String loginName = "marcos22";
    @Builder.Default
    public String password = "Mudar123";
    @Builder.Default
    public boolean aobUser = true;
    @Builder.Default
    public CountryEnum country = CountryEnum.BRAZIL_BR;
    @Builder.Default
    public String stateProvince = "SP";
    @Builder.Default
    public String cityName = "Barueri";
    @Builder.Default
    public String zipcode = "06516090";
    @Builder.Default
    public String address = "rua das bananeiras";
    @Builder.Default
    public String phoneNumber = "123456789";
    @Builder.Default
    public boolean allowOffersPromotion = false;


    public static UserAccount getINSTANCE() {
        if (UserAccount.INSTANCE == null) {
            synchronized (UserAccount.class) {
                if (INSTANCE == null) {
                    INSTANCE = new UserAccount();
                }
            }
        }
        return INSTANCE;
    }


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LoginDetails {


        private static LoginDetails INSTANCE;
        public String token;
        public String accountId;

        public static LoginDetails getINSTANCE() {
            if (INSTANCE == null) {
                synchronized (LoginDetails.class) {
                    if (INSTANCE == null) {
                        INSTANCE = new LoginDetails();
                    }
                }
            }
            return INSTANCE;
        }
    }
}




