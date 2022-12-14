package com.example.controledenotas.repositories

import androidx.annotation.WorkerThread
import com.example.controledenotas.DAOs.AppDAO
import com.example.controledenotas.entities.Student
import com.example.controledenotas.entities.StudentClassrooms
import kotlinx.coroutines.flow.Flow

class AppRepository(private val appDao: AppDAO) : Repository {

    val allStudents: Flow<List<Student>> = appDao.getAllStudents()
    val getAllClassrooms: Flow<List<StudentClassrooms>> = appDao.getAllClassrooms()
    var getAllClassroomsSync: List<StudentClassrooms> = appDao.getAllClassroomsSync()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread

    override suspend fun insert(student: Student) {
        appDao.insertStudent(student)
    }

    override fun observeStudents(): Flow<List<Student>> {
        return appDao.getAllStudents()
    }

    override suspend fun delete(student: Student){
        appDao.deleteStudent(student)
    }
    override suspend fun findStudentById(id: Int){
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