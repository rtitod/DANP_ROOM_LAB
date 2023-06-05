package com.example.danp2023room.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.danp2023room.entities.Curso
import com.example.danp2023room.entities.Estudiante
import com.example.danp2023room.entities.Matriculas


@Database(
    entities = [Estudiante::class, Curso::class, Matriculas::class],
    version = 8
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun estudianteDao(): estudianteDao
    abstract fun cursoDao(): cursoDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "database-name"
                    ).fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance

                }

                return instance
            }
        }
    }
}