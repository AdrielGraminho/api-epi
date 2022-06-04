package com.splenda.epi.repository;

import com.splenda.epi.entities.core.Exit;
import com.splenda.epi.entities.dtos.ExitItemDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExitRepository  extends JpaRepository<Exit, Long> {
    @Query(value = " select e.forecastDate from Exit e " +
            " inner join e.employee employe " +
            " left join Received r on r.exit.idExit = e.idExit " +
            " where employe.businessUnit.idBusinessUnit = :idBusinessUnit " +
            " and r.idReceived is null ")
    List<LocalDate> findAllDateNotReceivedByBusinessUnit(Long idBusinessUnit);

    @Query(value = " select ee.id_exit as idExit, ei.description as item  from human_resources_ms.epi_exit ee " +
            " inner join human_resources_ms.epi_item ei on ei.id_item = ee.id_item " +
            " left join human_resources_ms.epi_received er on er.id_exit = ee.id_exit " +
            " inner join human_resources_ms.employee e on e.id = ee.id_employee " +
            " where e.business_unit_id = :idBusinessUnit and ee.forecast_date = :date " +
            " and er.id_received is null ", nativeQuery = true)
    List<ExitItemDTO> findExitItemByBusinessUnitAndDateForecast(Integer idBusinessUnit, LocalDate date);

    @Query(value = " select ee.id_exit as idExit, ei.description as item  from human_resources_ms.epi_exit ee " +
            " inner join human_resources_ms.epi_item ei on ei.id_item = ee.id_item " +
            " left join human_resources_ms.epi_received er on er.id_exit = ee.id_exit " +
            " inner join human_resources_ms.employee e on e.id = ee.id_employee " +
            " where ee.forecast_date = :date " +
            " and er.id_received is null ", nativeQuery = true)
    List<ExitItemDTO> findExitItemDateForecast(LocalDate date);
}
