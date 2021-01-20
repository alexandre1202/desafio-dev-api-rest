package br.com.dockbank.bankaccount.domain.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = AccountActiveEnumSerializer.class)
public enum AccountEntityActiveEnum implements AccountBank {
    activate("A", "activate"), deactivate("D", "deactivate");

    private String code;
    private String description;

    AccountEntityActiveEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * method to find CampaignStatus Type by it's code.
     *
     * @param code String
     * @return PartnerOffersEntityActiveEnum
     */
    public static AccountEntityActiveEnum getValue(String code) {
        for (AccountEntityActiveEnum e : AccountEntityActiveEnum.values()) {
            if (e.code.equals(code)) {
                return e;
            }
        }
        return null;// not found
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * br.com.bradesco.next.platform.common.dao.PartnerOffers#getValue()
     */
    @Override
    public String getValue() {
        return this.description;
    }

    @JsonCreator
    public static AccountEntityActiveEnum getAccountEntityActiveEnum(String value) {
        return (AccountEntityActiveEnum) AccountBank
            .getEnumInstance(AccountEntityActiveEnum.values(), value);
    }

}
