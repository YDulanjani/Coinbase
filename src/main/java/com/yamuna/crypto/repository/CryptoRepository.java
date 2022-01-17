package com.yamuna.crypto.repository;

import com.yamuna.crypto.dao.Coins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CryptoRepository extends JpaRepository<Coins, Long> {

}
