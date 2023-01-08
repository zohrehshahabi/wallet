package ir.mapsa.wallet.service.impl;

import ir.mapsa.wallet.base.BaseServiceImpl;
import ir.mapsa.wallet.entities.Account;
import ir.mapsa.wallet.exceptions.NotFoundExceptions;
import ir.mapsa.wallet.repository.AccountRepository;
import ir.mapsa.wallet.service.AccountService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AccountServiceImpl extends BaseServiceImpl<Account, Long, AccountRepository>
        implements AccountService {

    public AccountServiceImpl(AccountRepository repository) {
        super(repository);
    }

    @Override
    public List<Account> findAll(){
        return repository.findAll();
    }

    @Override
    public Account findByUsernameAndPassword(String username, String password) throws NotFoundExceptions {
        Account account = repository.findByUsernameAndPassword(username, password);
        if (account.getId() == null) {
            throw new NotFoundExceptions("your entity not found");
        } else {
            return repository.findByUsernameAndPassword(username,password);
        }
    }

    @Override
    public Account findByUsername(String username) throws NotFoundExceptions {
        Account account = repository.findByUsername(username);
        if (account.getId() == null) {
            throw new NotFoundExceptions("your entity not found");
        } else {
            return repository.findByUsername(username);
        }
    }

    @Override
    public Boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }
}