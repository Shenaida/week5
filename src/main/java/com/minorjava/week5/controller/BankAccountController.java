package com.minorjava.week5.controller;

import com.minorjava.week5.dto.BankAccountDto;
import com.minorjava.week5.mapper.Mapper;
import com.minorjava.week5.model.BankAccount;
import com.minorjava.week5.service.BankAccountService;
import com.minorjava.week5.util.FieldErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/bankAccount")
public class BankAccountController {

    @Autowired
    private final BankAccountService bankAccountService;
    private final Mapper mapper;

    public BankAccountController(BankAccountService bankAccountService, Mapper mapper) {
        this.bankAccountService = bankAccountService;
        this.mapper = mapper;
    }
    @PostMapping()
    public ResponseEntity create(@Valid @RequestBody BankAccount account) throws ValidationException {
        {
            return new ResponseEntity(bankAccountService.save(account), HttpStatus.OK);
        }
    }

    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    List<FieldErrorMessage> exceptionHandler(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<FieldErrorMessage> fieldErrorMessages = fieldErrors.stream().map(fieldError -> new FieldErrorMessage(fieldError.getField(), fieldError.getDefaultMessage())).collect(Collectors.toList());
        return fieldErrorMessages;
    }

    @GetMapping()
    public ResponseEntity<BankAccountDto> read() {
        if (!bankAccountService.findAll().isEmpty()) {
            return new ResponseEntity(mapper.toBankAccountDto(bankAccountService.findAll()), HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping()
    public ResponseEntity<BankAccount> update(@RequestBody BankAccount account){
        if(bankAccountService.findById(account.getId()).isPresent()){
            return new ResponseEntity(bankAccountService.save(account), HttpStatus.OK);
        } else {
            return new ResponseEntity(account, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) throws ValidationException {
        if(bankAccountService.findById(id).isPresent()){
            bankAccountService.delete(id);
        } else {
            throw new ValidationException("Bankaccount cannot be deleted");
        }
    }
    @GetMapping("/{id}")
    public BankAccountDto findById(@PathVariable Long id)throws ValidationException{
        Optional<BankAccount> optionalBankAccount= bankAccountService.findById(id);
        if(optionalBankAccount.isPresent()){
            return mapper.toBankAccountDto(optionalBankAccount.get());
        }

        throw new ValidationException("Bankaccount not found");
    }


}
