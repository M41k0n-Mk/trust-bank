package me.m41k0n.Controller;

import me.m41k0n.model.Account;
import me.m41k0n.model.dto.AccountResponseDTO;
import me.m41k0n.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping
    public AccountResponseDTO createAccount(@RequestBody Account account) {
        return accountService.saveAccount(account);
    }

    @PutMapping
    public void updateAccountById(@RequestBody Account account) {
        accountService.updateAccountById(account);
    }

    @GetMapping("/{id}")
    public AccountResponseDTO getAccountById(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
    }
}