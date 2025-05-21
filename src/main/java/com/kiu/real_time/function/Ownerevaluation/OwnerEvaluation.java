package com.kiu.real_time.function.Ownerevaluation;

import com.kiu.real_time.person.owner.Owner;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class OwnerEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    private int paymentPunctuality;
    @Setter
    private int workEnvironment;
    @Setter
    private int workingHours;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boss_id", nullable = false)
    private Owner owner;

    public OwnerEvaluation() {}

    public OwnerEvaluation(int paymentPunctuality, int workEnvironment, int workingHours, Owner owner) {
        this.paymentPunctuality = paymentPunctuality;
        this.workEnvironment = workEnvironment;
        this.workingHours = workingHours;
        this.owner = owner;
    }
    public int getPaymentPunctuality() {
        return paymentPunctuality;
    }

    public int getWorkEnvironment() {
        return workEnvironment;
    }

    public int getWorkingHours() {
        return workingHours;
    }



}
