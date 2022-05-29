package com.splenda.epi.repository;


import com.splenda.epi.entities.core.PublicBusinessUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicBusinessUnitRepository extends JpaRepository<PublicBusinessUnit, Long> {
}
