package com.merabills.question3.viewmodels

import android.graphics.Color
import androidx.lifecycle.ViewModel
import com.merabills.question3.models.MyRow
import com.merabills.question3.models.MySquare
import com.merabills.question3.utils.MAX_SQUARE_PER_ROW
import com.merabills.question3.utils.SQUARE_TO_GENERATE

class MainViewModel : ViewModel() {

    var squareGrid = arrayListOf<MyRow>()
        private set

    fun generateSquareGrid() {
        var currentSquare = 0
        val tempRows = arrayListOf<MyRow>()

        while (currentSquare < SQUARE_TO_GENERATE) {

            val currentRowCount = getRowCount()
            val currentRow = MyRow(arrayListOf<MySquare>())

            val tempListOfSquares = arrayListOf<MySquare>()
            for (i in 0 until currentRowCount) {
                if (currentSquare >= SQUARE_TO_GENERATE) {
                    break
                }
                tempListOfSquares.add(getSquare(currentSquare++))
            }
            currentRow.squares = tempListOfSquares

            tempRows.add(currentRow)
        }

        squareGrid = tempRows
    }

    fun getSquare(squareNumber: Int) = MySquare(squareNumber, getBgColor())

    /**
     * @return Random Background color as @ColorInt.
     */
    fun getBgColor(): Int {
        val predefinedColors =
            intArrayOf(
                Color.GRAY,
                Color.RED,
                Color.CYAN,
                Color.LTGRAY,
                Color.MAGENTA,
                Color.YELLOW,
                Color.GREEN
            )

        return predefinedColors[(Math.random() * predefinedColors.count()).toInt()]
    }

    /**
     * @return Random integer value between 1(inclusive) and 100(inclusive)
     */
    fun getRowCount(): Int {
        val count = (Math.random() * (MAX_SQUARE_PER_ROW + 1)).toInt()

        return if (count > 0) count else 1
    }
}