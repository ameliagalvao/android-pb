package com.example.controledenotas.viewModels

import androidx.lifecycle.*
import com.example.controledenotas.entities.StudentClassrooms
import com.example.controledenotas.repositories.AppRepository
import kotlinx.coroutines.launch

class StudentClassroomsClassViewModel (private val repository: AppRepository) : ViewModel() {
    val allStudentClasses: LiveData<List<StudentClassrooms>> = repository.getAllClassrooms.asLiveData()
    fun insert(studentClassrooms: StudentClassrooms) = viewModelScope.launch {
        repository.insertClassroom(studentClassrooms)
    }
    fun delete(studentClassrooms: StudentClassrooms) = viewModelScope.launch {
        repository.deleteClassroom(studentClassrooms)
    }
}

class StudentClassroomsClassViewModelFactory(private val repository: AppRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudentClassroomsClassViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StudentClassroomsClassViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}