package com.myapp.georgewannabe.services;

import com.myapp.georgewannabe.dtos.AccountInDTO;
import com.myapp.georgewannabe.models.Account;
import com.myapp.georgewannabe.models.AccountType;
import com.myapp.georgewannabe.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor

public class SavingAccountService implements AccountService {
    private final AccountRepository accountRepository;


    @Override
    public Long createAccount(AccountInDTO accountInDTO) {
        Account newAccount = Account.builder()
                .name(accountInDTO.getName())
                .balance(0.0)
                .ownerId(accountInDTO.getOwnerId())
                .type(AccountType.SAVINGS)
                .build();
        accountRepository.save(newAccount);
        return newAccount.getId();
    }

    @Override
    public Long deleteAccount(Long id) {
      accountRepository.deleteById(id);
        return id;
    }

    @Override
    public Long deposit(Long id, Double amount) {
        Account account=accountRepository.findById(id).get();
        account.setBalance(account.getBalance()+amount);
        accountRepository.save(account);
        return id;
    }

    @Override
    public Long withdraw(Long id, Double amount) {
        Account account=accountRepository.findById(id).get();
        account.setBalance(account.getBalance()-amount);
        accountRepository.save(account);
        return id;
    }

    @Override
    public Double getBalance(Long id) {
        Account account=accountRepository.findById(id).get();
        return account.getBalance();
    }

    @Override
    public Double doInterest(Long id) {
        Account account=accountRepository.findById(id).get();
        account.setBalance(account.getBalance()*1.01);
        accountRepository.save(account);
        return account.getBalance();
    }
}
