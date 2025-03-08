package com.achievesoft.sunday.models

import jakarta.persistence.*
import java.util.Date

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val userId: Int = 0,
    val userCode: String = "",
    val email: String = "",
    val password: String = "",
    val createDate: Date = Date(),
    val createBy: String = "system"
)
