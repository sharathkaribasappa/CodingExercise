package com.codingexercise.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.codingexercise.R
import com.codingexercise.service.model.Transactions
import com.codingexercise.util.hide
import com.codingexercise.util.setVisibility
import com.codingexercise.util.show
import com.codingexercise.viewmodel.TransactionsViewModel
import kotlinx.android.synthetic.main.transactions_fragment.*

class TransactionsFragment : Fragment(), SwipeRefreshLayout.OnRefreshListener {

    companion object {
        fun newInstance() = TransactionsFragment()
    }

    private lateinit var viewModel: TransactionsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.transactions_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progress_circular.show()
        initViewModel()
        setupRecyclerView()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(TransactionsViewModel::class.java)
        viewModel.load()?.observe(this, Observer<Transactions> {
            Log.d("TransactionsFragment","data: $it")
            progress_circular.hide()
            if(it != null) {
                showContent(it)
            }
        })
    }

    private fun setupRecyclerView() {
        my_recycler_view.show()
        my_recycler_view.layoutManager = LinearLayoutManager(context)
        refreshLayout.setOnRefreshListener(this)
    }

    override fun onRefresh() {
        viewModel.load()
        refreshLayout.isRefreshing = false
    }

    private fun showContent(transactions: Transactions) {
        val adapter = TransactionsAdapter(TransactionsHelper.getTransactions(transactions))
        my_recycler_view.adapter = adapter
    }
}
