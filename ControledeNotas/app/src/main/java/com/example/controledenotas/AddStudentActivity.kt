package com.example.controledenotas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.activity.viewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.map
import com.example.controledenotas.entities.Student
import com.example.controledenotas.entities.StudentClassrooms
import com.example.controledenotas.entities.relations.StudentClassroomsWithStudents
import com.example.controledenotas.repositories.AppRepository
import com.example.controledenotas.viewModels.*
import kotlinx.coroutines.flow.*

class AddStudentActivity : AppCompatActivity() {

    private lateinit var name : EditText
    private lateinit var level : Spinner
    private lateinit var  scoreOne : EditText
    private lateinit var scoreTwo : EditText

    private val studentClassViewModel: StudentClassViewModel by viewModels {
        StudentClassViewModelFactory((application as StudentsApplication).repository)
    }
    private val studentViewModel: StudentViewModel by viewModels {
        StudentViewModelFactory((application as StudentsApplication).repository)
    }


    private val studentClassroomsViewModel: StudentClassroomsViewModel by viewModels {
        StudentClassroomsViewModelFactory((application as StudentsApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        // As turmas chegam aqui como flow e não consigo formar um array a partir delas pra entrar como último parâmetro do ArrayAdapter
        var classes = arrayOf(studentClassroomsViewModel.classrooms)
        var classroomsSpinner = findViewById<Spinner>(R.id.classroomsSpinner)
        classroomsSpinner.adapter = ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, classes)


        name = findViewById(R.id.editTextStudentName)
        level = findViewById(R.id.classroomsSpinner)
        scoreOne = findViewById(R.id.editTextDecimalScore1)
        scoreTwo = findViewById(R.id.editTextDecimalScore2)

        val button = findViewById<Button>(R.id.submitButton)
        button.setOnClickListener{
            val student = Student(0,name.text.toString(),level.id.toString(), scoreOne.text.toString().toDouble(), scoreTwo.text.toString().toDouble())
            studentViewModel.insert(student)
            Toast.makeText(this,"adicionado", Toast.LENGTH_SHORT).show()
        }

    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.studentlistsql.REPLY"
    }
}