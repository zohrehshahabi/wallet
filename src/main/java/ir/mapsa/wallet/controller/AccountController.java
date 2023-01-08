package ir.mapsa.wallet.controller;

import ir.mapsa.wallet.dto.AccountDto;
import ir.mapsa.wallet.entities.Account;
import ir.mapsa.wallet.exceptions.BaseException;
import ir.mapsa.wallet.mapper.AccountMapper;
import ir.mapsa.wallet.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    public AccountService accountService;

    private final AccountMapper accountMapper;

    @PostMapping
    public AccountDto saveAccount(@Valid @RequestBody AccountDto accountDto) throws BaseException {
        return accountMapper.convertEntityToDto(accountService.saveOrUpdate(accountMapper.convertDtoToEntity(accountDto)));
    }

    @PutMapping
    public AccountDto updateAccount(@Valid @RequestBody AccountDto accountDto) throws BaseException {
        return accountMapper.convertEntityToDto(accountService.saveOrUpdate(accountMapper.convertDtoToEntity(accountDto)));
    }

    @GetMapping("/{id}")
    public AccountDto findById(@PathVariable Long id) throws BaseException {
        return accountMapper.convertEntityToDto(accountService.findById(id));
    }
    @GetMapping
    public List<AccountDto> findAll() throws BaseException{
        List<Account> all = accountService.findAll();
        return accountMapper.convertListEntityToListDto(all);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) throws BaseException{
         accountService.deleteById(id);
    }
}
