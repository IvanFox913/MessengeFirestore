package br.edu.ifsp.dmo.messengefirestore.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.edu.ifsp.dmo.messengefirestore.data.model.Conversation
import br.edu.ifsp.dmo.messengefirestore.data.repositories.UserRepo

class HomeViewModel(private val repository: UserRepo) : ViewModel() {

    private val _conversations = MutableLiveData<List<String>>()
    val conversations: LiveData<List<String>> = _conversations

    init {
        loadConversations()
    }

    fun loadConversations() {
        repository.findAllConversations { list ->
            _conversations.value = list
        }
    }
}