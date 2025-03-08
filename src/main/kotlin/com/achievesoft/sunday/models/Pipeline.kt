package com.achievesoft.sunday.models

import java.util.Date
import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "pipelines")
data class Pipeline(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val pipelineId: Int = 0,
    val pipelineCode: String = "",
    @Enumerated(EnumType.ORDINAL)
    val state: State = State.Idle,
    val name: String = "",
    val description: String? = null,
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    val stages: List<Stage> = listOf(),
    val createDate: Date? = null,
    val createBy: String = ""
)
