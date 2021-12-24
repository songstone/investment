package com.fastcampus.investment.repository;

import com.fastcampus.investment.entity.Investment;
import com.fastcampus.investment.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InvestmentRepository extends JpaRepository<Investment, Long> {
    List<Investment> findByUserIdEqualsOrderByIdAsc(Long userId);

    Optional<Investment> findByUserIdEqualsAndProductEquals(Long userId, Products product);
}
