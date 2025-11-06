package com.merabills.question3.utils

import android.graphics.Color
import com.merabills.question3.models.MyRow
import com.merabills.question3.models.MySquare

object MyUtil {

    fun generateSquareGrid(totalSquaresToGenerate: Int = SQUARE_TO_GENERATE): ArrayList<MyRow> {
        var currentSquare = 0
        val tempRows = arrayListOf<MyRow>()

        while (currentSquare < totalSquaresToGenerate) {

            val currentRowCount = MyUtil.getRowCount()
            val currentRow = MyRow(arrayListOf<MySquare>())

            val tempListOfSquares = arrayListOf<MySquare>()
            for (i in 0 until currentRowCount) {
                if (currentSquare >= totalSquaresToGenerate) {
                    break
                }
                tempListOfSquares.add(MyUtil.getSquare(currentSquare++))
            }
            currentRow.squares = tempListOfSquares

            tempRows.add(currentRow)
        }

        return tempRows
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
    fun getRowCount(maxSquaresPerRow: Int = MAX_SQUARE_PER_ROW): Int {
        val count = (Math.random() * (maxSquaresPerRow + 1)).toInt()

        return if (count > 0) count else 1
    }

}