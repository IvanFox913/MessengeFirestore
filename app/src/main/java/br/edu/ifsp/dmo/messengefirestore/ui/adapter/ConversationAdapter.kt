package br.edu.ifsp.dmo.messengefirestore.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.dmo.messengefirestore.R
import br.edu.ifsp.dmo.messengefirestore.databinding.ItemConversationBinding
import br.edu.ifsp.dmo.messengefirestore.ui.listeners.ConversationItemClickListener

class ConversationAdapter(private val listener: ConversationItemClickListener): RecyclerView.Adapter<ConversationAdapter.ViewHolder>() {

    private var dataset: List<String> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_conversation, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.contactNumber.text = dataset[position]

        holder.itemView.setOnClickListener {
            listener.clickOpenConversation(dataset[position])
        }
    }
    override fun getItemCount(): Int {
        return dataset.size
    }
    fun submitDataset(dataset: List<String>){
        this.dataset = dataset
    }
    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding: ItemConversationBinding = ItemConversationBinding.bind(view)
    }
}