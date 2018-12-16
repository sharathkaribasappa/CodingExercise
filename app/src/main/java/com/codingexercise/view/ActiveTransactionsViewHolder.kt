package com.codingexercise.view

import android.content.Intent
import android.text.Html
import android.view.View
import com.codingexercise.service.model.TransactionStatus
import com.codingexercise.util.setVisibility
import kotlinx.android.synthetic.main.card_transaction.view.*

class ActiveTransactionsViewHolder(itemView: View) : TransactionViewHolder(itemView) {
    override fun bind(transactionsCardVO: TransactionsCardVO, position: Int) {
        val transactionStatus = transactionsCardVO.data as TransactionStatus

        itemView.description.text = Html.fromHtml(transactionStatus.transaction.description)

        val amount: String
        amount = if (transactionStatus.transaction.amount < 0)
            String.format("-$%.2f", Math.abs(transactionStatus.transaction.amount))
        else
            String.format("$%.2f", Math.abs(transactionStatus.transaction.amount))

        itemView.amount.text = amount

        itemView.pending.setVisibility(transactionStatus.status)

        val atm = transactionStatus.atms.filter { it.id == transactionStatus.transaction.atmId }

        if(atm.isNotEmpty()) {
            itemView.setOnClickListener {
                var intent = Intent(itemView.context, MapsActivity::class.java)
                intent.putExtra(PARAM_ATM, atm[0])
                itemView.context.startActivity(intent)
            }
        }
    }
}