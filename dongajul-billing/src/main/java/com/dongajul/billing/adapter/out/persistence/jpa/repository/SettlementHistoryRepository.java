package com.dongajul.billing.adapter.out.persistence.jpa.repository;

import com.dongajul.billing.adapter.out.persistence.jpa.entity.SettlementHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SettlementHistoryRepository extends JpaRepository<SettlementHistory, UUID> {
}
