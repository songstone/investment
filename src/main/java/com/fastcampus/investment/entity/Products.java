package com.fastcampus.investment.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "PRODUCTS")
@Entity
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "TOTAL_INVESTING_AMOUNT")
    private Long totalInvestingAmount;

    @Column(name = "INVESTED_AMOUNT")
    @ColumnDefault("0")
    private Long investedAmount;

    @Column(name = "INVESTED_COUNT")
    @ColumnDefault("0")
    private Integer investedCount;

    @Column(name = "STARTED_AT")
    private LocalDate startedAt;

    @Column(name = "FINISHED_AT")
    private LocalDate finishedAt;
}
