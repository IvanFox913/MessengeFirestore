package br.edu.ifsp.dmo.messengefirestore.data.model

class User (val phoneNumber: String) {

    var name: String = "new User"
    var conversations:List<Conversation> = ArrayList<Conversation>()
}