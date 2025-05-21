package com.kiu.real_time.person.worker;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class WorkerController {

    private final WorkerService workerService;

    // 전체 Worker(알바생) 목록 페이지
    @GetMapping("/worker")
    public String listWorkers(Model model) {
        model.addAttribute("workers", workerService.findAllWorkers());
        return "worker/list";
    }

    // 단일 Worker(알바생) 상세 페이지
    @GetMapping("/worker/{id}")
    public String workerDetail(@PathVariable Long id, Model model) {
        workerService.findWorkerById(id).ifPresent(worker -> model.addAttribute("worker", worker));
        return "worker/detail";
    }
}
