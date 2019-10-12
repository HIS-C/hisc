package com.hisc.api.entity

import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "patient")
data class Patient (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var patientId: Long,
    var patientName: String,
    var gender: String,
    var birthDate: LocalDate
)