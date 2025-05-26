package com.kiu.real_time.person.owner;

import com.kiu.real_time.job_postings.JobPosting;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "owner")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50)
    private String name;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    private BigDecimal rating;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<JobPosting> jobs;
}
