package com.hisc.api.service.impl

import com.hisc.api.entity.Patient
import com.hisc.api.repository.PatientRepository
import com.hisc.api.service.HospitalService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(rollbackFor = [Exception::class])
class HospitalServiceImpl(val patientRepository: PatientRepository) : HospitalService {
    override fun getPatients(): List<Patient> {
        return patientRepository.findAll()
    }
}