package com.dongajul.billing.adapter.out.persistence.jpa.repository;

import com.dongajul.billing.adapter.out.persistence.jpa.entity.SettlementAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SettlementAccountRepository extends JpaRepository<SettlementAccount, UUID> {
}
