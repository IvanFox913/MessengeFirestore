package br.edu.ifsp.dmo.messengefirestore.data.repositories.firestore

import br.edu.ifsp.dmo.messengefirestore.data.dao.CellphoneDao
import br.edu.ifsp.dmo.messengefirestore.data.model.Cellphone

class Cellphone : CellphoneDao {
    override fun insert(cellphone: Cellphone): Long {
        TODO("Not yet implemented")
    }

    override fun selectAll(): List<Cellphone> {
        TODO("Not yet implemented")
    }

    override fun selectById(number: String): Cellphone {
        TODO("Not yet implemented")
    }
}