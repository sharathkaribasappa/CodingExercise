package com.codingexercise.view

import java.text.DateFormatSymbols
import android.view.View
import kotlinx.android.synthetic.main.card_header.view.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class TransactionHeaderViewHolder(itemView: View): TransactionViewHolder(itemView) {
    override fun bind(transactionsCardVO: TransactionsCardVO, position: Int) {
        val date = transactionsCardVO.data as String

        val formattedDate = getFormattedDate(setCalenderInstance(date))

        itemView.transactionDate.text = formattedDate
    }

    private fun parse(input: String?, format: String?): Date? {
        if (input != null && format != null) {
            try {
                return SimpleDateFormat(format).parse(input)
            } catch (e: ParseException) {
            }
        }
        return null
    }

    private fun setCalenderInstance(date: String): Calendar {
        val cal = Calendar.getInstance()
        cal.time = parse(date, "dd/MM/yyyy")
        return cal
    }

    private fun getFormattedDate(cal: Calendar): String {
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DATE)

        val monthName = DateFormatSymbols().shortMonths[month]

        return day.toString() + " " + monthName.toString() + " " + year.toString()
    }
}