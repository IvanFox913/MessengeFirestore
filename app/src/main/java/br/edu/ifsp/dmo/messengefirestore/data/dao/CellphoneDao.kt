package br.edu.ifsp.dmo.messengefirestore.data.dao

import br.edu.ifsp.dmo.messengefirestore.data.model.Cellphone
import br.edu.ifsp.dmo.messengefirestore.data.model.Conversation

interface CellphoneDao {

    fun insert(cellphone: Cellphone): Long

    fun selectAll(): List<Cellphone>

    fun selectById(number: String): Cellphone

}