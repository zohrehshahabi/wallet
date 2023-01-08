package ir.mapsa.wallet.repository;

import ir.mapsa.wallet.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {



    @Query(value = "select a from Account where a.username=:username and a.password=:password", nativeQuery = true)
    Account findByUsernameAndPassword(String username, String password);

    Account findByUsername(String username);

    Boolean existsByUsername(String username);
}
