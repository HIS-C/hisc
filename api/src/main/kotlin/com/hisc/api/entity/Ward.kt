package com.hisc.api.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "ward")
data class Ward(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var wardId: Long,
    var wardName: String,
    var roomId: String,
    var roomName: String
)

data class WardReqVo(
    var wardId: String,
    var wardName: String,
    var rooms: List<RoomReqVo> = emptyList()
)

data class RoomReqVo(
    var roomId: String,
    var roomName: String
)
