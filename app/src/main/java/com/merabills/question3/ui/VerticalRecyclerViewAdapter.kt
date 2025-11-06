package com.merabills.question3.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.merabills.question3.databinding.VerticalVhBinding
import com.merabills.question3.models.MyRow
import com.merabills.question3.models.MySquare


class VerticalRecyclerViewAdapter(
    private val squareGrid: ArrayList<MyRow>,
    private val onRowItemDelete: (Int, Int) -> Unit,
    private val onRowRemoved: (Int) -> Unit
) :
    RecyclerView.Adapter<VerticalRecyclerViewAdapter.VerticalVh>() {
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
        holder.bind(position)
    }

    override fun getItemCount() = squareGrid.size

    fun deleteItem(position: Int) {
        squareGrid.removeAt(position)
        notifyItemRemoved(position)

        onRowRemoved.invoke(position)
    }

    fun resetList(newSquareGrid: ArrayList<MyRow>) {
        this.squareGrid.clear()
        this.squareGrid.addAll(newSquareGrid)
        notifyDataSetChanged()
    }

    inner class VerticalVh(private val itemBinding: VerticalVhBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        val horizontalRecyclerViewAdapter: HorizontalRecyclerViewAdapter =
            HorizontalRecyclerViewAdapter(
                rowSquares = arrayListOf<MySquare>(),
                onRowItemDelete = { itemPosition ->
                    onRowItemDelete(bindingAdapterPosition, itemPosition)
                },
                onRowEmpty = {
                    deleteItem(bindingAdapterPosition)
                })

        init {
            itemBinding.horizontalRv.adapter = horizontalRecyclerViewAdapter
            itemBinding.horizontalRv.setHasFixedSize(true)
        }

        fun bind(position: Int) {
            horizontalRecyclerViewAdapter.resetList(squareGrid[position].squares)
        }

    }
}