package com.splenda.epi.repository;

import com.splenda.epi.entities.core.AuditedItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuditedItemRepository extends JpaRepository<AuditedItem, Long> {
    @Query(value = "select ai from AuditedItem ai" +
            " where ai.auditDate = current_date " +
            " and ai.publicBusinessUnit.idBusinessUnit = :businessUnitId" +
            " and ai.auditItem.idAuditItem = :itemId ")
    Optional<AuditedItem> findByAuditItemBusinessUnitAndDate(Long itemId, Long businessUnitId);
}
