package com.hisc.api.repository


import com.hisc.api.entity.Ward
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WardRepository : JpaRepository<Ward, Long> {
}
