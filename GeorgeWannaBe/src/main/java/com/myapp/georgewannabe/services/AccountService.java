package com.myapp.georgewannabe.services;

import com.myapp.georgewannabe.dtos.AccountInDTO;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    public Long createAccount(AccountInDTO accountInDTO);
    public Long deleteAccount(Long id);
    public Long deposit(Long id, Double amount);
    public Long withdraw(Long id, Double amount);
    public Double getBalance(Long id);
    public Double doInterest(Long id);




}
