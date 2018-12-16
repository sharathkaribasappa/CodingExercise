package com.codingexercise

import com.codingexercise.service.model.Account
import com.codingexercise.service.model.Transaction
import com.codingexercise.service.model.TransactionStatus
import com.codingexercise.service.model.Transactions
import com.codingexercise.view.TransactionsHelper
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class TransactionHelperTest {
    private lateinit var transactions: Transactions

    @Before
    fun setUp() {
        transactions = getTransaction()
    }

    private fun getTransaction(): Transactions {
        val transactionsMock = mock<Transactions>()

        val account = mock<Account>()
        whenever(account.accountName).thenReturn("Complete Access")
        whenever(account.accountNumber).thenReturn("0465 5676 5657")

        whenever(transactionsMock.account).thenReturn(account)

        val transaction = mock<Transaction>()
        whenever(transaction.id).thenReturn("d4fae4b45e689707e7dea506afc8c0e7")
        whenever(transaction.effectiveDate).thenReturn("23/07/2017")

        val transaction1 = mock<Transaction>()
        whenever(transaction1.id).thenReturn("87f6f9d078c3bc5db5578f3b4add9470")
        whenever(transaction1.effectiveDate).thenReturn("23/07/2017")

        val activeTransactionList = mutableListOf<Transaction>()
        activeTransactionList.add(transaction)
        activeTransactionList.add(transaction1)
        whenever(transactionsMock.activeTransactions).thenReturn(activeTransactionList)
        return transactionsMock
    }

    @Test
    fun testGetTransactions() {
        var transactionCards = TransactionsHelper.getTransactions(transactions)

        val accountCard = transactionCards[0].data as Account
        TestCase.assertEquals(accountCard.accountName, "Complete Access")
        TestCase.assertEquals(accountCard.accountNumber, "0465 5676 5657")

        val transactionStatus = transactionCards[2].data as TransactionStatus
        TestCase.assertEquals(transactionStatus.transaction.id, "d4fae4b45e689707e7dea506afc8c0e7")
        TestCase.assertEquals(transactionStatus.transaction.effectiveDate, "23/07/2017")

        TestCase.assertEquals(transactionCards.size, 4)
    }
}