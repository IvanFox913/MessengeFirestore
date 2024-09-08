package br.edu.ifsp.dmo.messengefirestore.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.edu.ifsp.dmo.messengefirestore.data.repositories.UserRepo

class HomeViewModelFactory(private val repository: UserRepo, private val userNumber: String) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(repository, userNumber) as T
        }

        throw IllegalArgumentException("View Model desconhecido")
    }
}