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
    private Double rating;

    public OwnerDto(Long id, String name, String contactNumber, BigDecimal rating) {
    }

    // Owner 엔티티에서 Dto로 변환하는 정적 메서드
    public static OwnerDto from(Owner owner) {
        return new OwnerDto(
                owner.getId(),
                owner.getName(),
                owner.getContactNumber(), // 필드명 주의!
                owner.getRating()
        );
    }
}
