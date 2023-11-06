package com.myapp.georgewannabe.services;

import com.myapp.georgewannabe.dtos.AccountInDTO;
import com.myapp.georgewannabe.models.GeorgeException;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    public Long createAccount(AccountInDTO accountInDTO);
    public Long deleteAccount(Long id) throws GeorgeException;
    public Long deposit(Long id, Double amount) throws GeorgeException;
    public Long withdraw(Long id, Double amount) throws GeorgeException;
    public Double getBalance(Long id) throws GeorgeException;
    public Double doInterest(Long id);




}
