package br.edu.ifsp.dmo.messengefirestore.data.model

class Cellphone(val number: String) {

    lateinit var conversations: List<Conversation>;

}