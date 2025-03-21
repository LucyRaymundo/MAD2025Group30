package com.example.safetyjourneyapplication.components.classes

enum class Status(val id: String, val order: Int) {
    PENDING("Pending", 1),
    STARTED("Started", 2),
    PAUSED("Paused", 3),
    ABANDONED("Abandoned", 4),
    COMPLETED("Completed", 5)
}
