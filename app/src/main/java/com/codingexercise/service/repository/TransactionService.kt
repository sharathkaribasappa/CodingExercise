package com.codingexercise.service.repository

import com.codingexercise.service.model.Transactions
import retrofit2.http.GET
import retrofit2.Call

const val HTTPS_TRANSACTION_URL = "https://www.dropbox.com/s/tewg9b71x0wrou9/data.json?dl=1"

interface TransactionService {

    @GET(HTTPS_TRANSACTION_URL)
    fun getTransactions(): Call<Transactions>
}