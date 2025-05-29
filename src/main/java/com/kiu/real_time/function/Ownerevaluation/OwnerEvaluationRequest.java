package com.kiu.real_time.function.Ownerevaluation;

public class OwnerEvaluationRequest {

    ;
    private int paymentPunctuality;
    private int workEnvironment;
    private int workingHours;

    public OwnerEvaluationRequest() {}

    public OwnerEvaluationRequest( int paymentPunctuality, int workEnvironment, int workingHours) {

        this.paymentPunctuality = paymentPunctuality;
        this.workEnvironment = workEnvironment;
        this.workingHours = workingHours;
    }



    public int getPaymentPunctuality() {
        return paymentPunctuality;
    }

    public void setPaymentPunctuality(int paymentPunctuality) {
        this.paymentPunctuality = paymentPunctuality;
    }

    public int getWorkEnvironment() {
        return workEnvironment;
    }

    public void setWorkEnvironment(int workEnvironment) {
        this.workEnvironment = workEnvironment;
    }

    public int getWorkingHours() {
        return workingHours;
    }

    public void setWorkingHours(int workingHours) {
        this.workingHours = workingHours;
    }
}