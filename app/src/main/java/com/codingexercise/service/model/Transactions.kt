package com.codingexercise.service.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Transactions(
    val account: Account,
    @SerializedName("transactions")
    val activeTransactions: List<Transaction>,
    @SerializedName("pending")
    val pendingTransactions: List<Transaction>,
    val atms: List<Atm>): Parcelable

@Parcelize
data class Account(
    val accountName: String,
    val accountNumber: String,
    val available: Float,
    val balance: Float
): Parcelable

@Parcelize
data class Transaction(
    val id: String,
    val effectiveDate: String,
    val description: String,
    val amount: Float,
    val atmId: Int
): Parcelable

@Parcelize
data class Atm(
    val id: Int,
    val name: String,
    val address: String,
    val location: Location
): Parcelable

@Parcelize
data class Location(
    val lat: Double,
    val lng: Double
): Parcelable

@Parcelize
data class TransactionStatus(
    val transaction: Transaction,
    val status: Boolean,
    val atms: List<Atm>
) : Parcelable