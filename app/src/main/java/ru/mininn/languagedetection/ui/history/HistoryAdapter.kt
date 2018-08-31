package ru.mininn.languagedetection.ui.history

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import ru.mininn.languagedetection.R
import ru.mininn.languagedetection.data.model.DetectedText

class HistoryAdapter(var data: List<DetectedText>?) : RecyclerView.Adapter<HistoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        return HistoryViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_card, parent, false))
    }

    override fun getItemCount(): Int {
        return if (data != null) {
            data!!.size
        } else {
            0
        }
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        holder.bind(data!![position])
    }
}