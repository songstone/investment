package com.fastcampus.investment.entity;

import com.fastcampus.investment.type.InvestmentStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "INVESTMENT")
@Entity
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Products product;

    private Long investedAmount;

    @Enumerated(EnumType.STRING)
    private InvestmentStatus investmentStatus;

    private LocalDate investedAt;


}
