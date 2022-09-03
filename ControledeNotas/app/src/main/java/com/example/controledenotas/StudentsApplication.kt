package com.example.controledenotas

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class StudentsApplication : Application() {
    val applicationScope = CoroutineScope(SupervisorJob())
    val database by lazy { StudentRoomDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { StudentRepository(database.studentDao()) }
}