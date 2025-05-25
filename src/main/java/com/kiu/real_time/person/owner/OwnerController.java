package com.kiu.real_time.person.owner;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/owner")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    // 전체 Owner 목록 조회 (DTO 변환)
    @GetMapping
    public List<OwnerDto> listOwners() {
        return ownerService.findAllOwners().stream()
                .map(OwnerDto::from)
                .toList();
    }

    // 단일 Owner 상세 조회 (DTO 변환)
    @GetMapping("/{id}")
    public OwnerDto ownerDetail(@PathVariable Long id) {
        return ownerService.findOwnerById(id)
                .map(OwnerDto::from)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
