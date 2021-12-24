package com.fastcampus.investment.repository;

import com.fastcampus.investment.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ProductRepository extends JpaRepository<Products, Long> {
    List<Products> findByStartedAtLessThanAndFinishedAtGreaterThan(LocalDate curDate1, LocalDate curDate2);
}
