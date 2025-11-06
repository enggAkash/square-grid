package com.merabills.question3.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.merabills.question3.databinding.HorizontalVhBinding
import com.merabills.question3.models.MySquare


class HorizontalRecyclerViewAdapter(
    val rowSquares: ArrayList<MySquare>,
    val onRowItemDelete: (Int) -> Unit,
    val onRowEmpty: () -> Unit
) :
    RecyclerView.Adapter<HorizontalRecyclerViewAdapter.HorizontalVh>() {

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): HorizontalVh {
        return HorizontalVh(
            HorizontalVhBinding.inflate(
                LayoutInflater.from(viewGroup.context),
                viewGroup,
                false
            )
        )
    }

    override fun onBindViewHolder(
        viewHolder: HorizontalVh,
        position: Int
    ) {
        viewHolder.bind(position)
    }

    override fun getItemCount() = rowSquares.size

    fun deleteItem(position: Int) {
        rowSquares.removeAt(position)
        notifyItemRemoved(position)

        onRowItemDelete.invoke(position)
        if (rowSquares.isEmpty()) {
            onRowEmpty.invoke()
        }
    }

    fun resetList(newRowSquares: ArrayList<MySquare>) {
        this.rowSquares.clear()
        this.rowSquares.addAll(newRowSquares)
        notifyDataSetChanged()
    }

    inner class HorizontalVh(val itemBinding: HorizontalVhBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        init {
            itemBinding.root.setOnClickListener {
                deleteItem(bindingAdapterPosition)
            }
        }

        fun bind(position: Int) {
            itemBinding.squareTv.text = rowSquares[position].text.toString()
            itemBinding.squareTv.setBackgroundColor(rowSquares[position].bgColor)
        }

    }
}