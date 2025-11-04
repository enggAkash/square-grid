package com.merabills.question3.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.merabills.question3.databinding.VerticalVhBinding
import com.merabills.question3.models.MyRow
import com.merabills.question3.models.MySquare


class VerticalRecyclerViewAdapter(val squareGrid: ArrayList<MyRow>) :
    RecyclerView.Adapter<VerticalVh>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VerticalVh {
        return VerticalVh(
            VerticalVhBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: VerticalVh,
        position: Int
    ) {
        holder.bind(squareGrid[position])
    }

    override fun getItemCount() = squareGrid.size

    fun resetList(newSquareGrid: ArrayList<MyRow>) {
        this.squareGrid.clear()
        this.squareGrid.addAll(newSquareGrid)
        notifyDataSetChanged()
    }
}

class VerticalVh(private val itemBinding: VerticalVhBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {
    val horizontalRecyclerViewAdapter: HorizontalRecyclerViewAdapter =
        HorizontalRecyclerViewAdapter(arrayListOf<MySquare>())

    init {
        itemBinding.horizontalRv.adapter = horizontalRecyclerViewAdapter
    }

    fun bind(myRow: MyRow) {

        val filteredMyRow = ArrayList(myRow.squares.filter { it.isAdded })
        horizontalRecyclerViewAdapter.resetList(filteredMyRow)
    }

}