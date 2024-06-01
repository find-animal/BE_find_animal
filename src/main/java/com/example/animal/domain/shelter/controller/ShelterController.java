package com.example.animal.domain.shelter.controller;

import com.example.animal.domain.shelter.dto.ShelterSearchCondition;
import com.example.animal.domain.shelter.dto.response.SheltersResponse;
import com.example.animal.domain.shelter.service.ShelterService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${server.api.prefix}/shelter")
public class ShelterController {
    private final ShelterService shelterService;

    @GetMapping("/all")
    public ResponseEntity<Page<SheltersResponse>> findAll(
            @RequestParam("pageNo") int pageNo
    ) {
        ShelterSearchCondition shelterSearchCondition = new ShelterSearchCondition(pageNo);
        Page<SheltersResponse> shelters = shelterService.findAll(shelterSearchCondition);
        return ResponseEntity.ok()
                .body(shelters);
    }
}
