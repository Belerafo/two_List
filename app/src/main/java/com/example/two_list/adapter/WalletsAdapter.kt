package com.example.two_list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.two_list.common.RecyclerDiffUtil
import com.example.two_list.databinding.WalletRowLayoutBinding
import com.example.two_list.model.WalletsItem

class WalletsAdapter: RecyclerView.Adapter<WalletsAdapter.MyVieHolder>()  {
    private var currency = emptyList<WalletsItem>()

    class MyVieHolder(private val binding: WalletRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currency: WalletsItem){
            binding.item = currency
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup): MyVieHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = WalletRowLayoutBinding.inflate(layoutInflater, parent, false)
                return MyVieHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVieHolder {
        return MyVieHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyVieHolder, position: Int) {
        val currentCurrency = currency[position]
        holder.bind(currentCurrency)
    }

    override fun getItemCount(): Int {
        return currency.size
    }

    fun setData(newData: MutableList<WalletsItem>){
        val diffUtil = RecyclerDiffUtil(currency, newData)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
        currency = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }
}
