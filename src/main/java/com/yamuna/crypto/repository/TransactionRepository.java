package com.yamuna.crypto.repository;

import com.yamuna.crypto.dao.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transactions, Long > {
}
