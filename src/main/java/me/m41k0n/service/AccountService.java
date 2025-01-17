package me.m41k0n.service;

import jakarta.transaction.Transactional;
import me.m41k0n.model.Account;
import me.m41k0n.model.Customer;
import me.m41k0n.model.dto.AccountResponseDTO;
import me.m41k0n.repository.AccountRepository;
import me.m41k0n.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public AccountResponseDTO saveAccount(Account account) {
        ifClientExistsSetHimInAccount(account);
        checkForDuplicateAccount(account);
        Account savedAccount = accountRepository.save(account);
        updateCustomerAccounts(savedAccount);
        return convertToDTO(savedAccount);
    }

    public AccountResponseDTO getAccountById(Long id) {
        Account account = accountRepository.findById(id).orElse(null);
        return convertToDTO(account);
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

    private void ifClientExistsSetHimInAccount(Account account) {
        Optional<Customer> existingCustomer = customerRepository.findByCpf(account.getCustomer().getCpf());
        existingCustomer.ifPresent(account::setCustomer);
    }

    private void checkForDuplicateAccount(Account account) {
        Customer customer = account.getCustomer();
        if (customer != null) {
            List<Account> existingAccounts = accountRepository.findByCustomerId(customer.getId());
            for (Account existingAccount : existingAccounts) {
                if (existingAccount.getClass().equals(account.getClass())) {
                    throw new IllegalArgumentException("O cliente já possui uma conta do tipo " + account.getClass().getSimpleName());
                }
            }
        }
    }

    private void updateCustomerAccounts(Account account) {
        Customer customer = account.getCustomer();
        if (customer != null) {
            customer.getAccounts().add(account);
        }
    }

    private AccountResponseDTO convertToDTO(Account account) {
        if (account == null) {
            return null;
        }

        AccountResponseDTO dto = new AccountResponseDTO();
        dto.setType(account.getClass().getSimpleName().toLowerCase().replace("account", ""));
        dto.setCustomer(account.getCustomer());
        return dto;
    }
}