package ir.mapsa.wallet.controller;


import ir.mapsa.wallet.dto.DepositDto;
import ir.mapsa.wallet.dto.TransferDto;

import ir.mapsa.wallet.dto.WithdrawDto;
import ir.mapsa.wallet.entities.Account;
import ir.mapsa.wallet.entities.Wallet;
import ir.mapsa.wallet.exceptions.BaseException;
import ir.mapsa.wallet.exceptions.NotFoundExceptions;
import ir.mapsa.wallet.payload.response.MessageResponse;
import ir.mapsa.wallet.service.AccountService;
import ir.mapsa.wallet.service.TransferService;
import ir.mapsa.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/transfer")
@RequiredArgsConstructor
public class TransferController {

    private final TransferService transferService;

    private final WalletService walletService;

    private final AccountService accountService;

    @PostMapping("/wTow")
    public ResponseEntity<?> transferWalletToWallet(Principal principal, @RequestBody TransferDto transferDto) throws BaseException {
        Account account = accountService.findByUsername(principal.getName());
        Wallet wallet = walletService.findByWalletId(transferDto.getSourceWalletId());
        if (!wallet.getAccount().equals(account)) {
            throw new NotFoundExceptions("this wallet id is not yours");
        } else {
            transferService.transferWalletToWallet(
                    transferDto.getSourceWalletId(),
                    transferDto.getDestinationWalletId(),
                    transferDto.getAmount()
            );
        }
        return ResponseEntity.ok(new MessageResponse("transfer is done successfully"));
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(Principal principal, @RequestBody DepositDto depositDto) throws BaseException {
        Account account = accountService.findByUsername(principal.getName());
        Wallet wallet = walletService.findByWalletId(depositDto.getSourceWalletId());
        if (!wallet.getAccount().equals(account)) {
            throw new NotFoundExceptions("this wallet id is not yours");
        } else {
            transferService.deposit(
                    depositDto.getSourceWalletId(),
                    depositDto.getStatus(),
                    depositDto.getAmount()
            );
        }
        return ResponseEntity.ok(new MessageResponse("deposit is done successfully"));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(Principal principal, @RequestBody WithdrawDto withdrawDto) throws BaseException {
        Account account = accountService.findByUsername(principal.getName());
        Wallet wallet = walletService.findByWalletId(withdrawDto.getSourceWalletId());
        if (!wallet.getAccount().equals(account)) {
            throw new NotFoundExceptions("this wallet id is not yours");
        } else {
            transferService.deposit(
                    withdrawDto.getSourceWalletId(),
                    withdrawDto.getBankGatewayStatus(),
                    withdrawDto.getAmount()
            );
        }
        return ResponseEntity.ok(new MessageResponse("withdraw is done successfully"));
    }
}
