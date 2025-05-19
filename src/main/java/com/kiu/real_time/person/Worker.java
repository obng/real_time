package com.kiu.real_time.person;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "worker")
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(length = 10)
    private String gender;

    @Column(name = "contact_number", length = 20)
    private String contactNumber;
    
    private BigDecimal rating; // 평가 점수

}