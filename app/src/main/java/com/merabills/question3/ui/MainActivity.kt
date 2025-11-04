package com.merabills.question3.ui

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.MutableLiveData
import androidx.metrics.performance.JankStats
import com.merabills.question3.R
import com.merabills.question3.databinding.ActivityMainBinding
import com.merabills.question3.viewmodels.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicInteger
import kotlin.getValue

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    //region Jank stats tracking - do not modify
    private lateinit var jankStatsTracker: JankStats
    private val totalJankyFrames = AtomicInteger(0)
    //endregion

    private val viewModel by viewModels<MainViewModel>()
    private val defaultScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //region Jank stats tracking - do not modify
        val totalJankyFramesLiveData = MutableLiveData(0)
        findViewById<Button>(R.id.resetButton).setOnClickListener {
            totalJankyFrames.set(0)
            totalJankyFramesLiveData.value = totalJankyFrames.get()
        }

        val jankStatsListener = JankStats.OnFrameListener { frameData ->
            if (frameData.isJank)
                totalJankyFramesLiveData.postValue(totalJankyFrames.incrementAndGet())
        }
        jankStatsTracker = JankStats.Companion.createAndTrack(window, jankStatsListener)

        val jankStatsTextView = findViewById<TextView>(R.id.jankStatsTextView)
        totalJankyFramesLiveData.observe(this) { jankyFrames ->
            jankStatsTextView.text = getString(R.string.jank_stats, jankyFrames)
        }
        //endregion


        val adapter = VerticalRecyclerViewAdapter(arrayListOf())
        mainBinding.verticalRv.adapter = adapter

        defaultScope.launch {
            viewModel.generateSquareGrid()
            Log.d(TAG, "onCreate: ${viewModel.squareGrid}")

            for (i in 0 until viewModel.squareGrid.size) {
                Log.d(TAG, "onCreate: Row[$i] size {${viewModel.squareGrid[i].squares.size}}]")
            }

            withContext(Dispatchers.Main) {
                adapter.resetList(viewModel.squareGrid)
            }
        }

    }

    override fun onResume() {
        super.onResume()

        //region Jank stats tracking - do not modify
        if (::jankStatsTracker.isInitialized) {
            jankStatsTracker.isTrackingEnabled = true
        }
        //endregion
    }

    override fun onPause() {
        super.onPause()

        //region Jank stats tracking - do not modify
        if (::jankStatsTracker.isInitialized) {
            jankStatsTracker.isTrackingEnabled = false
        }
        //endregion
    }

}