package com.kiu.real_time.function.notification;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class NotificationService {
    private final Map<Long, List<NotificationDto>> notificationStore = new HashMap<>();

    public void addNotification(Long ownerId, String message, String type) {
        NotificationDto notification = new NotificationDto(
                System.currentTimeMillis(),
                message,
                type,
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        );
        notificationStore.computeIfAbsent(ownerId, k -> new ArrayList<>()).add(notification);
    }

    public List<NotificationDto> getNotificationsForOwner(Long ownerId) {
        return notificationStore.getOrDefault(ownerId, new ArrayList<>());
    }
}

