package com.minorjava.week5.mapper;

import com.minorjava.week5.dto.BankAccountDto;
import com.minorjava.week5.model.BankAccount;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Mapper {
    public BankAccountDto toBankAccountDto(BankAccount bankAccount) {
        BankAccountDto bankAccountDto = new BankAccountDto();
        bankAccountDto.setId(bankAccount.getId());
        bankAccountDto.setBalance(bankAccount.getBalance());
        bankAccountDto.setIBANNumber(bankAccount.getIBANNumber());
        bankAccountDto.setStatus(bankAccount.getStatus());
        return bankAccountDto;
    }

    public List<BankAccountDto> toBankAccountDto(List<BankAccount> bankAccounts) {
        List<BankAccountDto> bankAccountDtos = new ArrayList<BankAccountDto>();
        for (var bankAccount : bankAccounts
        ) {
            bankAccountDtos.add(toBankAccountDto(bankAccount));
        }
        return bankAccountDtos;
    }
}
