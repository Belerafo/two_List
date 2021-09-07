package com.example.two_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.two_list.adapter.WalletsAdapter
import com.example.two_list.common.Common
import com.example.two_list.databinding.ActivityMainBinding
import com.example.two_list.model.WalletsItem
import com.example.two_list.retrofit.RetrofitServices
import dmax.dialog.SpotsDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var mService: RetrofitServices
    lateinit var layoutManager: LinearLayoutManager
    private val adapter by lazy { WalletsAdapter() }
    lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mService = Common.retrofitService
        binding.recyclerViewWallets.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        binding.recyclerViewWallets.layoutManager = layoutManager
        dialog = SpotsDialog.Builder().setCancelable(true).setContext(this).build()

        getWalletList()

    }

    private fun getWalletList() {
        dialog.show()
        mService.getWalletList().enqueue(object : Callback<MutableList<WalletsItem>> {
            override fun onFailure(call: Call<MutableList<WalletsItem>>, t: Throwable) {

            }

            override fun onResponse(call: Call<MutableList<WalletsItem>>, response: Response<MutableList<WalletsItem>>) {
                adapter.setData(response.body() as MutableList<WalletsItem>)
                adapter.notifyDataSetChanged()
                binding.recyclerViewWallets.adapter = adapter

                dialog.dismiss()
            }


        })
    }
}