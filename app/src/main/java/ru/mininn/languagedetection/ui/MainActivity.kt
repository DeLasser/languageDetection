package ru.mininn.languagedetection.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import ru.mininn.languagedetection.R
import ru.mininn.languagedetection.data.model.DetectedText
import ru.mininn.languagedetection.ui.detection.DetectViewModel


class MainActivity : AppCompatActivity(), Observer<DetectedText>{
    private val viewModel by lazy { ViewModelProviders.of(this).get(DetectViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("asdasd","value" + viewModel.detectedTextLiveData.value?.language)
        viewModel.detectedTextLiveData.observe(this,this)
        this.translate_fab.setOnClickListener {
            viewModel.detectLanguage(this.translatable_text.text.toString())
        }
    }

    override fun onChanged(t: DetectedText?) {
        Log.d("asdasd","onChanged" + viewModel.detectedTextLiveData.value?.language)
    }

}
