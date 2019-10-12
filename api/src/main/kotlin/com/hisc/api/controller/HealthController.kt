package com.hisc.api.controller

import com.hisc.api.service.HospitalService
import com.hisc.api.vo.ResponseVo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/health")
class HealthController(val hospitalService: HospitalService) {

    @GetMapping
    fun checkHealth(): ResponseVo<String> {
        return ResponseVo("ok")
    }
}
