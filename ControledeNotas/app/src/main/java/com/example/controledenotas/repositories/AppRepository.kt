package com.example.controledenotas.repositories

import androidx.annotation.WorkerThread
import com.example.controledenotas.DAOs.AppDAO
import com.example.controledenotas.entities.Student
import com.example.controledenotas.entities.StudentClassrooms
import kotlinx.coroutines.flow.Flow

class AppRepository(private val appDao: AppDAO) {

    val allStudents: Flow<List<Student>> = appDao.getAllStudents()
    val getAllClassrooms: Flow<List<StudentClassrooms>> = appDao.getAllClassrooms()
    var getAllClassroomsSync: List<StudentClassrooms> = appDao.getAllClassroomsSync()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(student: Student) {
        appDao.insertStudent(student)
    }
    suspend fun delete(student: Student){
        appDao.deleteStudent(student)
    }
    suspend fun findStudentById(id: Int){
        appDao.findById(id)
    }

    suspend fun insertClassroom(classroom: StudentClassrooms){
        appDao.insertClassroom(classroom)
    }
    suspend fun deleteClassroom(classroom: StudentClassrooms){
        appDao.deleteClassroom(classroom)
    }
    suspend fun findClassroomById(id: Int){
        appDao.getClassroomWithStudents(id)
    }
}