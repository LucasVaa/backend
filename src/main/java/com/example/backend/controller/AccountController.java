package com.example.backend.controller;

import com.example.backend.dao.AccountMapper;
import com.example.backend.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountMapper accountMapper;

    @RequestMapping("/open")
    public Map<String, Integer> open(String accountName, String accountPassword, String customerName,
                    String documentType, String identityNumber, String userId) {
        Account account = new Account();
        account.setAccountName(accountName);
        account.setAccountPassword(accountPassword);
        account.setCustomerName(customerName);
        account.setDocumentType(Integer.valueOf(documentType));
        account.setIdentityNumber(identityNumber);
        account.setUserId(Integer.valueOf(userId));
        account.setBalance(new BigDecimal(0));
        account.setStatus(0);
        int res = 0;
        Map<String, Integer> map = new HashMap<>();
        List<Account> accountListByIdentity = accountMapper.queryByIdentity(identityNumber);
        List<Account> accountListByName = accountMapper.queryByAccountName(accountName);
        if (accountListByName.size() == 1 && accountListByIdentity.size() == 1) {
            map.put("status", 3);
            return map;
        } else if (accountListByName.size() == 1) {
            map.put("status", 2);
            return map;
        } else if (accountListByIdentity.size() == 1) {
            map.put("status", 1);
            return map;
        }
        accountMapper.insert(account);
        map.put("status", 0);
        return map;
    }

    @RequestMapping("/close")
    public Map<String, Integer> close(String accountName, String accountPassword) {
        Map<String, Integer> map = new HashMap<>();
        List<Account> accountList = accountMapper.query(accountName, accountPassword);
        if (accountList.size() == 1) {
            int res = accountMapper.close(accountName, accountPassword);
            map.put("status", res);
        } else {
            map.put("status", 2);
        }
        return map;
    }
}
