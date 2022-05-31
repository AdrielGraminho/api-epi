package com.splenda.epi.repository;


import com.splenda.epi.entities.core.PublicBusinessUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PublicBusinessUnitRepository extends JpaRepository<PublicBusinessUnit, Long> {
    @Query(value = "select bu.* from public.business_unit bu " +
            " inner join human_resources_ms.employee e on e.business_unit_id = bu.id_business_unit " +
            " inner join human_resources_ms.epi_user eu on eu.id_employee = e.id " +
            " inner join human_resources_ms.epi_role er on er.id_role = eu.id_role " +
            " where er.name = 'bu' and eu.user_name = :username " +
            " order by bu.id_business_unit " +
            " limit 1 ", nativeQuery = true)
    List<PublicBusinessUnit> findByUserName(String username);
}
