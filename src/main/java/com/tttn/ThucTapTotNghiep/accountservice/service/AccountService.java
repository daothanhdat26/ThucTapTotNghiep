package com.tttn.ThucTapTotNghiep.accountservice.service;

import com.tttn.ThucTapTotNghiep.accountservice.model.Account;
import java.util.List;
import java.util.Optional;

public interface AccountService {
    List<Account> findAll();

//    Optional<Account> findById(Integer integer);
    Account save(Account ac);
    void deleteById(Integer integer);
    Account updateAc(Integer id , Account ac);


}
