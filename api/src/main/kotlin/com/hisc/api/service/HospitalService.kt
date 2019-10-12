package com.hisc.api.service

import com.hisc.api.entity.Patient

interface HospitalService {
    fun getPatients(): List<Patient>
}