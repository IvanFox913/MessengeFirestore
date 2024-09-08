package br.edu.ifsp.dmo.messengefirestore.data.dao

import br.edu.ifsp.dmo.messengefirestore.data.model.Conversation
import br.edu.ifsp.dmo.messengefirestore.data.model.Message
import br.edu.ifsp.dmo.messengefirestore.data.model.User
import com.google.firebase.firestore.FirebaseFirestore
import java.math.BigInteger

class UserDao (private val firestore: FirebaseFirestore) {

    fun insertUser(user: User, callback: (Boolean) -> Unit) {
        firestore.collection("users").document(user.phoneNumber).set(user)
            .addOnSuccessListener { callback(true) }
            .addOnFailureListener { callback(false) }
    }

    fun insertConversation(senderNumber: String, receiverNumber: String, callback: (Boolean) -> Unit) {

        val smallerNumber = numberSortSmaller(senderNumber, receiverNumber)
        val biggerNumber: String

        if(smallerNumber == senderNumber){
            biggerNumber = receiverNumber
        } else {
            biggerNumber = senderNumber
        }

        val conversation = Conversation(biggerNumber, smallerNumber)

        firestore.collection("conversations").document(smallerNumber+biggerNumber).set(conversation)
            .addOnSuccessListener { callback(true) }
            .addOnFailureListener { callback(false) }
    }

    /*
    fun getUserByNumber(): User {
        
    }

    fun insertMessage(message: Message): Boolean {
        
    }

    fun getAllConversationsByUser(user: User): List<Conversation> {
        
    }

    fun getAllMessagesByConversation(conversation: Conversation): List<Message> {
        
    }

     */

    private fun numberSortSmaller(number: String, number2: String): String {
        if(BigInteger(number) < BigInteger(number2)){
            return number
        } else {
            return number2
        }
    }
}