package com.example.controledenotas.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.controledenotas.entities.Student
import com.example.controledenotas.entities.StudentClassrooms

data class StudentClassroomsWithStudents(
    // Trata-se da tabela que deve conter múltiplas instâncias da outra tabela.
    @Embedded val studentClassrooms: StudentClassrooms,
    // como vai ser a relação entre elas
    @Relation(
        parentColumn = "id",
        entityColumn = "classroom"
    )
    val studentsList : List<Student>
) {
}