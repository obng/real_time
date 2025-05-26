package com.kiu.real_time.person.worker;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkerDto {
    private Long id;
    private String name;
    private String gender;
    private Integer age;
    private String phoneNumber;
    private BigDecimal rating;

    public static WorkerDto from(Worker worker) {
        return new WorkerDto(worker.getId(), worker.getName(), worker.getGender(), worker.getAge(), worker.getPhoneNumber(), worker.getRating());
    }
}
