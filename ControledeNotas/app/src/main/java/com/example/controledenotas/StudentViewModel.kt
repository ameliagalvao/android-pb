package com.example.controledenotas

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class StudentViewModel(private val repository: StudentRepository) : ViewModel() {

    val allStudents: LiveData<List<Student>> = repository.allStudents.asLiveData()
    fun insert(student: Student) = viewModelScope.launch {
        repository.insert(student)
    }
    fun delete(student: Student) = viewModelScope.launch {
        repository.delete(student)
    }
    fun findById(id: Int) = viewModelScope.launch {
        repository.findById(id)
    }
}

class StudentViewModelFactory(private val repository: StudentRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudentViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StudentViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}