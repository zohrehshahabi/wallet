package ir.mapsa.wallet.mapper;


import ir.mapsa.wallet.dto.TransactionReportDto;
import ir.mapsa.wallet.entities.TransactionReport;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TransactionReportMapper {
    TransactionReport convertDtoToEntity(TransactionReportDto transactionReportDto);

    TransactionReportDto convertEntityToDto(TransactionReport transactionReport);

    List<TransactionReport> convertListDtoToListEntity(List<TransactionReportDto> transactionReportDtoList);

    List<TransactionReportDto> convertListEntityToListDto(List<TransactionReport> transactionReportList);

}