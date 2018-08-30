package ru.mininn.languagedetection.ui.detection

import android.app.AlertDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ru.mininn.languagedetection.R
import ru.mininn.languagedetection.data.model.DetectionHolder

class DetectionFragment : Fragment(), Observer<DetectionHolder> {
    private val viewModel = ViewModelProviders.of(this).get(DetectViewModel::class.java)

    private lateinit var textView: TextView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_detect, container, false)
        initViews(view)
        viewModel.detectedTextLiveData.observe(this, this)
        return view
    }

    private fun showDialog(data: DetectionHolder) {
        val dialogBuilder: AlertDialog.Builder = AlertDialog.Builder(this.context)
        if (data.detectedText != null) {
            dialogBuilder.setTitle(data.detectedText!!.language)
            dialogBuilder.setMessage(data.detectedText!!.text)
        } else if (data.error != null) {
            dialogBuilder.setTitle(getString(R.string.error))
            dialogBuilder.setMessage(data.error!!.message)
        }
        dialogBuilder.setPositiveButton("Ok"){dialog, which ->
            dialog.dismiss()
        }
    }

    override fun onChanged(t: DetectionHolder?) {
        if(t!= null) {
            showDialog(t)
        }
    }

    private fun initViews(view: View) {
        textView = view.findViewById(R.id.translatable_text)
        view.findViewById<FloatingActionButton>(R.id.translatable_text)
                .setOnClickListener {
                    viewModel.detectLanguage(textView.text.toString())
                }
    }
}