package com.kiu.real_time.function.Ownerevaluation;

public class OwnerEvaluationRequest {

    private Long ownerId;
    private int paymentPunctuality;
    private int workEnvironment;
    private int workingHours;

    public OwnerEvaluationRequest() {}

    public OwnerEvaluationRequest(Long ownerId, int paymentPunctuality, int workEnvironment, int workingHours) {
        this.ownerId = ownerId;
        this.paymentPunctuality = paymentPunctuality;
        this.workEnvironment = workEnvironment;
        this.workingHours = workingHours;
    }

    public Long getWorkerId() {
        return ownerId;
    }

    public int getPaymentPunctuality() {
        return paymentPunctuality; //급여를 재떄 주는 지
    }

    public int getWorkEnvironment() {
        return workEnvironment; //업무 환경
    }

    public int getWorkingHours() {
        return workingHours; //근무 시간을 잘 지키는 지
    }


}
