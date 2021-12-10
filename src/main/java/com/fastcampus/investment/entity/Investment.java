package com.fastcampus.investment.entity;

import com.fastcampus.investment.type.InvestmentStatus;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Investment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long investmentId;

    private Long xUserId;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Products product;

    @Enumerated(EnumType.STRING)
    private InvestmentStatus investmentStatus;

    private Timestamp investedAt;


}
