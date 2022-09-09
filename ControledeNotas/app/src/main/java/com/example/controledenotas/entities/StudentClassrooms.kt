package com.example.controledenotas.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "classrooms_table")
data class StudentClassrooms(@ColumnInfo var name:String):Serializable {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}