package br.edu.ifsp.dmo.messengefirestore.data.repositories.firestore

import android.util.Log
import br.edu.ifsp.dmo.messengefirestore.data.dao.CellphoneDao
import br.edu.ifsp.dmo.messengefirestore.data.model.Cellphone
import com.google.firebase.Firebase
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.firestore

class CellphoneFirestoreRepo : CellphoneDao {

    val firestore = Firebase.firestore

    override fun insert(cellphone: Cellphone): Boolean {

        var success : Boolean = false

        firestore.collection("Cellphone")
            .document(cellphone.number)
            .set(cellphone)
            .addOnSuccessListener {
                Log.v("Cellphone", "Cellphone saved. Number: ${cellphone.number}")
                success = true
            }
            .addOnFailureListener {
                Log.v("Cellphone", "Cellphone insert error: ", it)
                success = false
            }

        return success
    }

    override fun selectAll(): List<Cellphone> {
        TODO("Not yet implemented")
    }

    override fun selectById(number: String): Cellphone {

        val cellphone = Cellphone()

        firestore.collection("Cellphone")
            .document(number)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful){
                    val document: DocumentSnapshot = it.result
                    if (document.exists()){
                        cellphone.number = document.data.toString()
                    } else {
                        Log.v("DMO", "Usuário não encontrado")
                    }
                }
            }

        return cellphone
    }
}