package com.merabills.question3.viewmodels

import androidx.lifecycle.ViewModel
import com.merabills.question3.models.MyRow
import com.merabills.question3.utils.MyUtil

class MainViewModel : ViewModel() {

    var squareGrid = arrayListOf<MyRow>()
        private set

    fun generateSquareGrid() {
        squareGrid = MyUtil.generateSquareGrid()
    }
}