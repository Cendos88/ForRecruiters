package com.myapp.georgewannabe.services;

import com.myapp.georgewannabe.dtos.AccountInDTO;
import com.myapp.georgewannabe.models.Account;
import com.myapp.georgewannabe.models.AccountType;
import com.myapp.georgewannabe.models.GeorgeException;
import com.myapp.georgewannabe.models.User;
import com.myapp.georgewannabe.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
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
                .ownerId(getOwnerId())
                .type(AccountType.SAVINGS)
                .build();
        accountRepository.save(newAccount);
        return newAccount.getId();
    }

    @Override
    public Long deleteAccount(Long id) throws GeorgeException {
        if (notOwnerOfAccount(id)) {
            throw new GeorgeException("You are not the owner of this account");
        }
        accountRepository.deleteById(id);
        return id;
    }

    @Override
    public Long deposit(Long accountId, Double amount) throws GeorgeException {
        Account account = accountRepository.findById(accountId).get();
        if (notOwnerOfAccount(accountId)) {
            throw new GeorgeException("You are not the owner of this account");
        }
        account.setBalance(account.getBalance() + amount);
        accountRepository.save(account);
        return accountId;
    }

    @Override
    public Long withdraw(Long id, Double amount) throws GeorgeException {
        if (notOwnerOfAccount(id)) {
            throw new GeorgeException("You are not the owner of this account");
        }
        Account account = accountRepository.findById(id).get();
        account.setBalance(account.getBalance() - amount);
        accountRepository.save(account);
        return id;
    }

    @Override
    public Double getBalance(Long accountId) throws GeorgeException {
        if (notOwnerOfAccount(accountId)) {
            throw new GeorgeException("You are not the owner of this account");
        }
        Account account = accountRepository.findById(accountId).get();
        return account.getBalance();
    }

    @Override
    public Double doInterest(Long id) {
        Account account = accountRepository.findById(id).get();
        account.setBalance(account.getBalance() * 1.01);
        accountRepository.save(account);
        return account.getBalance();
    }

    private static boolean notOwnerOfAccount(Long accountId) {
        Long ownerId = getOwnerId();
        return !ownerId.equals(accountId);
    }

    private static Long getOwnerId() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getId();
    }
}
