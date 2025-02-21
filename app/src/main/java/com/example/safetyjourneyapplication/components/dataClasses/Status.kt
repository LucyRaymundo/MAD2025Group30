package com.example.safetyjourneyapplication.components.dataClasses

enum class Status(val id: String, val order: Int) {
    PENDING("pending", 1),
    STARTED("started", 2),
    PAUSED("paused", 3),
    ABANDONED("abandoned", 4),
    COMPLETED("completed", 5)
}
