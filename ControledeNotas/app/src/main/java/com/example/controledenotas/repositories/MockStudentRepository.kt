package com.example.controledenotas.repositories

import com.example.controledenotas.entities.Student
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class MockStudentRepository(initialTestStudents: List<Student>): Repository{
    //lista para simular como se estivesse salvando no banco de dados
    private val allStudents = mutableListOf<Student>()
    private var studentsFlow = flowOf<List<Student>>()

    init {
        allStudents.addAll(initialTestStudents)
        studentsFlow = flowOf<List<Student>>(allStudents)
    }

    override suspend fun insert(student: Student) {
        // toda vez que houver uma inserção vai atualizar os estudos e recriar o flow
        allStudents.add(student)
        studentsFlow = flowOf<List<Student>>(allStudents)
    }

    // retorna o flow
    override fun observeStudents(): Flow<List<Student>> = studentsFlow
    override suspend fun delete(student: Student) {
        allStudents.remove(student)
        studentsFlow = flowOf<List<Student>>(allStudents)
    }

    override suspend fun findStudentById(id: Int) {
        TODO("Not yet implemented")
    }
}