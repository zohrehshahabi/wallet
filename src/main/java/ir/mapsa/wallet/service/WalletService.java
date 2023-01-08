package ir.mapsa.wallet.service;

import ir.mapsa.wallet.base.BaseService;
import ir.mapsa.wallet.entities.Wallet;
import ir.mapsa.wallet.exceptions.BaseException;

import java.util.List;

public interface WalletService extends BaseService<Wallet,Long> {

    Wallet saveOrUpdate(Wallet t) throws BaseException;
    Wallet findByWalletId(String walletId) throws BaseException;
    List<Wallet> findWalletsByAccount_FirstName(String firstName);

}
