package ir.mapsa.wallet.service.impl;

import ir.mapsa.wallet.base.BaseServiceImpl;
import ir.mapsa.wallet.entities.TransactionReport;
import ir.mapsa.wallet.exceptions.BaseException;
import ir.mapsa.wallet.repository.TransactionRepository;
import ir.mapsa.wallet.service.TransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class TransactionServiceImpl extends BaseServiceImpl<TransactionReport,Long, TransactionRepository> implements TransactionService {

    public TransactionServiceImpl(TransactionRepository repository) {
        super(repository);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public TransactionReport saveOrUpdate(TransactionReport transactionReport) throws BaseException {
        return super.saveOrUpdate(transactionReport);
    }

}
