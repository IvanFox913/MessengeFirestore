package br.edu.ifsp.dmo.messengefirestore.ui.home

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.ifsp.dmo.messengefirestore.data.dao.UserDao
import br.edu.ifsp.dmo.messengefirestore.data.repositories.UserRepo
import br.edu.ifsp.dmo.messengefirestore.databinding.ActivityHomeBinding
import br.edu.ifsp.dmo.messengefirestore.databinding.AddConversationDialogBinding
import br.edu.ifsp.dmo.messengefirestore.ui.adapter.ConversationItemAdapter
import br.edu.ifsp.dmo.messengefirestore.ui.conversation.ConversationActivity
import br.edu.ifsp.dmo.messengefirestore.ui.listeners.ConversationItemClickListener
import br.edu.ifsp.dmo.messengefirestore.util.Constants
import com.google.firebase.firestore.FirebaseFirestore

class HomeActivity : AppCompatActivity(), ConversationItemClickListener {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var userRepo: UserRepo
    private lateinit var userNumber: String

    private val adapter = ConversationItemAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val firestore = FirebaseFirestore.getInstance()
        val userDao = UserDao(firestore)
        userRepo = UserRepo(userDao)


        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(intent.hasExtra(Constants.USER_PHONE_NUMBER)){
            userNumber = intent.getStringExtra(Constants.USER_PHONE_NUMBER)!!
        }

        val factory = HomeViewModelFactory(userRepo, userNumber)
        viewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)

        configClickListener()
        setupRecyclerView()
        setupObservers()
    }

    private fun configClickListener() {
        binding.buttonAddConversation.setOnClickListener {
            handleNewConversationDialog()
        }
    }

    private fun handleNewConversationDialog() {
        val bindingDialog = AddConversationDialogBinding.inflate(layoutInflater)
        val builderDialog = AlertDialog.Builder(this)

        builderDialog.setView(bindingDialog.root)
            .setTitle("Add new conversation")
            .setPositiveButton(
                "Salvar",
                DialogInterface.OnClickListener { dialog, which ->
                    userRepo.insertConversation(
                        userNumber,
                        bindingDialog.edittextNumberField.text.toString()) { success ->
                            if (success) {
                                //val mIntent = Intent(this, ConversationActivity::class.java)
                                //startActivity(mIntent)
                                dialog.dismiss()
                            } else {
                                Toast.makeText(this, "Failed to start new conversation. Please try again.", Toast.LENGTH_LONG).show()
                            }
                        }
                    dialog.dismiss()
                })
            .setNegativeButton(
                "Cancel",
                DialogInterface.OnClickListener { dialog, which ->
                    Log.v("dialog", "Cancelar novo contato")
                    dialog.cancel()
                })
        builderDialog.create().show()
    }

    override fun clickOpenConversation(contactNumber: String) {
        val mIntent = Intent(this, ConversationActivity::class.java)
        mIntent.putExtra(Constants.USER_PHONE_NUMBER, userNumber)
        mIntent.putExtra(Constants.CONTACT_PHONE_NUMBER, contactNumber)
        startActivity(mIntent)
    }

    private fun setupRecyclerView() {
        binding.recyclerviewConversations.layoutManager = LinearLayoutManager(this)
        binding.recyclerviewConversations.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.conversations.observe(this, Observer {
            adapter.submitDataset(it)
        })
    }
}