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
    private String contactNumber; // 필드명 수정
    private BigDecimal rating;

    public static OwnerDto from(Owner owner) {
        return new OwnerDto(owner.getId(), owner.getName(), owner.getContactNumber(), owner.getRating());
    }

}
