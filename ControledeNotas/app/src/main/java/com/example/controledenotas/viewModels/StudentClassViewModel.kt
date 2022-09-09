package com.example.controledenotas.viewModels

import androidx.lifecycle.*
import com.example.controledenotas.entities.StudentClassrooms
import com.example.controledenotas.repositories.AppRepository
import kotlinx.coroutines.launch

class StudentClassViewModel (private val repository: AppRepository) : ViewModel() {
    val allStudentClasses: LiveData<List<StudentClassrooms>> = repository.getAllClassrooms.asLiveData()
    fun insert(studentClassrooms: StudentClassrooms) = viewModelScope.launch {
        repository.insertClassroom(studentClassrooms)
    }
    fun delete(studentClassrooms: StudentClassrooms) = viewModelScope.launch {
        repository.deleteClassroom(studentClassrooms)
    }
}

class StudentClassViewModelFactory(private val repository: AppRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudentClassViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StudentClassViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}