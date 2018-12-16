package com.codingexercise.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.codingexercise.service.model.Transactions
import com.codingexercise.service.repository.TransactionRepository

class TransactionsViewModel : ViewModel() {
    private var transactions: LiveData<Transactions>? = null
    private val transactionRepository: TransactionRepository = TransactionRepository()

    fun load(): LiveData<Transactions>? {
        transactions = transactionRepository.transactions()
        return transactions
    }
}
