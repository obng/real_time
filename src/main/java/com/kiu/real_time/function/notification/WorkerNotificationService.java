package com.kiu.real_time.function.notification;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WorkerNotificationService {
    private final Map<Long, List<NotificationDto>> notificationStore = new HashMap<>();

    public void addNotification(Long workerId, String message, String type) {
        NotificationDto notification = new NotificationDto(
                System.currentTimeMillis(),
                message,
                type,
                LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        );
        notificationStore.computeIfAbsent(workerId, k -> new ArrayList<>()).add(notification);
    }

    public List<NotificationDto> getNotificationsForWorker(Long workerId) {
        return notificationStore.getOrDefault(workerId, new ArrayList<>());
    }
}