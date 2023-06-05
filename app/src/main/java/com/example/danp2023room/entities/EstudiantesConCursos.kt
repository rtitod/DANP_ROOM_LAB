package com.example.danp2023room.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class EstudiantesConCursos(
    @Embedded val estudiante: Estudiante,
    @Relation(
        parentColumn = "EstudianteId",
        entityColumn = "CursoId",
        associateBy = Junction(Matriculas::class)
    )
    val cursos: List<Curso>
)
