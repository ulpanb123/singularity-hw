package kz.jusan.singularityhomeworks

import androidx.recyclerview.widget.RecyclerView

interface ItemTouchDelegate {
    fun startDragging(viewholder : RecyclerView.ViewHolder)
    fun startSwiping(viewholder: RecyclerView.ViewHolder)
}