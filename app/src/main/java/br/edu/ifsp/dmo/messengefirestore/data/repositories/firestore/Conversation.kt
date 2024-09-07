package br.edu.ifsp.dmo.messengefirestore.data.repositories.firestore

import br.edu.ifsp.dmo.messengefirestore.data.dao.ConversationDao
import br.edu.ifsp.dmo.messengefirestore.data.model.Conversation

class Conversation : ConversationDao{
    override fun insert(conversation: Conversation): Long {
        TODO("Not yet implemented")
    }

    override fun update(conversation: Conversation): Int {
        TODO("Not yet implemented")
    }

    override fun delete(conversation: Conversation): Int {
        TODO("Not yet implemented")
    }

    override fun selectAll(): List<Conversation> {
        TODO("Not yet implemented")
    }

    override fun selectById(id: Long): Conversation {
        TODO("Not yet implemented")
    }

}