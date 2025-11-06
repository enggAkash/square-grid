package com.merabills.question3.ui

import android.util.Log
import com.merabills.question3.viewmodels.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private const val TAG = "GridCoordinator"

class GridCoordinator(
    private val viewModel: MainViewModel,
    private val adapter: VerticalRecyclerViewAdapter,
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Default)
) {

    /**
     * Generates a new grid and updates the adapter on the main thread.
     * @param onComplete Optional callback executed after grid is generated and adapter is updated.
     */
    fun generateGridAndShow(onComplete: (() -> Unit)? = null) {
        coroutineScope.launch {
            viewModel.generateSquareGrid()
//            Log.d(TAG, "generateGridAndShow: ${viewModel.squareGrid}")
//
//            for (i in 0 until viewModel.squareGrid.size) {
//                Log.d(TAG, "generateGridAndShow: Row[$i] size {${viewModel.squareGrid[i].squares.size}}]")
//            }

            withContext(Dispatchers.Main) {
                adapter.resetList(viewModel.squareGrid)
                onComplete?.invoke()
            }
        }
    }

    /**
     * Updates the adapter with the existing grid from the ViewModel.
     */
    fun updateAdapterWithExistingGrid() {
        adapter.resetList(viewModel.squareGrid)
    }
}

