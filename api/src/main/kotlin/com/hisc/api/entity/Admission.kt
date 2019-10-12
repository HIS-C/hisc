package com.hisc.api.entity

import com.hisc.api.util.toYmd
import java.io.Serializable
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass

@Entity(name = "admission")
@IdClass(AdmissionId::class)
data class Admission(
    @Id
    var patientId: Long,
    @Id
    var admissionDate: LocalDate,
    var dischargeDate: LocalDate?
) {
    fun toReqVo(): AdmissionReqVo {
        return AdmissionReqVo(
            patientId,
            admissionDate.toYmd(),
            dischargeDate?.toYmd()
        )
    }
}

data class AdmissionId(
    var patientId: Long = 0,
    var admissionDate: LocalDate = LocalDate.now()
) : Serializable

data class AdmissionReqVo(
    var patientId: Long,
    var admissionDate: String,
    var dischargeDate: String? = null
)