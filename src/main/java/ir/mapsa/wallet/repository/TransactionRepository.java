package ir.mapsa.wallet.repository;

import ir.mapsa.wallet.entities.TransactionReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionReport,Long> {
}
