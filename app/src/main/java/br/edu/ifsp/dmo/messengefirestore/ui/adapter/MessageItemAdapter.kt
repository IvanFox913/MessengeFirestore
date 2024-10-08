package br.edu.ifsp.dmo.messengefirestore.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.dmo.messengefirestore.R
import br.edu.ifsp.dmo.messengefirestore.data.model.Message
import br.edu.ifsp.dmo.messengefirestore.databinding.ItemMessageBinding

class MessageItemAdapter(val userNumber: String): RecyclerView.Adapter<MessageItemAdapter.ViewHolder>() {

    private var dataset: List<Message> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_message, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binding.textviewMessageText.text = dataset[position].messageText
        holder.binding.textviewMessageTime.text = dataset[position].time.toString()
        if (dataset[position].sender == userNumber){
            val resolvedColor = holder.itemView.resources.getColor(R.color.transluscent_light_blue, null)
            holder.binding.recyclerviewItemContainer.setBackgroundColor(resolvedColor)
        } else {
            val resolvedColor = holder.itemView.resources.getColor(R.color.transluscent_light_green, null)
            holder.binding.recyclerviewItemContainer.setBackgroundColor(resolvedColor)
        }

    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    fun submitDataset(dataset: List<Message>){
        this.dataset = dataset
        notifyDataSetChanged()
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val binding: ItemMessageBinding = ItemMessageBinding.bind(view)
    }

}