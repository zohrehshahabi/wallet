package ir.mapsa.wallet.controller;

import ir.mapsa.wallet.dto.TransactionReportDto;
import ir.mapsa.wallet.exceptions.BaseException;
import ir.mapsa.wallet.mapper.TransactionReportMapper;
import ir.mapsa.wallet.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionReportService;

    private final TransactionReportMapper transactionReportMapper;


    @PostMapping()
    public TransactionReportDto save(@Valid @RequestBody TransactionReportDto transactionReportDto) throws BaseException {
        return transactionReportMapper.
                convertEntityToDto(transactionReportService.
                        saveOrUpdate( transactionReportMapper.convertDtoToEntity(transactionReportDto)));
    }

    @PutMapping
    public TransactionReportDto update(@Valid @RequestBody TransactionReportDto transactionReportDto) throws BaseException {
        return transactionReportMapper.
                convertEntityToDto(transactionReportService.
                        saveOrUpdate(transactionReportMapper.convertDtoToEntity(transactionReportDto)));
    }

    @GetMapping("/{id}")
    public TransactionReportDto findById(@PathVariable Long id) throws BaseException {
        return transactionReportMapper.convertEntityToDto(transactionReportService.findById(id));
    }

    @GetMapping
    public List<TransactionReportDto> findAll() throws BaseException {
        return transactionReportMapper.convertListEntityToListDto(transactionReportService.findAll());
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) throws BaseException {
        transactionReportService.deleteById(id);
    }
}
