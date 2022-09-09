package com.example.controledenotas.entities

import androidx.room.*
import java.io.Serializable

// se não especificarmos o nome no parêntesis ele vai dar o mesmo nome da classe.
@Entity(tableName = "student_table")
data class Student(@PrimaryKey(autoGenerate = true) @ColumnInfo(name = "student_id") var studentId: Int,
                   @ColumnInfo(name = "name") var name: String,
                   @ColumnInfo(name = "level") var level: String,
                   @ColumnInfo(name = "score_one") var scoreOne: Double = -1.0,
                   @ColumnInfo(name = "score_two") var scoreTwo: Double = -1.0,
                   @ColumnInfo(name = "classroom") var classroom:Int = 0) : Serializable {

    var totalScore: Double
        get(){ return getStudentTotalScore(this)}
        set(value) {-1.0}

    fun getStudentTotalScore(student: Student = this): Double {
        var totalScore: Double
        if (student.scoreOne != -1.0 && student.scoreTwo != -1.0) {
            totalScore = (student.scoreOne + student.scoreTwo) / 2
        } else {
            if (student.scoreOne == -1.0) {
                totalScore = student.scoreTwo
            } else {
                totalScore = student.scoreOne
            }
        }
        return totalScore
    }
}


