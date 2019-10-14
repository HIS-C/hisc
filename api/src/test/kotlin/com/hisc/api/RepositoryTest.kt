package com.hisc.api

import com.hisc.api.config.ElasticsearchConfiguration
import com.hisc.api.entity.RoomReqVo
import com.hisc.api.entity.WardReqVo
import com.hisc.api.repository.AdmissionRepository
import com.hisc.api.repository.ExamRepository
import com.hisc.api.repository.PatientRepository
import com.hisc.api.repository.SearchRepository
import com.hisc.api.repository.TransferRepository
import com.hisc.api.repository.WardRepository
import com.hisc.api.repository.impl.SearchRepositoryImpl
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit4.SpringRunner
import java.time.LocalDate


@RunWith(SpringRunner::class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(SearchRepositoryImpl::class, ElasticsearchConfiguration::class)
class RepositoryTest {
    @Autowired
    lateinit var patientRepository: PatientRepository
    @Autowired
    lateinit var admissionRepository: AdmissionRepository
    @Autowired
    lateinit var transferRepository: TransferRepository
    @Autowired
    lateinit var examRepository: ExamRepository
    @Autowired
    lateinit var wardRepository: WardRepository
    @Autowired
    lateinit var searchRepository: SearchRepository

    @Test
    fun addItems() {
        val admissions = admissionRepository.findAll()
        val exams = examRepository.findAll()
        val map: MutableMap<LocalDate, MutableList<Any>> = mutableMapOf()
        admissions.forEach {
            if (map.containsKey(it.admissionDate)) {
                map[it.admissionDate]?.add(it)
            } else {
                map[it.admissionDate] = mutableListOf(it as Any)
            }
        }
        exams.forEach {
            if (map.containsKey(it.orderDate)) {
                map[it.orderDate]?.add(it)
            } else {
                map[it.orderDate] = mutableListOf(it as Any)
            }
        }
        val sortedMap = map.toSortedMap()
        sortedMap.keys.forEach {
            sortedMap[it]?.let { list ->
                searchRepository.addItems(list)
            }
        }
    }

    @Test
    fun addAdmissions() {
        val admissions = admissionRepository.findAll()
        searchRepository.addItems(admissions)
    }

    @Test
    fun addTrans() {
        val trans = transferRepository.findAll()
        searchRepository.addItems(trans)
    }

    @Test
    fun addExams() {
        val exams = examRepository.findAll()
        println(exams.size)
        searchRepository.addExams(exams)
    }

    @Test
    fun addWards() {
        val list = wardRepository.findAll()
        list.groupBy { it.wardName }.let { map ->
            searchRepository.addWards(map.keys.map { wardName ->
                WardReqVo(
                    wardId = wardName,
                    wardName = map[wardName]?.first()?.wardName ?: "",
                    rooms = map[wardName]?.map {
                        RoomReqVo(it.roomId, it.roomName)
                    } ?: emptyList()
                )
            })
        }
    }
}
