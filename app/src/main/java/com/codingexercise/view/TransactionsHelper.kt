package com.codingexercise.view

import com.codingexercise.service.model.TransactionStatus
import com.codingexercise.service.model.Transactions
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date

object TransactionsHelper {
    fun getTransactions(transactions: Transactions): MutableList<TransactionsCardVO> {
        val myTransactions = mutableListOf<TransactionsCardVO>()

        //create the account card
        myTransactions.add(TransactionsCardVO(CardType.ACCOUNT, transactions.account))

        //create the transactions card
        val transactionList = mutableListOf<TransactionStatus>()
        transactions.activeTransactions.forEach {
            transactionList.add(TransactionStatus(it, false,transactions.atms))
        }

        transactions.pendingTransactions.forEach {
            transactionList.add(TransactionStatus(it, true,transactions.atms))
        }

        var previousDate: Date? = null
        var currentDate: Date?
        transactionList.sortedByDescending { parse(it.transaction.effectiveDate, "dd/MM/yyyy") }
            .forEachIndexed { index, element ->
                if (index > 0) {
                    currentDate = parse(element.transaction.effectiveDate, "dd/MM/yyyy")
                    if (currentDate != previousDate) {
                        //create the header card
                        myTransactions.add(TransactionsCardVO(CardType.HEADER, element.transaction.effectiveDate))
                    }
                    previousDate = currentDate
                } else {
                    myTransactions.add(TransactionsCardVO(CardType.HEADER, element.transaction.effectiveDate))
                    previousDate = parse(element.transaction.effectiveDate, "dd/MM/yyyy")
                }

                myTransactions.add(TransactionsCardVO(CardType.TRANSACTION, element))
            }

        return myTransactions
    }

    fun parse(input: String?, format: String?): Date? {
        if (input != null && format != null) {
            try {
                return SimpleDateFormat(format).parse(input)
            } catch (e: ParseException) {
            }
        }

        return null
    }
}