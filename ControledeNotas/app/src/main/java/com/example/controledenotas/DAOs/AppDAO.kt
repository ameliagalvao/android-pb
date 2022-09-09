package com.example.controledenotas.DAOs

import androidx.room.*
import com.example.controledenotas.entities.Student
import com.example.controledenotas.entities.StudentClassrooms
import com.example.controledenotas.entities.relations.StudentClassroomsWithStudents
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDAO {
    @Query("SELECT * FROM student_table ORDER BY name ASC")
    fun getAllStudents(): Flow<List<Student>>

    @Query("SELECT * FROM classrooms_table ORDER BY name ASC")
    fun getAllClassrooms(): Flow<List<StudentClassrooms>>

    @Query("SELECT * FROM student_table WHERE student_id = :id")
    suspend fun findById(id: Int) : Student

    @Transaction
    @Query("SELECT * FROM classrooms_table WHERE id = :id")
    suspend fun getClassroomWithStudents(id: Int):List<StudentClassroomsWithStudents>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertStudent(student: Student)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertClassroom(classroom: StudentClassrooms)

    @Delete(entity = Student::class)
    suspend fun deleteStudent(student: Student)

    @Delete(entity = StudentClassrooms::class)
    suspend fun deleteClassroom(classroom: StudentClassrooms)

    @Query("DELETE FROM student_table")
    suspend fun deleteAllStudents()

    @Update(entity = Student::class)
    suspend fun updateStudent(student: Student)

    @Update(entity = StudentClassrooms::class)
    suspend fun updateClassroom(classroom: StudentClassrooms)

}