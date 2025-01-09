package me.m41k0n.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import me.m41k0n.model.Account;
import me.m41k0n.repository.AccountRepository;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    public void updateAccountById(Account updatedAccount) {
        Account existingAccount = accountRepository.findById(updatedAccount.getId()).orElse(null);
        if (existingAccount != null) {
            updateAccountFields(existingAccount, updatedAccount);
            accountRepository.save(existingAccount);
        }
    }

    private void updateAccountFields(Account existingAccount, Account updatedAccount) {

    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
}