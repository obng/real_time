package com.kiu.real_time.Evaluation.model;

import jakarta.persistence.*;
//import javax.persistence.*;
//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;

@Entity
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int sincerityDelta;
    private int lateDelta;
    private int absentDelta;

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

    public void setSincerityDelta(int sincerityDelta) {
        this.sincerityDelta = sincerityDelta;
    }

    public int getLateDelta() {
        return lateDelta;
    }

    public void setLateDelta(int lateDelta) {
        this.lateDelta = lateDelta;
    }

    public int getAbsentDelta() {
        return absentDelta;
    }

    public void setAbsentDelta(int absentDelta) {
        this.absentDelta = absentDelta;
    }

    public Worker getWorker() {
        return worker;
    }

    public void setWorker(Worker worker) {
        this.worker = worker;
    }
}