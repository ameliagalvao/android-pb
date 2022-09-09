package com.example.controledenotas

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.controledenotas.listAdapters.StudentClassListAdapter
import com.example.controledenotas.listAdapters.StudentListAdapter
import com.example.controledenotas.viewModels.StudentClassViewModel
import com.example.controledenotas.viewModels.StudentClassViewModelFactory
import com.example.controledenotas.viewModels.StudentViewModel
import com.example.controledenotas.viewModels.StudentViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class StudentListActivity : AppCompatActivity() {

    lateinit var adapter: StudentListAdapter
    lateinit var adapterClass: StudentClassListAdapter
    private val studentClassViewModel: StudentClassViewModel by viewModels {
        StudentClassViewModelFactory((application as StudentsApplication).repository)
    }
    private val studentViewModel: StudentViewModel by viewModels {
        StudentViewModelFactory((application as StudentsApplication).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_list)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        adapter = StudentListAdapter(this.baseContext, studentViewModel)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val recylerViewClass = findViewById<RecyclerView>(R.id.rvClassList)
        adapterClass = StudentClassListAdapter(this.baseContext, studentClassViewModel)
        recylerViewClass.adapter = adapterClass
        recylerViewClass.layoutManager = LinearLayoutManager(this)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@StudentListActivity, AddStudentActivity::class.java)
            startActivity(intent)
        }

        studentViewModel.allStudents.observe(this) { students ->
            students?.let { adapter.submitList((it)) }
        }
    }
}