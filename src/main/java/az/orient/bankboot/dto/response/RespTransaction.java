package az.orient.bankboot.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RespTransaction {
    private Long transactionId;
    private RespAccount dtaccount;
    private String crAccount;
    private Integer amount;
    private String currency;
}
