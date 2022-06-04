package com.splenda.epi.repository;

import com.splenda.epi.entities.core.PpraAndPcmso;
import com.splenda.epi.entities.dtos.PpraAndPcmsoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PpraAndPcmsoRepository extends JpaRepository<PpraAndPcmso, Long> {
    @Query(value = " select pp.* from human_resources_ms.epi_ppra_pcmso pp " +
            " where pp.business_unit = :businessUnitId and pp.expiration_date in ( " +
            " select max(pp2.expiration_date) from human_resources_ms.epi_ppra_pcmso pp2 " +
            " where pp2.business_unit = :businessUnitId and pp2.ppra_and_pcmso_type = pp.ppra_and_pcmso_type " +
            " group by pp2.ppra_and_pcmso_type " +
            ") " +
            "order by pp.expiration_date desc ", nativeQuery = true)
    List<PpraAndPcmso> findLastPpraAndPcmsoByUnitId(Integer businessUnitId);

    @Query(value = "select epp.id_ppra_pcmso as idPpraAndPcmso, epp.ppra_and_pcmso_type as description " +
            " from human_resources_ms.epi_ppra_pcmso epp " +
            " where epp.business_unit = :idBusinessUnit " +
            " and epp.expiration_date = :expirationDate ", nativeQuery = true)
    List<PpraAndPcmsoDTO> findByBusinessUnitAndExpirationDate(Integer idBusinessUnit, LocalDate expirationDate);

    @Query(value = "select epp.id_ppra_pcmso as idPpraAndPcmso, epp.ppra_and_pcmso_type as description " +
            " from human_resources_ms.epi_ppra_pcmso epp " +
            " where epp.expiration_date = :expirationDate ", nativeQuery = true)
    List<PpraAndPcmsoDTO> findByExpirationDate(LocalDate expirationDate);
}
