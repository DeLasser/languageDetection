package ru.mininn.languagedetection.ui.history

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_history.view.*
import ru.mininn.languagedetection.R
import ru.mininn.languagedetection.data.model.DetectedText

class HistoryFragment : Fragment(), Observer<List<DetectedText>> {
    private val viewModel by lazy { ViewModelProviders.of(this).get(HistoryViewModel::class.java) }

    private lateinit var recyclerView: RecyclerView
    private lateinit var historyAdapter: HistoryAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_history, container, false)
        initView(view)
        initAdapter()
        return view
    }

    private fun initView(view: View) {
        recyclerView = view.history_list
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    private fun initAdapter() {
        viewModel.historyLiveData.observe(this, this)
        historyAdapter = HistoryAdapter(viewModel.historyLiveData.value)
        recyclerView.adapter = historyAdapter

    }

    override fun onChanged(t: List<DetectedText>?) {
        historyAdapter.data = t
        historyAdapter.notifyDataSetChanged()
    }
}
