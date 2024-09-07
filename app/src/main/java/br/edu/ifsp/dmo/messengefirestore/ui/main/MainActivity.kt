package br.edu.ifsp.dmo.messengefirestore.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.ifsp.dmo.messengefirestore.R
import br.edu.ifsp.dmo.messengefirestore.databinding.ActivityMainBinding
import br.edu.ifsp.dmo.messengefirestore.ui.home.HomeActivity
import br.edu.ifsp.dmo.messengefirestore.util.Constants

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListeners()
    }

    private fun setupListeners() {
        binding.buttonContinue.setOnClickListener{
            val mIntent = Intent(this, HomeActivity::class.java)
            mIntent.putExtra(Constants.USER_CELLPHONE_NUMBER, binding.edittextCellphoneNumber.text.toString())
            startActivity(mIntent)
        }
    }
}