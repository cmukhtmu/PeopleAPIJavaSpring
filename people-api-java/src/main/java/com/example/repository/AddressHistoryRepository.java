package com.example.repository;

import com.example.model.AddressHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressHistoryRepository  extends JpaRepository<AddressHistory, Long> {
}