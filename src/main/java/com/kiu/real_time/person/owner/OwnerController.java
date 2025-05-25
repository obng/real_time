package com.kiu.real_time.person.owner;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/owner")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class OwnerController {

    private final OwnerService ownerService;

    // 전체 Owner 목록 조회 (최신순 등 필요시 정렬)
    @GetMapping
    public ResponseEntity<List<OwnerDto>> getOwners() {
        List<OwnerDto> owners = ownerService.findAllOwners().stream()
                .map(OwnerDto::from)
                .toList();
        return ResponseEntity.ok(owners);
    }

    // 단일 Owner 상세 조회
    @GetMapping("/{id}")
    public ResponseEntity<OwnerDto> getOwnerById(@PathVariable Long id) {
        return ownerService.findOwnerById(id)
                .map(owner -> ResponseEntity.ok(OwnerDto.from(owner)))
                .orElseThrow(() -> new ResponseStatusException(org.springframework.http.HttpStatus.NOT_FOUND, "해당 Owner를 찾을 수 없습니다."));
    }
}
