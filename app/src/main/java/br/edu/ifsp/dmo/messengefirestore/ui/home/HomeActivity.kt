package br.edu.ifsp.dmo.messengefirestore.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.dmo.messengefirestore.data.dao.UserDao
import br.edu.ifsp.dmo.messengefirestore.data.repositories.UserRepo
import br.edu.ifsp.dmo.messengefirestore.databinding.ActivityHomeBinding
import br.edu.ifsp.dmo.messengefirestore.util.Constants
import com.google.firebase.firestore.FirebaseFirestore

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var userRepo: UserRepo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val firestore = FirebaseFirestore.getInstance()
        val userDao = UserDao(firestore)
        userRepo = UserRepo(userDao)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent.hasExtra(Constants.USER_PHONE_NUMBER)){
            var userNumber: String = intent.getStringExtra(Constants.USER_PHONE_NUMBER)!!
            binding.textviewUserNumber.text = userNumber
        }
    }
}