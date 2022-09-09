package com.example.controledenotas

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.controledenotas.listAdapters.StudentClassListAdapter
import com.example.controledenotas.listAdapters.StudentClassroomsClassListAdapter
import com.example.controledenotas.listAdapters.StudentClassroomsListAdapter
import com.example.controledenotas.listAdapters.StudentListAdapter
import com.example.controledenotas.viewModels.*
import com.google.android.material.floatingactionbutton.FloatingActionButton

class StudentClassroomsListActivity : AppCompatActivity() {

    lateinit var adapter: StudentClassroomsListAdapter
    lateinit var adapterClass: StudentClassroomsClassListAdapter
    private val studentClassroomsClassViewModel: StudentClassroomsClassViewModel by viewModels {
        StudentClassroomsClassViewModelFactory((application as StudentsApplication).repository)
    }
    private val studentClassroomsViewModel: StudentClassroomsViewModel by viewModels {
        StudentClassroomsViewModelFactory((application as StudentsApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_classrooms_list)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        adapter = StudentClassroomsListAdapter(this.baseContext, studentClassroomsViewModel)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@StudentClassroomsListActivity, AddStudentClassroomActivity::class.java)
            startActivity(intent)
        }

        studentClassroomsViewModel.allClassrooms.observe(this) { classrooms ->
            classrooms?.let { adapter.submitList((it)) }
        }
    }
}