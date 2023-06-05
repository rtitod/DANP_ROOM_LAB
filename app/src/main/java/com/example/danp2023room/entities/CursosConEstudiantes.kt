package com.example.danp2023room.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class CursosConEstudiantes(
    @Embedded val curso: Curso,
    @Relation(
        parentColumn = "CursoId",
        entityColumn = "EstudianteId",
        associateBy = Junction(Matriculas::class)
    )
    val estudiantes: List<Estudiante>
)
