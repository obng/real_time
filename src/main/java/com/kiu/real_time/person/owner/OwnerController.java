package com.kiu.real_time.person.owner;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    // 전체 Owner 목록 페이지
    @GetMapping("/owners")
    public String listOwners(Model model) {
        model.addAttribute("owners", ownerService.findAllOwners());
        return "owners/list"; // src/main/resources/templates/owners/list.html
    }

    // 단일 Owner 상세 페이지
    @GetMapping("/owners/{id}")
    public String ownerDetail(@PathVariable Long id, Model model) {
        ownerService.findOwnerById(id).ifPresent(owner -> model.addAttribute("owner", owner));
        return "owners/detail"; // src/main/resources/templates/owners/detail.html
    }
}
