package com.example.backend.repos;

import com.example.backend.entities.AccountOperation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountOperationRepo extends JpaRepository<AccountOperation, Long> {
}
