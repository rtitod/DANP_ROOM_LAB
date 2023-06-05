package com.example.danp2023room.model

import com.example.danp2023room.entities.Curso
import com.example.danp2023room.entities.CursosConEstudiantes
import com.example.danp2023room.entities.Estudiante
import com.example.danp2023room.entities.EstudiantesConCursos
import com.example.danp2023room.entities.Matriculas

class Repository(private val appDatabase: AppDatabase) {

    suspend fun getAllStudents(): List<Estudiante> {
        return appDatabase.estudianteDao().getAll()
    }

    suspend fun getAllCourses(): List<Curso> {
        return appDatabase.cursoDao().getAll()
    }

    suspend fun obtenerEstudiantesConCursos(): List<EstudiantesConCursos>{
        return appDatabase.estudianteDao().obtenerEstudiantesConCursos()
    }

    suspend fun obtenerCursosConEstudiantes(): List<CursosConEstudiantes>{
        return appDatabase.cursoDao().obtenerCursosConEstudiantes()
    }

    suspend fun insertarEstudiantes(Estudiante: List<Estudiante>){
        return appDatabase.estudianteDao().insert(Estudiante)
    }

    suspend fun insertarCursos(Curso: List<Curso>){
        return appDatabase.cursoDao().insert(Curso)
    }

    suspend fun insertMatricula(matricula: Matriculas){
        appDatabase.cursoDao().insertMatricula(matricula)
    }

}