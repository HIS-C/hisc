package com.hisc.api.repository


import com.hisc.api.entity.Exam
import com.hisc.api.entity.ExamId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ExamRepository : JpaRepository<Exam, ExamId> {
}
