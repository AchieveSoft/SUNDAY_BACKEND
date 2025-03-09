package com.achievesoft.sunday.models

import java.util.Date
import jakarta.persistence.*

@Entity
@Table(name = "pipelines")
data class Pipeline(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val pipelineId: Int = 0,
    var pipelineCode: String = "",
    @Enumerated(EnumType.ORDINAL)
    var state: State = State.Idle,
    var name: String = "",
    var description: String? = null,
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    var stages: List<Stage> = listOf(),
    var createDate: Date? = null,
    var createBy: String = ""
)
