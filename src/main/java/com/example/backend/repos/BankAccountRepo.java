package com.example.backend.repos;

import com.example.backend.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepo extends JpaRepository<BankAccount, String> {
}
