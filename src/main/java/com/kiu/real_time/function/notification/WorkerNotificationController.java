package com.kiu.real_time.function.notification;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/worker/notification")
public class WorkerNotificationController {
    private static final ConcurrentHashMap<Long, SseEmitter> emitters = new ConcurrentHashMap<>();

    private final WorkerNotificationService notificationService;

    public WorkerNotificationController(WorkerNotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // 알림 목록 조회
    @GetMapping("/{workerId}")
    public List<NotificationDto> getNotifications(@PathVariable("workerId") Long workerId) {
        return notificationService.getNotificationsForWorker(workerId);
    }

    // SSE 구독
    @GetMapping(value = "/{workerId}/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe(@PathVariable("workerId") Long workerId) {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        emitters.put(workerId, emitter);

        emitter.onCompletion(() -> emitters.remove(workerId));
        emitter.onTimeout(() -> emitters.remove(workerId));
        emitter.onError((e) -> emitters.remove(workerId));

        try {
            emitter.send(SseEmitter.event().name("ping").data("connected"));
        } catch (Exception e) {
            emitters.remove(workerId);
        }
        return emitter;
    }

    // 알림 전송 메서드(서비스 등에서 호출)
    public void sendNotification(Long workerId, String message, String type) {
        notificationService.addNotification(workerId, message, type);
        SseEmitter emitter = emitters.get(workerId);
        System.out.println("알림 전송 시도: workerId=" + workerId + ", emitter=" + emitter);
        if (emitter != null) {
            try {
                List<NotificationDto> list = notificationService.getNotificationsForWorker(workerId);
                NotificationDto last = list.get(list.size() - 1);
                emitter.send(SseEmitter.event().name("application").data(last));
                System.out.println("알림 전송 성공!");
            } catch (Exception e) {
                System.out.println("알림 전송 실패: " + e.getMessage());
                emitters.remove(workerId);
            }
        } else {
            System.out.println("SseEmitter가 없습니다. 알바생이 알림 페이지를 보고 있나요?");
        }
    }



}
