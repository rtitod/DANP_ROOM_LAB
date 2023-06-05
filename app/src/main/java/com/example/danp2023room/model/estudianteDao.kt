package com.example.danp2023room.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.danp2023room.entities.Estudiante
import com.example.danp2023room.entities.EstudiantesConCursos

@Dao
interface estudianteDao {
    @Query("select * from Estudiante")
    suspend fun getAll(): List<Estudiante>

    @Transaction
    @Query("select * from Estudiante")
    suspend fun obtenerEstudiantesConCursos(): List<EstudiantesConCursos>

    @Insert
    suspend fun insert(Estudiante: Estudiante)

    @Insert
    suspend fun insert(Estudiante: List<Estudiante>)

    @Delete
    suspend fun delete(Estudiante: Estudiante)

}