package ir.mapsa.wallet.mapper;


import ir.mapsa.wallet.dto.AccountDto;
import ir.mapsa.wallet.entities.Account;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper
public interface AccountMapper  {
    Account convertDtoToEntity(AccountDto accountDto);

    AccountDto convertEntityToDto(Account account);

    List<Account> convertListDtoToListEntity(List<AccountDto> accountDtoList);

    List<AccountDto> convertListEntityToListDto(List<Account> accountList);
}
