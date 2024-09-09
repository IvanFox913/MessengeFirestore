package br.edu.ifsp.dmo.messengefirestore.data.repositories

import br.edu.ifsp.dmo.messengefirestore.data.dao.UserDao
import br.edu.ifsp.dmo.messengefirestore.data.model.Message
import br.edu.ifsp.dmo.messengefirestore.data.model.User

class UserRepo (private val userDao: UserDao){

    fun insertUser(user: User, callback: (Boolean) -> Unit) {
        userDao.insertUser(user, callback)
    }

    fun insertConversation(senderNumber : String, receiverNumber: String, callback: (Boolean) -> Unit) {
        userDao.insertConversation(senderNumber, receiverNumber, callback)
    }

    fun findAllConversations(userNumber: String, callback: (List<String>) -> Unit){
        userDao.findAllConversations(userNumber, callback)
    }

    fun findAllMessages(conversationId: String, callback: (List<Message>) -> Unit){
        userDao.findAllMessages(conversationId, callback)
    }

}