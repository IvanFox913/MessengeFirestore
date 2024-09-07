package br.edu.ifsp.dmo.messengefirestore.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.dmo.messengefirestore.data.repositories.firestore.CellphoneFirestoreRepo
import br.edu.ifsp.dmo.messengefirestore.databinding.ActivityHomeBinding
import br.edu.ifsp.dmo.messengefirestore.util.Constants

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel
    private var repository = CellphoneFirestoreRepo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent.hasExtra(Constants.USER_CELLPHONE_NUMBER)){
            val number = intent.getStringExtra(Constants.USER_CELLPHONE_NUMBER)


            binding.textviewUserNumber.text = repository.selectById(number!!).number
        }


    }
}