package com.hisc.api.repository


import com.hisc.api.entity.Admission
import com.hisc.api.entity.AdmissionId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AdmissionRepository : JpaRepository<Admission, AdmissionId> {
}
