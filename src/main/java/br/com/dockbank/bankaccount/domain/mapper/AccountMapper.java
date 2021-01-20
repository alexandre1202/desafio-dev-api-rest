package br.com.dockbank.bankaccount.domain.mapper;

import br.com.dockbank.bankaccount.domain.entity.AccountBank;
import br.com.dockbank.bankaccount.domain.response.AccountResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel="spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {
    @Mapping(source = "customerBank.customerId", target = "customerId")
    AccountResponse toResponse(AccountBank accountBank);
}
