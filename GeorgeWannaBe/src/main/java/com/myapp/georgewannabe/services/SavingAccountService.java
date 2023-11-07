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
    public Double getBalance(Long accountId) throws GeorgeException {
        if (notOwnerOfAccount(accountId)) {
            throw new GeorgeException("You are not the owner of this account");
        }
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new GeorgeException("Account not found"));
        return account.getBalance();
    }

    @Override
    public Double doInterest(Long id) {
        Account account = accountRepository.findById(id).get();
        account.setBalance(account.getBalance() * 1.01);
        accountRepository.save(account);
        return account.getBalance();
    }

    private  boolean notOwnerOfAccount(Long accountId) throws GeorgeException {
        Long ownerId = getOwnerId();
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new GeorgeException("Account not found"));
        Long accountOwnerId = account.getOwnerId();
        return !ownerId.equals(accountOwnerId);
    }

    private static Long getOwnerId() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getId();
    }
}
