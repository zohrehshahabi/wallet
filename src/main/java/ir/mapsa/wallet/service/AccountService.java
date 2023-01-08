package ir.mapsa.wallet.service;

import ir.mapsa.wallet.base.BaseService;
import ir.mapsa.wallet.entities.Account;
import ir.mapsa.wallet.exceptions.NotFoundExceptions;

import java.util.List;

public interface AccountService extends BaseService<Account,Long> {
    Account findByUsernameAndPassword(String username, String password) throws NotFoundExceptions;

    Account findByUsername(String username) throws NotFoundExceptions;

    Boolean existsByUsername(String username);

    List<Account> findAll();
}
