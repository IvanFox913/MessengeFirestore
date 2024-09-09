package br.edu.ifsp.dmo.messengefirestore.ui.conversation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.edu.ifsp.dmo.messengefirestore.data.model.Message
import br.edu.ifsp.dmo.messengefirestore.data.repositories.UserRepo

class ConversationViewModel(private val repository: UserRepo, private val conversationID: String) : ViewModel() {

    private val _messages = MutableLiveData<List<Message>>()
    val messages: LiveData<List<Message>> = _messages

    init {
        loadMessages()
    }

    fun loadMessages() {
        repository.findAllMessages(conversationID) { list ->
            _messages.value = list
        }
    }
}