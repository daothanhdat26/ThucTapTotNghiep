package com.tttn.ThucTapTotNghiep.accountservice.repository;

import com.tttn.ThucTapTotNghiep.accountservice.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account,Integer> {

}
