package br.edu.ifsp.dmo.messengefirestore.data.repositories

import br.edu.ifsp.dmo.messengefirestore.data.dao.UserDao
import br.edu.ifsp.dmo.messengefirestore.data.model.User

class UserRepo (private val userDao: UserDao){

    fun insertUser(user: User, callback: (Boolean) -> Unit) {
        userDao.insertUser(user, callback)
    }

    fun insertConversation(senderNumber : String, recieverNumber: String, callback: (Boolean) -> Unit) {
        userDao.insertConversation(senderNumber, recieverNumber, callback)
    }

}