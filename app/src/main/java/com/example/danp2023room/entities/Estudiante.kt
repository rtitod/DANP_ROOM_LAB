package com.example.danp2023room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Estudiante")
data class Estudiante(
    @PrimaryKey(autoGenerate = true)
    val EstudianteId: Int,

    @ColumnInfo(name="nombres")
    val Nombres: String,

    @ColumnInfo(name="apellidos")
    val Apellidos: String
)