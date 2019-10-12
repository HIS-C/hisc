package com.hisc.api.repository

import com.hisc.api.entity.Exam
import com.hisc.api.entity.WardReqVo

interface SearchRepository {
    fun addItems(items: List<Any>)
    fun addWards(wards: List<WardReqVo>)
    fun addExams(exams: List<Exam>)
}
