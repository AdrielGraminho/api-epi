package com.splenda.epi.repository;

import com.splenda.epi.entities.core.Received;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReceivedRepository extends JpaRepository<Received, Long> {
    @Query(value = "select r from Received r " +
            "where r.exit.idExit = :idExit")
    Optional<Received> findByExit(Long idExit);
}
