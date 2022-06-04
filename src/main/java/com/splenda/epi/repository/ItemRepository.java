package com.splenda.epi.repository;

import com.splenda.epi.entities.core.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query(value = "select i from Item i " +
            " inner join Exit e on e.item.idItem = i.idItem " +
            " left join Received r on r.exit.idExit = e.idExit " +
            " where r.exit.idExit is null and e.employee.id = :idUser ")
    List<Item> findByIdEmployee(Long idUser);
}
