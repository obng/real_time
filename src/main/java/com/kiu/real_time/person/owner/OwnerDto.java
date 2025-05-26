package com.kiu.real_time.person.owner;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OwnerDto {
    private Long id;
    private String name;
    private String phoneNumber;
    private BigDecimal rating;

    public static OwnerDto from(Owner owner) {
        return new OwnerDto(owner.getId(), owner.getName(), owner.getPhoneNumber(), owner.getRating());
    }

}
