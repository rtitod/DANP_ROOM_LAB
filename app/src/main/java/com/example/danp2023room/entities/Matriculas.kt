package com.example.danp2023room.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(primaryKeys = ["EstudianteId","CursoId"])
data class Matriculas(
    val EstudianteId: Int,
    val CursoId: Int
)
