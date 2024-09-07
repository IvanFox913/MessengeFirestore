package br.edu.ifsp.dmo.messengefirestore.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import br.edu.ifsp.dmo.messengefirestore.data.dao.UserDao
import br.edu.ifsp.dmo.messengefirestore.data.model.User
import br.edu.ifsp.dmo.messengefirestore.data.repositories.UserRepo
import br.edu.ifsp.dmo.messengefirestore.databinding.ActivityMainBinding
import br.edu.ifsp.dmo.messengefirestore.ui.home.HomeActivity
import br.edu.ifsp.dmo.messengefirestore.util.Constants
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var userRepo: UserRepo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val firestore = FirebaseFirestore.getInstance()
        val userDao = UserDao(firestore)
        userRepo = UserRepo(userDao)

        setupListeners()
    }

    private fun setupListeners() {
        binding.buttonContinue.setOnClickListener{
            val user = User(binding.edittextCellphoneNumber.text.toString())
            userRepo.insertUser(user) { success ->
                if (success) {
                    val mIntent = Intent(this, HomeActivity::class.java)
                    mIntent.putExtra(Constants.USER_PHONE_NUMBER, user.phoneNumber)
                    startActivity(mIntent)
                } else {
                    Toast.makeText(this, "Failed to insert user. Please try again.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}