package com.hisc.api.entity

import com.hisc.api.util.toYmd
import java.io.Serializable
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass

@Entity(name = "exam")
@IdClass(ExamId::class)
data class Exam(
    @Id
    var patientId: Long,
    var admissionDate: LocalDate,
    @Id
    var orderDate: LocalDate,
    @Id
    var orderSno: Long,
    var deptCode: String,
    var orderCode: String,
    var orderName: String,
    var orderExecDate: LocalDate,
    var orderResult: String,
    var infectCode: String? = null,
    var mdro: String? = null
) {
    fun toReqVo(): ExamReqVo {
        return ExamReqVo(
            patientId,
            admissionDate.toYmd(),
            orderDate.toYmd(),
            orderSno,
            deptCode,
            orderCode,
            orderName,
            orderExecDate.toYmd(),
            orderResult,
            infectCode,
            mdro
        )
    }
}

data class ExamId(
    var patientId: Long = 0,
    var orderDate: LocalDate = LocalDate.now(),
    var orderSno: Long = 0
) : Serializable

data class ExamReqVo(
    var patientId: Long,
    var admissionDate: String,
    var orderDate: String,
    var orderSno: Long,
    var deptCode: String,
    var orderCode: String,
    var orderName: String,
    var orderExecDate: String,
    var orderResult: String,
    var infectCode: String? = null,
    var mdro: String? = null
)