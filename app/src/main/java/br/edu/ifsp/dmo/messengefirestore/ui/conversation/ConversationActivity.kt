package br.edu.ifsp.dmo.messengefirestore.ui.conversation

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.ifsp.dmo.messengefirestore.R
import br.edu.ifsp.dmo.messengefirestore.data.dao.UserDao
import br.edu.ifsp.dmo.messengefirestore.data.repositories.UserRepo
import br.edu.ifsp.dmo.messengefirestore.databinding.ActivityConversationBinding
import br.edu.ifsp.dmo.messengefirestore.databinding.ActivityHomeBinding
import br.edu.ifsp.dmo.messengefirestore.ui.adapter.ConversationItemAdapter
import br.edu.ifsp.dmo.messengefirestore.ui.adapter.MessageItemAdapter
import br.edu.ifsp.dmo.messengefirestore.ui.home.HomeViewModel
import br.edu.ifsp.dmo.messengefirestore.ui.home.HomeViewModelFactory
import br.edu.ifsp.dmo.messengefirestore.util.Constants
import com.google.firebase.firestore.FirebaseFirestore
import java.math.BigInteger
import java.util.Date

class ConversationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConversationBinding
    private lateinit var viewModel: ConversationViewModel
    private lateinit var userRepo: UserRepo
    private lateinit var userNumber: String
    private lateinit var contactNumber: String

    private val adapter = MessageItemAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val firestore = FirebaseFirestore.getInstance()
        val userDao = UserDao(firestore)
        userRepo = UserRepo(userDao)

        binding = ActivityConversationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent.hasExtra(Constants.USER_PHONE_NUMBER) && intent.hasExtra(Constants.CONTACT_PHONE_NUMBER)){
            userNumber = intent.getStringExtra(Constants.USER_PHONE_NUMBER)!!
            contactNumber = intent.getStringExtra(Constants.CONTACT_PHONE_NUMBER)!!
        }

        val conversationId = numberSortSmaller(userNumber, contactNumber)

        val factory = ConversationViewModelFactory(userRepo, conversationId)
        viewModel = ViewModelProvider(this, factory).get(ConversationViewModel::class.java)

        setupUi()
        //configClickListener()
        setupRecyclerView()
        setupObservers()
    }

    private fun setupUi(){
        binding.textviewContactNumber.text = contactNumber
    }

    private fun setupRecyclerView() {
        binding.recyclerviewMessages.layoutManager = LinearLayoutManager(this)
        binding.recyclerviewMessages.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.messages.observe(this, Observer {
            adapter.submitDataset(it)
        })
    }

    private fun numberSortSmaller(number: String, number2: String): String {
        if(BigInteger(number) < BigInteger(number2)){
            return number + number2
        } else {
            return number2 + number
        }
    }
}