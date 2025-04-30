package com.kiu.real_time.Evaluation.dto;

public class EvaluationRequest {

    private Long workerId;
    private int sincerityDelta;
    private int lateDelta;
    private int absentDelta;

    public EvaluationRequest() {}

    public EvaluationRequest(Long workerId, int sincerityDelta, int lateDelta, int absentDelta) {
        this.workerId = workerId;
        this.sincerityDelta = sincerityDelta;
        this.lateDelta = lateDelta;
        this.absentDelta = absentDelta;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
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
}