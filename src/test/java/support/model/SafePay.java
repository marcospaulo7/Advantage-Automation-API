package support.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SafePay {

    public String SPUserName;
    public String SPTransactionDate;
    public float SPReceivingAmountValue;
    public String SPCustomerPhone;
    public String SPPassword;
    public String SPReceivingAmountCurrency;
    public int SPReceivingCardAccountNumber;
    @Builder.Default
    public String SPTransactionType = "PAYMENT";


}
