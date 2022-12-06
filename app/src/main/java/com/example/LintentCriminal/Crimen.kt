package com.example.LintentCriminal
import java.util.Date
import java.util.UUID
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Crimen(
    @PrimaryKey val  id: UUID,
    val title : String,
    val date:Date,
    val isSolved: Boolean,
    val suspect: String ="",
    val photoFileName: String? = null


)
