package com.hisc.api.entity

import com.hisc.api.util.toYmd
import java.io.Serializable
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass

@Entity(name = "transfer")
@IdClass(TransferId::class)
data class Transfer(
    @Id
    var patientId: Long,
    @Id
    var transferStrDate: LocalDate,
    @Id
    var transferEndDate: LocalDate,
    var deptCode: String? = null,
    var wardId: String? = null,
    var roomId: String? = null,
    var doctorName: String? = null
) {
    fun toReqVo(): TransferReqVo {
        return TransferReqVo(
            patientId,
            transferStrDate.toYmd(),
            transferEndDate.toYmd(),
            deptCode,
            wardId,
            roomId,
            doctorName
        )
    }
}

data class TransferId(
    var patientId: Long = 0,
    var transferStrDate: LocalDate = LocalDate.now(),
    var transferEndDate: LocalDate = LocalDate.now()
) : Serializable

data class TransferReqVo(
    var patientId: Long,
    var transferStrDate: String,
    var transferEndDate: String,
    var deptCode: String? = null,
    var wardId: String? = null,
    var roomId: String? = null,
    var doctorName: String? = null
)