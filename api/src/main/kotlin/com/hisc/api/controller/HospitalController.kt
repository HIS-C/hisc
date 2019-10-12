package com.hisc.api.controller

import com.hisc.api.entity.Patient
import com.hisc.api.service.HospitalService
import com.hisc.api.vo.ResponseVo
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/hospital")
class HospitalController(val hospitalService: HospitalService) {
    @PostMapping
    fun updateHospital(): ResponseVo<String> {
        return ResponseVo("updateHospital")
    }

    @GetMapping("/patients")
    fun getHospital(): ResponseVo<List<Patient>> {
        return ResponseVo(hospitalService.getPatients())
    }


}
