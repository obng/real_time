package com.kiu.real_time.Evaluation.dto;


public class ScoreSummary {
    private String workerName;
    private int sincerityScore;
    private int lateScore;
    private int absentScore;

    // 기본 생성자
    public ScoreSummary() {}

    // 생성자
    public ScoreSummary(String workerName, int sincerityScore, int lateScore, int absentScore) {
        this.workerName = workerName;
        this.sincerityScore = sincerityScore;
        this.lateScore = lateScore;
        this.absentScore = absentScore;
    }

    // Getter와 Setter
    public String getWorkerName() {
        return workerName;
    }

    public void setWorkerName(String workerName) {
        this.workerName = workerName;
    }

    public int getSincerityScore() {
        return sincerityScore;
    }

    public void setSincerityScore(int sincerityScore) {
        this.sincerityScore = sincerityScore;
    }

    public int getLateScore() {
        return lateScore;
    }

    public void setLateScore(int lateScore) {
        this.lateScore = lateScore;
    }

    public int getAbsentScore() {
        return absentScore;
    }

    public void setAbsentScore(int absentScore) {
        this.absentScore = absentScore;
    }
}
