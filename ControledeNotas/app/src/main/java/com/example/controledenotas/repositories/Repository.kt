package com.example.controledenotas.repositories

import com.example.controledenotas.entities.Student
import com.example.controledenotas.entities.StudentClassrooms
import kotlinx.coroutines.flow.Flow

interface Repository {
    suspend fun insert(student: Student)
    fun observeStudents(): Flow<List<Student>>
    suspend fun delete(student: Student)
    suspend fun findStudentById(id:Int)
}