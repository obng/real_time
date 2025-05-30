package com.kiu.real_time.function.notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {
    private Long id;
    private String message;
    private String type;     // "info", "success" 등
    private String dateTime; // ISO 문자열
}
