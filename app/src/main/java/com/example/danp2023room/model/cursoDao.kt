package com.example.danp2023room.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.danp2023room.entities.Curso
import com.example.danp2023room.entities.CursosConEstudiantes
import com.example.danp2023room.entities.Estudiante
import com.example.danp2023room.entities.Matriculas

@Dao
interface cursoDao {
    @Query("select * from Curso")
    suspend fun getAll(): List<Curso>

    @Transaction
    @Query("select * from Curso")
    suspend fun obtenerCursosConEstudiantes(): List<CursosConEstudiantes>

    @Insert
    suspend fun insert(Curso: Curso)

    @Insert
    suspend fun insert(Curso: List<Curso>)

    @Delete
    suspend fun delete(Curso: Curso)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMatricula(matricula: Matriculas)
}