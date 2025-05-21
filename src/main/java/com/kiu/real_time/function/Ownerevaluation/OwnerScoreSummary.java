package com.kiu.real_time.function.Ownerevaluation;

public class OwnerScoreSummary {

    private String ownerName;
    private int paymentPunctualityScore;
    private int workEnvironmentScore;
    private int workingHoursScore;

    public OwnerScoreSummary() {}

    public OwnerScoreSummary(String ownerName, int paymentPunctualityScore, int workEnvironmentScore, int workingHoursScore) {
        this.ownerName = ownerName;
        this.paymentPunctualityScore = paymentPunctualityScore;
        this.workEnvironmentScore = workEnvironmentScore;
        this.workingHoursScore = workingHoursScore;
    }



}
