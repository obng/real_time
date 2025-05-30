package com.kiu.real_time.function.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/owner/notification")
public class OwnerNotificationController {
    private static final ConcurrentHashMap<Long, SseEmitter> emitters = new ConcurrentHashMap<>();

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/{ownerId}")
    public List<NotificationDto> getNotifications(@PathVariable("ownerId") Long ownerId) {
        return notificationService.getNotificationsForOwner(ownerId);
    }

    @GetMapping(value = "/{ownerId}/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe(@PathVariable("ownerId") Long ownerId) {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        emitters.put(ownerId, emitter);

        emitter.onCompletion(() -> emitters.remove(ownerId));
        emitter.onTimeout(() -> emitters.remove(ownerId));
        emitter.onError((e) -> emitters.remove(ownerId));

        try {
            emitter.send(SseEmitter.event().name("ping").data("connected"));
        } catch (Exception e) {
            emitters.remove(ownerId);
        }
        return emitter;
    }

    public void sendNotification(Long ownerId, String message, String type) {
        notificationService.addNotification(ownerId, message, type);
        SseEmitter emitter = emitters.get(ownerId);
        if (emitter != null) {
            try {
                List<NotificationDto> list = notificationService.getNotificationsForOwner(ownerId);
                NotificationDto last = list.get(list.size() - 1);
                emitter.send(SseEmitter.event().name("application").data(last));
            } catch (Exception e) {
                emitters.remove(ownerId);
            }
        }
    }
}
