package com.example.controledenotas.viewModels

import androidx.lifecycle.*
import com.example.controledenotas.entities.Student
import com.example.controledenotas.repositories.AppRepository
import kotlinx.coroutines.launch

class StudentViewModel(private val repository: AppRepository) : ViewModel() {

    val allStudents: LiveData<List<Student>> = repository.allStudents.asLiveData()
    fun insert(student: Student) = viewModelScope.launch {
        repository.insert(student)
    }
    fun delete(student: Student) = viewModelScope.launch {
        repository.delete(student)
    }
    fun findById(id: Int) = viewModelScope.launch {
        repository.findStudentById(id)
    }
}

class StudentViewModelFactory(private val repository: AppRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StudentViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}