package com.example.danp2023room

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.danp2023room.entities.Curso
import com.example.danp2023room.entities.Estudiante
import com.example.danp2023room.entities.Matriculas
import com.example.danp2023room.model.AppDatabase
import com.example.danp2023room.model.Repository
import com.example.danp2023room.ui.theme.DANP2023RoomTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.random.Random


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DANP2023RoomTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context = LocalContext.current
                    val repository = Repository(AppDatabase.getInstance(context.applicationContext))

                    RoomSample(repository)

                }
            }
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun RoomSample(repository: Repository) {
    val TAG: String = "RoomDatabase"
    val scope = rememberCoroutineScope()
    var contenido = remember { mutableStateOf("Deliberadamente Vacio") }
    Column(
        modifier = Modifier.padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        val fillDataOnClick = {
            fillTables(repository, scope)
        }

        val changeContentOnClick: () -> Unit = {
            scope.launch {
                val listOfUsers = fill_list_text(repository, scope)
                contenido.value = listOfUsers.joinToString(", ")
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = fillDataOnClick) {
            Text(text = "Generar Alumnos y Cursos")
        }

        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = changeContentOnClick) {
            Text(text = "Mostrar Listado")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Lista de Cursos y Matriculados\n\n")
        Text(text = contenido.value)
    }
}


fun fillTables(rep: Repository, scope: CoroutineScope) {
    val estudiantes = ArrayList<Estudiante>()
    val cursos = ArrayList<Curso>()

    for (i in 1..10) {
        val studentEntity = Estudiante(0, "Nombre"+ i.toString(), "Apellido" + i.toString())
        estudiantes.add(studentEntity)
    }

    scope.launch {
        rep.insertarEstudiantes(estudiantes)
    }

    for (i in 1..5) {
        val courseIdentity = Curso(0, "Curso_"+ i.toString())
        cursos.add(courseIdentity)
    }

    scope.launch {
        rep.insertarCursos(cursos)
        var ListaCursos = rep.getAllCourses()
        rep.getAllStudents().forEach {
            rep.insertMatricula(Matriculas(it.EstudianteId, Random.nextInt(1,ListaCursos.size)))
        }
    }

}
suspend fun fill_list_text(rep: Repository, scope: CoroutineScope): List<String> {
    return withContext(Dispatchers.IO) {
        val coursesWithStudents = rep.obtenerCursosConEstudiantes()
        coursesWithStudents.map { it.curso.Nombre + " :\t"  + it.estudiantes.toString() + "\n"}
    }
}


