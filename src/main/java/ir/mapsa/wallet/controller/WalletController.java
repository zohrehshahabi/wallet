package ir.mapsa.wallet.controller;

import ir.mapsa.wallet.dto.WalletDto;
import ir.mapsa.wallet.entities.Wallet;
import ir.mapsa.wallet.exceptions.BaseException;
import ir.mapsa.wallet.mapper.WalletMapper;
import ir.mapsa.wallet.service.AccountService;
import ir.mapsa.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/wallets")
@RequiredArgsConstructor
public class WalletController {
    private final WalletService walletService;

    private final AccountService accountService;

    private final WalletMapper walletMapper;

    @PostMapping()
    public WalletDto save(Principal principal, @Valid @RequestBody WalletDto walletDto) throws BaseException {
        Wallet wallet = walletMapper.convertDtoToEntity(walletDto);
        wallet.setAccount(accountService.findByUsername(principal.getName()));
        return walletMapper.convertEntityToDto(walletService.saveOrUpdate(wallet));
    }

    @PutMapping
    public WalletDto update(@Valid @RequestBody WalletDto walletDto) throws BaseException {
        return walletMapper.convertEntityToDto(walletService.saveOrUpdate(walletMapper.convertDtoToEntity(walletDto)));
    }

    @GetMapping("/{id}")
    public WalletDto findById(@PathVariable Long id) throws BaseException {
        return walletMapper.convertEntityToDto(walletService.findById(id));
    }

    @GetMapping
    public List<WalletDto> findAll() throws BaseException {
        return walletMapper.convertListEntityToListDto(walletService.findAll());
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) throws BaseException {
        walletService.deleteById(id);
    }
}
