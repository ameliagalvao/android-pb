package com.example.controledenotas.viewModels

import androidx.lifecycle.*
import com.example.controledenotas.entities.StudentClassrooms
import com.example.controledenotas.repositories.AppRepository
import kotlinx.coroutines.launch

class StudentClassroomsViewModel(private val repository: AppRepository) : ViewModel() {

    var classrooms = repository.getAllClassrooms
    val allClassrooms: LiveData<List<StudentClassrooms>> = repository.getAllClassrooms.asLiveData()

    fun insert(classroom: StudentClassrooms) = viewModelScope.launch {
        repository.insertClassroom(classroom)
    }
    fun delete(classroom: StudentClassrooms) = viewModelScope.launch {
        repository.deleteClassroom(classroom)
    }
    fun findById(id: Int) = viewModelScope.launch {
        repository.findClassroomById(id)
    }
}

class StudentClassroomsViewModelFactory(private val repository: AppRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(StudentClassroomsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return StudentClassroomsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}