package com.splenda.epi.repository;

import com.splenda.epi.entities.core.AuditType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuditTypeRepository extends JpaRepository<AuditType, Long> {
    @Override
    @Query(value = "select at from AuditType at " +
            "order by at.idAuditType")
    List<AuditType> findAll();

}
