package com.example.two_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.two_list.common.RecyclerDiffUtil
import com.example.two_list.databinding.HistoriesRowLayoutBinding
import com.example.two_list.model.HistoriesItem

class HistoriesAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val INCOMING = 1
    private val OUTCOMING = 2
    private var history = emptyList<HistoriesItem>()

    class IncomingViewHolder(private val binding: HistoriesRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(history: HistoriesItem){
            binding.operationDescriptionTextView.text = "You've received payment from ${history.sender}"
            binding.operationValueTextView.text = "+${history.amount} ${history.currency}"
        }

        companion object{
            fun from(parent: ViewGroup): IncomingViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = HistoriesRowLayoutBinding.inflate(layoutInflater, parent, false)
                return IncomingViewHolder(binding)
            }
        }

    }

    class OutComingViewHolder(private val binding: HistoriesRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(history: HistoriesItem){
            binding.operationDescriptionTextView.text = "You've cashed out to ${history.recipient}"
            binding.operationValueTextView.text = "-${history.amount} ${history.currency}"
        }

        companion object{
            fun from(parent: ViewGroup): OutComingViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = HistoriesRowLayoutBinding.inflate(layoutInflater, parent, false)
                return OutComingViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == INCOMING) {
            IncomingViewHolder.from(parent)
        } else OutComingViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (history[position].entry == "incoming") { // put your condition, according to your requirements
            (holder as IncomingViewHolder).bind(history[position])
        } else {
            (holder as OutComingViewHolder).bind(history[position])
        }
    }



    override fun getItemCount(): Int {
        return history.size
    }

    override fun getItemViewType(position: Int): Int {
        // here you can get decide from your model's ArrayList, which type of view you need to load. Like
        return if (history[position].entry == "incoming") { // put your condition, according to your requirements
            INCOMING
        } else OUTCOMING
    }

    fun setData(newData: MutableList<HistoriesItem>){
        val diffUtil = RecyclerDiffUtil(history, newData)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
        history = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }
}