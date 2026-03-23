package com.spring.java.dao.user;

import com.spring.java.dao.model.sql.UserBankDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBankDetailsRepository extends JpaRepository<UserBankDetailsEntity, Long> {
}
