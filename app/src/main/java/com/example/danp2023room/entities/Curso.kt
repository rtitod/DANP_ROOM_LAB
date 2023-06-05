package com.example.danp2023room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Curso")
data class Curso(
    @PrimaryKey(autoGenerate = true)
    val CursoId: Int,

    @ColumnInfo(name="nombre")
    val Nombre: String
)