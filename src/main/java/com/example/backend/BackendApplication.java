package com.example.backend;

import com.example.backend.entities.AccountOperation;
import com.example.backend.entities.CurrentAccount;
import com.example.backend.entities.Customer;
import com.example.backend.entities.SavingAccount;
import com.example.backend.enums.AccountStatus;
import com.example.backend.enums.OperationType;
import com.example.backend.repos.AccountOperationRepo;
import com.example.backend.repos.BankAccountRepo;
import com.example.backend.repos.CustomerRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    CommandLineRunner start(CustomerRepo customerRepo, BankAccountRepo bankAccountRepo, AccountOperationRepo accountOperationRepo){

        return args -> {
            Stream.of("Hamid", "Meryam", "Ismail").forEach(name-> {
                Customer customer = new Customer();
                customer.setName(name);
                customer.setEmail(name + "@gmail.com");
                customerRepo.save(customer);
            });
            customerRepo.findAll().forEach(customer -> {
                CurrentAccount currentAccount = new CurrentAccount();
                currentAccount.setId(UUID.randomUUID().toString());
                currentAccount.setBalance(Math.random()*90000);
                currentAccount.setCreatedAt(new Date());
                currentAccount.setStatus(AccountStatus.CREATED);
                currentAccount.setCustomer(customer);
                currentAccount.setOverDraft(9000);
                bankAccountRepo.save(currentAccount);

                SavingAccount savingAccount = new SavingAccount();
                savingAccount.setId(UUID.randomUUID().toString());
                savingAccount.setBalance(Math.random()*90000);
                savingAccount.setCreatedAt(new Date());
                savingAccount.setStatus(AccountStatus.CREATED);
                savingAccount.setCustomer(customer);
                savingAccount.setInterestRate(5.5);
                bankAccountRepo.save(savingAccount);
            });

            bankAccountRepo.findAll().forEach(acc -> {
                for(int i = 0; i< 10 ; i++){
                    AccountOperation accountOperation = new AccountOperation();
                    accountOperation.setOperationDate(new Date());
                    accountOperation.setAmount(Math.random()* 120000);
                    accountOperation.setType(Math.random() > 0.5? OperationType.DEBIT: OperationType.CREDIT);
                    accountOperation.setBankAccount(acc);
                    accountOperationRepo.save(accountOperation);
                }
            });
        };
    }

}
