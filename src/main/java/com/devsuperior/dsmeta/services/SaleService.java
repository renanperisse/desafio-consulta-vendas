package com.devsuperior.dsmeta.services;

import com.devsuperior.dsmeta.dto.SaleDetailsDTO;
import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.dto.SaleSellerTotalDTO;
import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class SaleService {

    @Autowired
    private SaleRepository repository;

    public SaleMinDTO findById(Long id) {
        Optional<Sale> result = repository.findById(id);
        Sale entity = result.get();
        return new SaleMinDTO(entity);
    }

    public Page<SaleDetailsDTO> findByDateAndName(String minDate, String maxDate, String name, Pageable pageable) {
        LocalDate dataMax = (maxDate != null)
                ? LocalDate.parse(maxDate)
                : LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

        LocalDate dataMin = (minDate != null)
                ? LocalDate.parse(minDate)
                : dataMax.minusYears(1L);

        return repository.searchDetails(dataMin, dataMax, name, pageable);
    }

    public List<SaleSellerTotalDTO> findSellerAndTotalAmount(String minDate, String maxDate) {
        LocalDate dataMax = (maxDate != null)
                ? LocalDate.parse(maxDate)
                : LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());

        LocalDate dataMin = (minDate != null)
                ? LocalDate.parse(minDate)
                : dataMax.minusYears(1L);

        return repository.searchSellerTotal(dataMin, dataMax);
    }
}
