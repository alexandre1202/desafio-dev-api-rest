package br.com.dockbank.bankaccount.domain.entity;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "CUSTOMER")
public class CustomerBank {
    @Id
    @Column(name = "CUSTOMER_ID")
    @SequenceGenerator(name = "CUSTOMER_GENERATOR", sequenceName = "SQ_CUSTOMER")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerId;

    private String customerName;

    @Column
    private String customerCpf;

    @Column
    private LocalDate customerBirth;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "customerBank",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<AccountBank> accountBanks;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "customerBank",
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<Address> addresses;

    public CustomerBank() {}

    public CustomerBank(String customerName, String customerCpf,
        LocalDate customerBirth) {
        this.customerName = customerName;
        this.customerCpf = customerCpf;
        this.customerBirth = customerBirth;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerCpf() {
        return customerCpf;
    }

    public void setCustomerCpf(String customerCpf) {
        this.customerCpf = customerCpf;
    }

    public LocalDate getCustomerBirth() {
        return customerBirth;
    }

    public void setCustomerBirth(LocalDate customerBirth) {
        this.customerBirth = customerBirth;
    }

    public Set<AccountBank> getAccountBanks() {
        return accountBanks;
    }

    public void setAccountBanks(Set<AccountBank> accountBanks) {
        this.accountBanks = accountBanks;
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CustomerBank that = (CustomerBank) o;
        return Objects.equals(customerId, that.customerId) && Objects
            .equals(customerName, that.customerName) && Objects
            .equals(customerCpf, that.customerCpf) && Objects
            .equals(customerBirth, that.customerBirth);
    }

    @Override
    public String toString() {
        return "CustomerBank{" +
            "customerId=" + customerId +
            ", customerName='" + customerName + '\'' +
            ", customerCpf='" + customerCpf + '\'' +
            ", customerBirth=" + customerBirth +
            '}';
    }
}
