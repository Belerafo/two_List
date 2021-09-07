package com.example.two_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.two_list.adapter.HistoriesAdapter
import com.example.two_list.adapter.WalletsAdapter
import com.example.two_list.common.Common
import com.example.two_list.databinding.ActivityMainBinding
import com.example.two_list.model.HistoriesItem
import com.example.two_list.model.WalletsItem
import com.example.two_list.retrofit.RetrofitServices
import dmax.dialog.SpotsDialog
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    lateinit var mService: RetrofitServices
    private val mWalletAdapter by lazy { WalletsAdapter() }
    private val mHistoriesAdapter by lazy { HistoriesAdapter() }
    lateinit var dialog: AlertDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mService = Common.retrofitService
        binding.recyclerViewWallets.setHasFixedSize(true)
        binding.recyclerViewHistories.setHasFixedSize(true)
        binding.recyclerViewWallets.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewHistories.layoutManager = LinearLayoutManager(this)
        dialog = SpotsDialog.Builder().setCancelable(true).setContext(this).build()

        getWalletList()
        getHistoriesList()

    }

    private fun getWalletList() {
        dialog.show()
        mService.getWalletList().enqueue(object : Callback<MutableList<WalletsItem>> {
            override fun onFailure(call: Call<MutableList<WalletsItem>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<MutableList<WalletsItem>>,
                response: Response<MutableList<WalletsItem>>
            ) {
                mWalletAdapter.setData(response.body() as MutableList<WalletsItem>)
                mWalletAdapter.notifyDataSetChanged()
                binding.recyclerViewWallets.adapter = mWalletAdapter

                dialog.dismiss()
            }


        })
    }

    private fun getHistoriesList() {
        dialog.show()
        mService.getHistoriesList().enqueue(object : Callback<MutableList<HistoriesItem>> {
            override fun onFailure(call: Call<MutableList<HistoriesItem>>, t: Throwable) {

            }

            override fun onResponse(
                call: Call<MutableList<HistoriesItem>>,
                response: Response<MutableList<HistoriesItem>>
            ) {
                mHistoriesAdapter.setData(response.body() as MutableList<HistoriesItem>)
                mHistoriesAdapter.notifyDataSetChanged()
                binding.recyclerViewHistories.adapter = mHistoriesAdapter

                dialog.dismiss()
            }


        })
    }
}