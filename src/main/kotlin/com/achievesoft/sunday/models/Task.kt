package com.achievesoft.sunday.models

import jakarta.persistence.*

@Entity
@Table(name = "tasks")
data class Task(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val taskId: Int = 0,
    val taskCode: String = "",
    val stageCode: String = "",
    val name: String = "",
    @Enumerated(EnumType.ORDINAL)
    val state: State = State.Idle,
    val seq: Int? = null,
    val dependsOn: String? = null,
    val exec: String = ""
)
