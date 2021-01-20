package br.com.dockbank.bankaccount.domain.mapper;

import br.com.dockbank.bankaccount.domain.entity.TransactionBank;
import br.com.dockbank.bankaccount.domain.response.TransactionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TransactionMapper {
    @Mapping(source = "accountBank.accountId", target = "accountId")
    TransactionResponse toResponse(TransactionBank transactionBank);
}
