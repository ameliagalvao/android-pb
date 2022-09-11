package com.example.controledenotas

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.controledenotas.DAOs.AppDAO
import com.example.controledenotas.entities.Student
import com.example.controledenotas.entities.StudentClassrooms
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [
    StudentClassrooms::class,
    Student::class
                     ], version = 2, exportSchema = false,
)
abstract class StudentRoomDatabase : RoomDatabase() {

    abstract fun studentDao(): AppDAO

    companion object {

        //Sempre que mudarmos o valor da instance essa mudança ficará imediatamente visivel para
        // todas as threads. Previne raise conditions.
        @Volatile
        private var INSTANCE: StudentRoomDatabase? = null

        val migration_1_2:Migration=object :Migration(1,2){
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE `student_table` ADD COLUMN classroom INTEGER DEFAULT 0 NOT NULL")
                database.execSQL("CREATE TABLE `classrooms_table`(`name`TEXT NOT NULL," +
                        "`id`INTEGER NOT NULL, PRIMARY KEY(`id`))")
            }
        }

        fun getDatabase(context: Context, scope: CoroutineScope): StudentRoomDatabase {
            // o synchronized garante que apenas uma single thread execute o bloco de
            // código a seguir. Isso previne que duas thread queiram criar o mesmo bd
            // ao mesmo tempo. Queremos um singletton.
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StudentRoomDatabase::class.java,
                    "student_database"
                ).allowMainThreadQueries()
                    .addMigrations(migration_1_2)
                .addCallback(StudentDatabaseCallback(scope))
                .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }

    private class StudentDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.studentDao())
                }
            }
        }

        suspend fun populateDatabase(appDao: AppDAO) {
            // Delete all content here.
            appDao.deleteAllStudents()

            // Add sample words.
            var student = Student(0,"Fulano", "advanced one")
            appDao.insertStudent(student)
            student = Student(1, "Ciclano", "advanced one")
            appDao.insertStudent(student)
            var classroom = StudentClassrooms("Advanced One")
            appDao.insertClassroom(classroom)
        }
    }
}