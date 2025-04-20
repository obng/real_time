package com.kiu.real_time.Evaluation.model;

//평가(한 구인자의 평가)
// 데이터베이스랑 연결필요

import javax.persistence.*;

@Entity
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 평가 항목들
    private int sincerityDelta;
    private int lateDelta;
    private int absentDelta;

    // 한 명의 Worker(구인자)를 참조하는 외래키
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "worker_id", nullable = false)
    private Worker worker;

    // 기본 생성자 (JPA용)
    public Evaluation() {
    }

    // 생성자 (전체 필드 초기화용)
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
