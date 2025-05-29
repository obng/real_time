package com.kiu.real_time.function.evaluation;

public class EvaluationRequest {


    private int sincerityDelta;
    private int lateDelta;
    private int absentDelta;

    public EvaluationRequest() {}

    public EvaluationRequest( int sincerityDelta, int lateDelta, int absentDelta) {

        this.sincerityDelta = sincerityDelta;
        this.lateDelta = lateDelta;
        this.absentDelta = absentDelta;
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
