package br.edu.ifsp.dmo.messengefirestore.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.edu.ifsp.dmo.messengefirestore.data.repositories.UserRepo

class MainViewModelFactory(private val repository: UserRepo) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }

        throw IllegalArgumentException("View Model desconhecido")
    }
}
