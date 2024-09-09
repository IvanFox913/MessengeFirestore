package br.edu.ifsp.dmo.messengefirestore.data.dao

import android.util.Log
import br.edu.ifsp.dmo.messengefirestore.data.model.Message
import br.edu.ifsp.dmo.messengefirestore.data.model.User
import com.google.firebase.firestore.FirebaseFirestore
import java.math.BigInteger
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.util.Date


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

        val conversationId : String = (smallerNumber + biggerNumber)

        firestore.collection("users")
            .document(senderNumber).collection("userConversations")
            .document(receiverNumber).set(mapOf("conversationId" to conversationId))
                .addOnSuccessListener { callback(true) }
                .addOnFailureListener { callback(false) }

    }

    fun findAllConversations(userNumber: String, callback: (List<String>) -> Unit) {

        firestore.collection("users")
            .document(userNumber).collection("userConversations")
            .addSnapshotListener { querySnapshot, exception ->
                if (exception != null) {
                    Log.e("firebase", "Listen fail.")
                    callback(emptyList())
                    return@addSnapshotListener
                }
                if (querySnapshot != null) {
                    val list = querySnapshot.documents.map { it.id }
                    callback(list)
                } else {
                    Log.e("firebase", "Empty conversations list.")
                    callback(emptyList())
                }
            }
    }

    fun findAllMessages(conversationId: String, callback: (List<Message>) -> Unit) {

        firestore.collection("conversations")
            .document(conversationId)
            .collection("messages")
            .orderBy("time")
            .addSnapshotListener { querySnapshot, exception ->
                if (exception != null) {
                    Log.e("firebase", "Listen failed.", exception)
                    callback(emptyList())
                    return@addSnapshotListener
                }
                if (querySnapshot != null) {
                    val messages = querySnapshot.documents.mapNotNull { document ->
                        val sender = document.getString("sender")
                        val time = document.getTimestamp("time")?.toDate()
                        val messageText = document.getString("messageText")

                        if (sender != null && time != null && messageText != null) {
                            Message(sender, time, messageText)
                        } else {
                            Log.w("firebase", "Incomplete message data: ${document.id}")
                            null
                        }
                    }
                    callback(messages)
                } else {
                    Log.e("firebase", "Empty messages list.")
                    callback(emptyList())
                }
            }
    }

    fun sendMessage(conversationId: String, message: Message){

        firestore.collection("conversations")
            .document(conversationId)
            .collection("messages").document().set(message)
    }

    private fun numberSortSmaller(number: String, number2: String): String {
        if(BigInteger(number) < BigInteger(number2)){
            return number
        } else {
            return number2
        }
    }
}