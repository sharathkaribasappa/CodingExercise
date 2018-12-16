package com.codingexercise.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class TransactionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract open fun bind(
        transactionsCardVO: TransactionsCardVO,
        position: Int
    )
}