package com.tttn.ThucTapTotNghiep.accountservice.repository;

import com.tttn.ThucTapTotNghiep.accountservice.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
    Account findByEmail(String email);
    @Query("SELECT userId FROM Account WHERE email = :email")
    int findUserIdByEmail(@Param("email") String email);

}
