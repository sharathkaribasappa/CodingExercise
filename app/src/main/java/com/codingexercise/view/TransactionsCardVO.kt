package com.codingexercise.view

enum class CardType {
    ACCOUNT,
    HEADER,
    TRANSACTION,
    NONE
}

data class TransactionsCardVO (val type: CardType, val data: Any)