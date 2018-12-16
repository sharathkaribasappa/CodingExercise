package com.codingexercise.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codingexercise.R

class TransactionsAdapter(val cardVOList: MutableList<TransactionsCardVO>) :
    RecyclerView.Adapter<TransactionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        return when (viewType) {
            CardType.ACCOUNT.ordinal -> {
                TransactionAccountViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.card_account,
                        parent,
                        false
                    )
                )
            }
            CardType.HEADER.ordinal -> {
                TransactionHeaderViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.card_header,
                        parent,
                        false
                    )
                )
            }
            CardType.TRANSACTION.ordinal -> {
                ActiveTransactionsViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.card_transaction,
                        parent,
                        false
                    )
                )
            }
            else -> {
                TransactionAccountViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.card_account,
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun getItemCount() = cardVOList.size

    override fun getItemViewType(position: Int) = cardVOList[position].type.ordinal

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(cardVOList[position], position)
    }
}