package ir.mapsa.wallet.service.impl;

import ir.mapsa.wallet.base.BaseServiceImpl;
import ir.mapsa.wallet.entities.Wallet;
import ir.mapsa.wallet.exceptions.BaseException;
import ir.mapsa.wallet.repository.WalletRepository;
import ir.mapsa.wallet.service.WalletService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.util.List;

@Transactional
@Service
public class WalletServiceImpl extends BaseServiceImpl<Wallet, Long, WalletRepository> implements WalletService {

    public WalletServiceImpl(WalletRepository repository) {
        super(repository);
    }

    @Override
    public Wallet saveOrUpdate(Wallet wallet) throws BaseException{
        SecureRandom secureRandom=new SecureRandom();
        long sourceRandomWalletId =  Math.abs(secureRandom.nextLong());
        wallet.setWalletId(Long.toString(sourceRandomWalletId));
        return repository.save(wallet);
    }

    @Override
    public Wallet findByWalletId(String walletId) throws BaseException {
        return repository.findByWalletId(walletId);
    }

    @Override
    public List<Wallet> findWalletsByAccount_FirstName(String firstName) {
        return repository.findWalletsByAccount_FirstName(firstName);
    }
}
