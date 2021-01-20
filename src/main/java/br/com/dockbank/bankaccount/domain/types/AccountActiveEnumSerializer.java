package br.com.dockbank.bankaccount.domain.types;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public class AccountActiveEnumSerializer extends
    JsonSerializer<AccountEntityActiveEnum> {

    @Override
    public void serialize(AccountEntityActiveEnum value, JsonGenerator gen,
        SerializerProvider serializers) throws IOException {
        gen.writeString(value.getValue());
    }
}
