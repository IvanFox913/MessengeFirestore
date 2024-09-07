package br.edu.ifsp.dmo.messengefirestore.data.model

class Conversation (val smallerNumber: String, val biggerNumber: String) {

    lateinit var messages: List<Message>
}