package com.kiu.real_time.person.worker;

import com.kiu.real_time.function.application.Application;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.List;

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

    @Column
    private Integer age;

    @Column(name = "contact_number", length = 20)
    private String contactNumber;
    
    private BigDecimal rating; // 평가 점수

    // 알바생이 지원한 공고(지원서) 리스트
    @OneToMany(mappedBy = "worker", fetch = FetchType.LAZY)
    private List<Application> applications;
}