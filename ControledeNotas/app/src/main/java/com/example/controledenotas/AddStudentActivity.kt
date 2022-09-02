package com.example.controledenotas

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast

class AddStudentActivity : AppCompatActivity() {

    private lateinit var name : EditText
    private lateinit var level : EditText
    private lateinit var  scoreOne : EditText
    private lateinit var scoreTwo : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)
        name = findViewById(R.id.editTextStudentName)
        level = findViewById(R.id.editTextLevel)
        scoreOne = findViewById(R.id.editTextDecimalScore1)
        scoreTwo = findViewById(R.id.editTextDecimalScore2)
        val button = findViewById<Button>(R.id.submitButton)
        button.setOnClickListener{
            val replyIntent = Intent()
            if (TextUtils.isEmpty(name.text)){
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                val student = Student(0,name.text.toString(),level.text.toString(), scoreOne.text.toString().toDouble(), scoreTwo.text.toString().toDouble())
                replyIntent.putExtra(EXTRA_REPLY, student)
                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.android.studentlistsql.REPLY"
    }
}