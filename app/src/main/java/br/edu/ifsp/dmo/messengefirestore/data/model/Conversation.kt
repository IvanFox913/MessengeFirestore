package br.edu.ifsp.dmo.messengefirestore.data.model

class Conversation (val smallerNumber: String, val biggerNumber: String) {

    var messages: List<Message> = ArrayList<Message>()
}