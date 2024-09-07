package br.edu.ifsp.dmo.messengefirestore.data.dao

import br.edu.ifsp.dmo.messengefirestore.data.model.Conversation

interface ConversationDao {

    fun insert(conversation: Conversation): Long

    fun update(conversation: Conversation): Int

    fun delete(conversation: Conversation): Int

    fun selectAll(): List<Conversation>

    fun selectById(id: Long): Conversation
}