package com.codingexercise.view

import android.view.View
import com.codingexercise.service.model.Account
import kotlinx.android.synthetic.main.card_account.view.*

class TransactionAccountViewHolder(itemView: View): TransactionViewHolder(itemView) {

    override fun bind(transactionsCardVO: TransactionsCardVO, position: Int) {
        val account = transactionsCardVO.data as Account
        itemView.accountName.text = account.accountName
        itemView.accountNumber.text = account.accountNumber

        val available: String
        available = if(account.available < 0)
            String.format("-$%.2f", Math.abs(account.available))
        else
            String.format("$%.2f", Math.abs(account.available))

        itemView.available.text = available

        val balance: String
        balance = if(account.available < 0)
            String.format("-$%.2f", Math.abs(account.balance))
        else
            String.format("$%.2f", Math.abs(account.balance))

        itemView.balance.text = balance
    }
}