package com.example.controledenotas.viewModels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.controledenotas.entities.Student
import com.example.controledenotas.getOrAwaitValue
import com.example.controledenotas.repositories.MockStudentRepository
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class StudentViewModelTest {
    private lateinit var studentViewModel: StudentViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp(){
        val initialStudent = listOf(
            Student(100, "Testudo", "Beginner", 7.0, 9.0, 2),
            Student(200, "Testiane", "Advanced", 10.0, 8.5, 1)
        )
        // mock dos dados com o repositório falso
        val factory = StudentViewModelFactory(MockStudentRepository(initialStudent))
        studentViewModel = factory.create(StudentViewModel::class.java)
    }

    @Test
    fun insert_NewStudent_IntoViewModel_DispatchesObserver_OnLiveData(){
        studentViewModel.allStudents.getOrAwaitValue()
        runBlocking {
            studentViewModel.insert(Student(300, "Testinho", "Beginner", 8.0, 8.5, 2))
        }
        val value:List<Student>? = studentViewModel.allStudents.value
        assertThat(value?.last(), (not(nullValue())))
        assertThat(value?.last()?.studentId, (equalTo("300")))

    }

}