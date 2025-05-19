package com.kiu.real_time.Evaluation.model;

import com.kiu.real_time.person.Worker;
import jakarta.persistence.*;
import lombok.Setter;
//import javax.persistence.*;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;

@Entity
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private int sincerityDelta;
    @Setter
    private int lateDelta;
    @Setter
    private int absentDelta;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id", nullable = false)
    private Worker worker;

    // 기본 생성자 (JPA용)
    public Evaluation() {
    }

    public Evaluation(int sincerityDelta, int lateDelta, int absentDelta, Worker worker) {
        this.sincerityDelta = sincerityDelta;
        this.lateDelta = lateDelta;
        this.absentDelta = absentDelta;
        this.worker = worker;
    }

    // Getter & Setter
    public Long getId() {
        return id;
    }

    public int getSincerityDelta() {
        return sincerityDelta;
    }

    public int getLateDelta() {
        return lateDelta;
    }

    public int getAbsentDelta() {
        return absentDelta;
    }

    public Worker getWorker() {
        return worker;
    }

}