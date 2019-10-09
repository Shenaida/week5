package com.minorjava.week5.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BANKACCOUNT_ID")
    private Long Id;

    @NotNull
    @Column(name="IBANNUMBER")
    private String IBANnumber;

    @Min(8)
    @Column(name="ACCOUNTNUMBER")
    private Long AccountNumber;

    @NotNull
    @Column(name="BALANCE")
    private double balance;

    @Column(name="ACOUNTSTATUS")
    @Enumerated(value = EnumType.STRING)
    private AccountStatus status = AccountStatus.ACTIVE;

    public enum AccountStatus {
        ACTIVE,
        NONACTIVE
    }

    public BankAccount() {
    }

    public BankAccount(Long id, @NotNull String IBANnumber, @NotBlank(message = "AccountNumber is mandatory") @Min(8) Long accountNumber, @NotNull double balance, AccountStatus status) {
        Id = id;
        this.IBANnumber = IBANnumber;
        AccountNumber = accountNumber;
        this.balance = balance;
        this.status = status;
    }

    public BankAccount(@NotNull String IBANnumber, @NotBlank(message = "AccountNumber is mandatory") @Min(8) Long accountNumber, @NotNull double balance) {
        this.IBANnumber = IBANnumber;
        AccountNumber = accountNumber;
        this.balance = balance;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getIBANnumber() {
        return IBANnumber;
    }

    public void setIBANnumber(String IBANnumber) {
        this.IBANnumber = IBANnumber;
    }

    public Long getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        AccountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }
}
