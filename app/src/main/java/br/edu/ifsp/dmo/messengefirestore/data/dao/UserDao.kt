package br.edu.ifsp.dmo.messengefirestore.data.dao

import br.edu.ifsp.dmo.messengefirestore.data.model.Conversation
import br.edu.ifsp.dmo.messengefirestore.data.model.Message
import br.edu.ifsp.dmo.messengefirestore.data.model.User
import com.google.firebase.firestore.FirebaseFirestore

class UserDao (private val firestore: FirebaseFirestore) {

    fun insertUser(user: User, callback: (Boolean) -> Unit) {
        firestore.collection("users").document(user.phoneNumber).set(user)
            .addOnSuccessListener { callback(true) }
            .addOnFailureListener { callback(false) }
    }

    /*
    fun getUserByNumber(): User {
        
    }

    fun insertConversation(userSmaller: User, userBigger: User) {
        
    }

    fun insertMessage(message: Message): Boolean {
        
    }

    fun getAllConversationsByUser(user: User): List<Conversation> {
        
    }

    fun getAllMessagesByConversation(conversation: Conversation): List<Message> {
        
    }

     */
}