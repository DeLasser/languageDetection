package ru.mininn.languagedetection.ui.history

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.item_card.view.*
import ru.mininn.languagedetection.data.model.DetectedText

class HistoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bind(item: DetectedText) {
        itemView.item_text.text = item.language
        itemView.item_language.text = item.text
    }
}