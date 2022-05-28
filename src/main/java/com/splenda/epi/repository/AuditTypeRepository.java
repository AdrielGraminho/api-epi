package com.splenda.epi.repository;

import com.splenda.epi.entities.core.AuditType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditTypeRepository extends JpaRepository<AuditType, Long> {
}
