package ir.mapsa.wallet.entities;

import ir.mapsa.wallet.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Table(name = "T_wallet")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Wallet extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String walletName;

    private String walletId;

    private BigDecimal balance;

    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "wal_acc_id")
    public Account account;
}
