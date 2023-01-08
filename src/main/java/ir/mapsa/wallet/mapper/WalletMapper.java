package ir.mapsa.wallet.mapper;

import ir.mapsa.wallet.dto.WalletDto;
import ir.mapsa.wallet.entities.Wallet;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface WalletMapper  {
    Wallet convertDtoToEntity(WalletDto walletDto);

    WalletDto convertEntityToDto(Wallet wallet);

    List<Wallet> convertListDtoToListEntity(List<WalletDto> walletDtoList);

    List<WalletDto> convertListEntityToListDto(List<Wallet> walletList);
}
