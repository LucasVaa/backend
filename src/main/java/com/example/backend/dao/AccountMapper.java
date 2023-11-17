package com.example.backend.dao;

import com.example.backend.entity.Account;
import com.example.backend.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AccountMapper {

    int insert(Account account);

    List<Account> queryByIdentity (@Param("identityNumber") String identityNumber);

    List<Account> queryByAccountName (@Param("accountName") String accountName);

    List<Account> query (@Param("accountName") String accountName, @Param("accountPassword") String accountPassword);

    int close (@Param("accountName") String accountName, @Param("accountPassword") String accountPassword);
}
