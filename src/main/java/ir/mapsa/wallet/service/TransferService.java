package ir.mapsa.wallet.service;

import ir.mapsa.wallet.entities.Enums.EBankStatus;
import ir.mapsa.wallet.exceptions.BaseException;

import java.math.BigDecimal;

public interface TransferService {
    void transferWalletToWallet(String sourceWalletId, String destinationWalletId, BigDecimal amount) throws BaseException;

    void deposit(String sourceWalletId, EBankStatus bankGatewayStatus, BigDecimal amount) throws BaseException;

    void withdraw(String sourceWalletId, EBankStatus bankGatewayStatus, BigDecimal amount) throws BaseException;
}
