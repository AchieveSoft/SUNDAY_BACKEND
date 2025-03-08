package com.achievesoft.sunday.models

import jakarta.persistence.*

@Entity
@Table(name = "stages")
data class Stage(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val stageId: Int = 0,
    val stageCode: String = "",
    val pipelineCode: String = "",
    @Enumerated(EnumType.ORDINAL)
    val state: State = State.Idle,
    val name: String = "",
    @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    val tasks: List<Task>? = null
)
