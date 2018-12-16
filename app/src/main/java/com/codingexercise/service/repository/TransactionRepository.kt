package com.codingexercise.service.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.codingexercise.service.model.Transactions
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TransactionRepository() : Callback<Transactions> {
    private var transactionService: TransactionService
    private var data = MutableLiveData<Transactions>()
    val gson: Gson = Gson()

    private var retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(
            getGsonFactoryProvider()
        )
        .baseUrl("https://api.github.com/")
        .build()

    init {
        transactionService = retrofit.create(TransactionService::class.java)
    }

    fun transactions(): LiveData<Transactions> {
        return request(transactionService.getTransactions())
    }

    private fun request(call: Call<Transactions>): LiveData<Transactions> {
        call.enqueue(this)
        return data
    }

    override fun onFailure(call: Call<Transactions>, t: Throwable) {
        Log.e("TransactionRepository","error:" + t.message)
        data.value = null
    }

    override fun onResponse(call: Call<Transactions>, response: Response<Transactions>) {
        data.value = response.body()
    }

    fun getGsonFactoryProvider(): GsonConverterFactory {
        return GsonConverterFactory.create(gson)
    }
}