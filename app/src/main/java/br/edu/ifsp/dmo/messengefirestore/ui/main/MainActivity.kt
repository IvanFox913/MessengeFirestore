package br.edu.ifsp.dmo.messengefirestore.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.dmo.messengefirestore.data.model.Cellphone
import br.edu.ifsp.dmo.messengefirestore.data.repositories.firestore.CellphoneFirestoreRepo
import br.edu.ifsp.dmo.messengefirestore.databinding.ActivityMainBinding
import br.edu.ifsp.dmo.messengefirestore.ui.home.HomeActivity
import br.edu.ifsp.dmo.messengefirestore.util.Constants

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private var repository = CellphoneFirestoreRepo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        setupListeners()
    }

    private fun setupListeners() {
        binding.buttonContinue.setOnClickListener{
            val mIntent = Intent(this, HomeActivity::class.java)
            val cellphone = Cellphone(binding.edittextCellphoneNumber.text.toString())
            repository.insert(cellphone)

            mIntent.putExtra(Constants.USER_CELLPHONE_NUMBER, binding.edittextCellphoneNumber.text.toString())

            startActivity(mIntent)
        }
    }
}