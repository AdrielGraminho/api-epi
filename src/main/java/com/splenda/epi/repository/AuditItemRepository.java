package com.splenda.epi.repository;

import com.splenda.epi.entities.core.AuditItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditItemRepository extends JpaRepository<AuditItem, Long> {
}
