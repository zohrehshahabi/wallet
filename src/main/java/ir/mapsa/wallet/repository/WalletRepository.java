package ir.mapsa.wallet.repository;

import ir.mapsa.wallet.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletRepository extends JpaRepository<Wallet,Long> {
    Wallet findByWalletId(String walletId);
    List<Wallet> findWalletsByAccount_FirstName(String firstName);
}
