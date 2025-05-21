package com.kiu.real_time.person.owner;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;

    // 전체 Owner 조회
    public List<Owner> findAllOwners() {
        return ownerRepository.findAll();
    }

    // ID로 Owner 조회
    public Optional<Owner> findOwnerById(Long id) {
        return ownerRepository.findById(id);
    }
}
