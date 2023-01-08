package ir.mapsa.wallet.service.impl;


import ir.mapsa.wallet.entities.Enums.EBankStatus;
import ir.mapsa.wallet.entities.Enums.EType;
import ir.mapsa.wallet.entities.TransactionReport;
import ir.mapsa.wallet.entities.Wallet;
import ir.mapsa.wallet.exceptions.*;
import ir.mapsa.wallet.service.TransactionService;
import ir.mapsa.wallet.service.TransferService;
import ir.mapsa.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Transactional
@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    WalletService walletService;
    TransactionService transactionService;
    private static final Logger logger = LoggerFactory.getLogger(TransferServiceImpl.class);

    @Override
    public void withdraw(String sourceWalletId, EBankStatus bankGatewayStatus, BigDecimal amount) throws BaseException {
        if (sourceWalletId == null) {

            logger.error("your entity not found", WalletIdNullException.class);
            throw new WalletIdNullException("id is null");

        } else {
            Wallet sourceWallet = walletService.findByWalletId(sourceWalletId);
            TransactionReport transactionReport = TransactionReport.builder()
                    .sourceWalletId(sourceWalletId)
                    .amount(amount)
                    .type(EType.withdraw).build();
            if (sourceWallet.getBalance().compareTo(amount) == -1) {
                transactionReport.setDescription("balance is not enough");
                transactionReport.setStatus(false);
                transactionService.saveOrUpdate(transactionReport);
                throw new NotEnoughBalanceException("balance is not enough");
            } else if (bankGatewayStatus == EBankStatus.Fail) {
                transactionReport.setDescription("bad gateway");
                transactionReport.setStatus(false);
                transactionService.saveOrUpdate(transactionReport);
                throw new BadGatewayException("bad gateway");
            } else if (!sourceWallet.getStatus()) {
                transactionReport.setDescription("wallet is not active");
                transactionReport.setStatus(false);
                transactionService.saveOrUpdate(transactionReport);
            } else {
                sourceWallet.setBalance(sourceWallet.getBalance().subtract(amount));
                walletService.saveOrUpdate(sourceWallet);
                transactionReport.setStatus(true);
                transactionReport.setDescription("transaction is done successfully");
                transactionService.saveOrUpdate(transactionReport);
            }
        }

    }

    @Override
    public void transferWalletToWallet(String sourceWalletId, String destinationWalletId, BigDecimal amount) throws BaseException {
        if (sourceWalletId == null || destinationWalletId == null) {
            logger.error("your entity not found: {}", WalletIdNullException.class);
            throw new WalletIdNullException("WalletId is null");
        } else {
            Wallet sourceWallet = walletService.findByWalletId(sourceWalletId);
            Wallet destinationWallet = walletService.findByWalletId(destinationWalletId);
            TransactionReport transactionReport = TransactionReport.builder()
                    .sourceWalletId(sourceWalletId)
                    .amount(amount)
                    .wallet(sourceWallet)
                    .type(EType.transfer)
                    .destinationWalletId(destinationWalletId).build();
            if(!sourceWallet.getStatus() || !destinationWallet.getStatus()){
                transactionReport.setDescription("wallet is not active");
                transactionReport.setStatus(false);
                transactionService.saveOrUpdate(transactionReport);

                logger.error("wallet is not active: {}", WalletNotActiveException.class);
                throw new WalletNotActiveException("wallet is not active");
            }
            else if (sourceWallet.getBalance().compareTo(amount) == -1) {
                transactionReport.setDescription("balance is not enough");
                transactionReport.setStatus(false);
                transactionService.saveOrUpdate(transactionReport);
                logger.error("your balance is not enough : {}", NotEnoughBalanceException.class);
                throw new NotEnoughBalanceException("balance is not enough");
            }else {
                sourceWallet.setBalance(sourceWallet.getBalance().subtract(amount));
                destinationWallet.setBalance(destinationWallet.getBalance().add(amount));
                walletService.saveOrUpdate(sourceWallet);
                walletService.saveOrUpdate(destinationWallet);
                transactionReport.setStatus(true);
                transactionReport.setDescription("transfer is done successfully");
                transactionService.saveOrUpdate(transactionReport);
                logger.info("transfer is done successfully");
            }
        }
    }

    @Override
    public void deposit(String sourceWalletId, EBankStatus bankGatewayStatus, BigDecimal amount) throws BaseException {
        if(sourceWalletId==null){
            logger.error("your entity not found: {}", WalletIdNullException.class);
            throw new WalletIdNullException("id is null");
        }else{
            Wallet sourceWallet=walletService.findByWalletId(sourceWalletId);
            TransactionReport transactionReport = TransactionReport.builder()
                    .sourceWalletId(sourceWalletId)
                    .amount(amount)
                    .type(EType.deposit).build();

            if(bankGatewayStatus == EBankStatus.Fail){
                transactionReport.setDescription("bad gateway");
                transactionReport.setStatus(false);
                transactionService.saveOrUpdate(transactionReport);
                logger.error("bad gateway : {}", BadGatewayException.class);
                throw new BadGatewayException("bad gateway");
            }
            else if (!sourceWallet.getStatus()) {
                transactionReport.setDescription("wallet is not active");
                transactionReport.setStatus(false);
                transactionService.saveOrUpdate(transactionReport);
                logger.error("wallet is not active : {}", WalletNotActiveException.class);
                throw new WalletNotActiveException("wallet is not active");
            }else{
                sourceWallet.setBalance(sourceWallet.getBalance().add(amount));
                walletService.saveOrUpdate(sourceWallet);
                transactionReport.setStatus(true);
                transactionReport.setDescription("deposit is done successfully");
                transactionService.saveOrUpdate(transactionReport);
                logger.info("deposit is done successfully");
            }


        }
    }


}