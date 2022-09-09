package com.example.controledenotas

import android.content.Intent
import android.os.Bundle
import android.widget.Button
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

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val studentBtn = findViewById<Button>(R.id.studentsBtn)
        studentBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, StudentListActivity::class.java)
            startActivity(intent)
        }

        val classroomsBtn = findViewById<Button>(R.id.classroomsBtn)
        classroomsBtn.setOnClickListener {
            val intent = Intent(this@MainActivity, StudentClassroomsListActivity::class.java)
            startActivity(intent)
        }
    }
}