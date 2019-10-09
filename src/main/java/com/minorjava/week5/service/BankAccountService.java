package com.minorjava.week5.service;

import com.minorjava.week5.model.BankAccount;
import com.minorjava.week5.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankAccountService {

    @Autowired
    private final BankAccountRepository bankAccountRepository;

    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public List<BankAccount> findAll() {
            return (List<BankAccount>)bankAccountRepository.findAll();
        }

    public BankAccount save(BankAccount account) {
            return bankAccountRepository.save(account);
        }

    public void delete(Long id) {
        bankAccountRepository.deleteById(id);
        }

    public Optional<BankAccount> findById(Long id) {
            return bankAccountRepository.findById(id);
        }
}