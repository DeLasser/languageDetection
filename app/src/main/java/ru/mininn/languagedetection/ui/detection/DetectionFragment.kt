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
    private val viewModel by lazy { ViewModelProviders.of(this).get(DetectViewModel::class.java) }

    private lateinit var textView: TextView
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        retainInstance = false
        val view = inflater.inflate(R.layout.fragment_detect, container, false)
        initViews(view)
        viewModel.detectedTextLiveData.observe(this, this)
        return view
    }

    private fun showDialog(title: String, message: String) {
        AlertDialog.Builder(this.context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("Ok") { dialog, which ->
                    dialog.dismiss()
                }
                .create()
                .show()
    }

    override fun onChanged(data: DetectionHolder?) {
        if (data != null) {
            if (!viewModel.isResultShown) {
                if (data.detectedText != null) {
                    showDialog(data.detectedText!!.language!!, data.detectedText!!.text!!)
                } else if (data.error != null) {
                    showDialog(getString(R.string.error), data.error!!.message!!)
                }
            }
            viewModel.isResultShown = true
        }
    }

    private fun initViews(view: View) {
        textView = view.findViewById(R.id.translatable_text)
        view.findViewById<FloatingActionButton>(R.id.translate_fab)
                .setOnClickListener {
                    if (textView.text.isNotEmpty()) {
                        viewModel.detectLanguage(textView.text.toString())
                    } else {
                        showDialog(getString(R.string.error), getString(R.string.enter_text))
                    }
                }
    }
}