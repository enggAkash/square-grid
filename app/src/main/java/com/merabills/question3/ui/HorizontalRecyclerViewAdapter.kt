package com.merabills.question3.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.merabills.question3.databinding.HorizontalVhBinding
import com.merabills.question3.models.MySquare


class HorizontalRecyclerViewAdapter(val rowSquares: ArrayList<MySquare>) :
    RecyclerView.Adapter<HorizontalVh>() {

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

    override fun onBindViewHolder(viewHolder: HorizontalVh, position: Int) {
        viewHolder.bind(rowSquares[position])
    }

    override fun getItemCount() = rowSquares.size

    fun resetList(newRowSquares: ArrayList<MySquare>) {
        this.rowSquares.clear()
        this.rowSquares.addAll(newRowSquares)
        notifyDataSetChanged()
    }
}

class HorizontalVh(val itemBinding: HorizontalVhBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    fun bind(mySquare: MySquare) {
        itemBinding.squareTv.text = mySquare.text.toString()
        itemBinding.squareTv.setBackgroundColor(mySquare.bgColor)
    }

}