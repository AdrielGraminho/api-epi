package com.splenda.epi.repository;

import com.splenda.epi.entities.core.Received;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceivedRepository extends JpaRepository<Received, Long> {
}
