package br.edu.ifsp.dmo.messengefirestore.ui.conversation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.edu.ifsp.dmo.messengefirestore.data.repositories.UserRepo
import br.edu.ifsp.dmo.messengefirestore.ui.home.HomeViewModel

class ConversationViewModelFactory (private val repository: UserRepo, private val conversationI: String) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(ConversationViewModel::class.java)) {
            return ConversationViewModel(repository, conversationI) as T
        }

        throw IllegalArgumentException("View Model desconhecido")
    }
}