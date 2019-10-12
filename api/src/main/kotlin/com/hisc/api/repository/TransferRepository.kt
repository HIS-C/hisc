package com.hisc.api.repository


import com.hisc.api.entity.Transfer
import com.hisc.api.entity.TransferId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TransferRepository : JpaRepository<Transfer, TransferId> {
}
