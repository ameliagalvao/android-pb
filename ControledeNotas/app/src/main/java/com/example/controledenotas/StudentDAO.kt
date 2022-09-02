package com.example.controledenotas

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDAO {
    @Query("SELECT * FROM student_table ORDER BY name ASC")
    fun getAllStudents(): Flow<List<Student>>

    @Query("SELECT * FROM student_table WHERE student_id = :id")
    suspend fun findById(id: Int) : Student

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(student: Student)

    @Delete(entity = Student::class)
    suspend fun delete(student: Student)

    @Query("DELETE FROM student_table")
    suspend fun deleteAll()

    @Update(entity = Student::class)
    suspend fun update(student: Student)


}