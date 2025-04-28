package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.dto.SaleDetailsDTO;
import com.devsuperior.dsmeta.dto.SaleSellerTotalDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT new com.devsuperior.dsmeta.dto.SaleDetailsDTO(obj.id, obj.amount, obj.date, sel.name) "
            + "FROM Sale obj "
            + "INNER JOIN obj.seller sel "
            + "WHERE obj.date BETWEEN :dataMin AND :dataMax "
            + "AND UPPER(sel.name) LIKE UPPER(CONCAT('%', :name, '%'))")
    Page<SaleDetailsDTO> searchDetails(LocalDate dataMin, LocalDate dataMax, String name, Pageable pageable);

    @Query("SELECT new com.devsuperior.dsmeta.dto.SaleSellerTotalDTO(sel.name, SUM(obj.amount)) "
            + "FROM Sale obj "
            + "INNER JOIN obj.seller sel "
            + "WHERE obj.date BETWEEN :dataMin AND :dataMax " +
            "GROUP BY sel.name")
    List<SaleSellerTotalDTO> searchSellerTotal(LocalDate dataMin, LocalDate dataMax);
}
