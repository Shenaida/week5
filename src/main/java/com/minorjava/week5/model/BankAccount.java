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
    private Long id;

    @NotNull
    @Column(name="IBANNUMBER")
    private String IBANNumber;

    @Min(8)
    @Column(name="ACCOUNTNUMBER")
    private Long accountNumber;

    @NotNull
    @Column(name="BALANCE")
    private double balance;

    @Column(name="ACOUNTSTATUS")
    @Enumerated(value = EnumType.STRING)
    private AccountStatus status = AccountStatus.ACTIVE;

    public BankAccount() {
    }

    public BankAccount(Long id, @NotNull String IBANNumber, @NotBlank(message = "AccountNumber is mandatory") @Min(8) Long accountNumber, @NotNull double balance, AccountStatus status) {
        this.id = id;
        this.IBANNumber = IBANNumber;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.status = status;
    }

    public BankAccount(@NotNull String IBANNumber, @NotBlank(message = "AccountNumber is mandatory") @Min(8) Long accountNumber, @NotNull double balance) {
        this.IBANNumber = IBANNumber;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIBANNumber() {
        return IBANNumber;
    }

    public void setIBANNumber(String IBANNumber) {
        this.IBANNumber = IBANNumber;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
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
