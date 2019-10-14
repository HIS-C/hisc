package com.hisc.api.repository.impl

import com.hisc.api.config.getDao
import com.hisc.api.entity.Admission
import com.hisc.api.entity.AdmissionReqVo
import com.hisc.api.entity.Exam
import com.hisc.api.entity.ExamReqVo
import com.hisc.api.entity.Patient
import com.hisc.api.entity.PatientReqVo
import com.hisc.api.entity.Transfer
import com.hisc.api.entity.TransferReqVo
import com.hisc.api.entity.WardReqVo
import com.hisc.api.repository.SearchRepository
import org.elasticsearch.client.RestHighLevelClient
import org.springframework.stereotype.Component

@Component
class SearchRepositoryImpl(
    val restHighLevelClient: RestHighLevelClient) : SearchRepository {
    override fun addItems(items: List<Any>) {
        println("total size :: ${items.size}")
        var idx = 0
        items.forEach { item ->
            if (item is Patient) {
                addPatient(item)
            } else if (item is Admission) {
                addAdmission(item)
            } else if (item is Transfer) {
                addTransfer(item)
            } else if (item is Exam) {
                addExam(item)
            }

            if (idx++ == 100) {
                println("added item :: $idx")
            }
        }
    }

    fun addPatient(patient: Patient) {
        restHighLevelClient.getDao(
            indexName = "patient",
            clazz = PatientReqVo::class)
            .index(
                id = "${patient.patientId}",
                obj = patient.toReqVo(),
                create = false
            )
    }

    fun addAdmission(admission: Admission) {
        restHighLevelClient.getDao(
            indexName = "admission",
            clazz = AdmissionReqVo::class)
            .index(
                id = "${admission.patientId}_${admission.admissionDate}",
                obj = admission.toReqVo(),
                create = false
            )
    }

    // transfer
    fun addTransfer(transfer: Transfer) {
        restHighLevelClient.getDao(
            indexName = "transfer",
            clazz = TransferReqVo::class)
            .index(
                id = "${transfer.patientId}_${transfer.transferStrDate}_${transfer.transferEndDate}",
                obj = transfer.toReqVo(),
                create = false
            )
    }

    fun addExam(exam: Exam) {
        restHighLevelClient.getDao(
            indexName = "exam",
            clazz = ExamReqVo::class)
            .index(
                id = "${exam.patientId}_${exam.orderDate}_${exam.orderSno}",
                obj = exam.toReqVo(),
                create = false
            )
    }

    override fun addExams(exams: List<Exam>) {
        restHighLevelClient.getDao(
            indexName = "exam",
            clazz = ExamReqVo::class)
            .bulk(bulkSize = 1000) {
                exams.forEach {
                    index(
                        id = "${it.patientId}_${it.orderDate}_${it.orderSno}",
                        obj = it.toReqVo(),
                        create = false
                    )
                }
            }
    }

    override fun addWards(wards: List<WardReqVo>) {
        restHighLevelClient.getDao(
            indexName = "ward",
            clazz = WardReqVo::class)
            .bulk(bulkSize = 1000) {
                wards.forEach {
                    index(
                        id = "${it.wardId}",
                        obj = it,
                        create = false
                    )
                }
            }
    }

}
