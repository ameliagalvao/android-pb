package com.example.controledenotas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import com.example.controledenotas.entities.Student
import com.example.controledenotas.entities.StudentClassrooms
import com.example.controledenotas.viewModels.*

class AddStudentClassroomActivity : AppCompatActivity() {

    private lateinit var name : EditText

    private val studentClassroomsClassViewModel: StudentClassroomsClassViewModel by viewModels {
        StudentClassroomsClassViewModelFactory((application as StudentsApplication).repository)
    }
    private val studentClassroomsViewModel: StudentClassroomsViewModel by viewModels {
        StudentClassroomsViewModelFactory((application as StudentsApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_classroom)
        name = findViewById(R.id.etClassName)
        val button = findViewById<Button>(R.id.btnAddClass)
        button.setOnClickListener{
            val classroom = StudentClassrooms(name.text.toString())
            studentClassroomsViewModel.insert(classroom)
            Toast.makeText(this,"Class added", Toast.LENGTH_SHORT).show()
        }

    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.studentlistsql.REPLY"
    }
}