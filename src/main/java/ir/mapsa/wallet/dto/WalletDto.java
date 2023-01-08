package ir.mapsa.wallet.dto;

import ir.mapsa.wallet.base.BaseDto;
import ir.mapsa.wallet.entities.TransactionReport;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WalletDto extends BaseDto {

    private Long id;

    private String walletName;

    private String walletId;

    private BigDecimal balance;

    private Boolean status;

    private List<TransactionReport> transactionReportList = new ArrayList<>();
}

