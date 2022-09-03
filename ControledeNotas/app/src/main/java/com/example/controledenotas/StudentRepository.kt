package com.example.controledenotas

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class StudentRepository(private val studentDao: StudentDAO) {

    val allStudents: Flow<List<Student>> = studentDao.getAllStudents()
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(student: Student) {
        studentDao.insert(student)
    }
    suspend fun delete(student: Student){
        studentDao.delete(student)
    }
    suspend fun findById(id: Int){
        studentDao.findById(id)
    }
}