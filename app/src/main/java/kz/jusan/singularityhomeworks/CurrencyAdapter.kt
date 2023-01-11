package kz.jusan.singularityhomeworks

import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

class CurrencyAdapter(
    private val layoutInflater: LayoutInflater,
    private val itemTouchDelegate: ItemTouchDelegate,
    private val onLongClickListener: OnLongClickListener
) : RecyclerView.Adapter<CurrencyViewHolder>() {

    interface OnLongClickListener {
        fun onLongClick(itemView: View, currency: Currency)
    }

    private val currencies: MutableList<Currency> = mutableListOf()
    private val diffCallback = CurrencyDiffCallback()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val layoutId = R.layout.item_currency
        val view = layoutInflater.inflate(layoutId, parent, false)
        val viewholder = CurrencyViewHolder(view)

        viewholder.itemView.setOnTouchListener { _, motionEvent ->
            if (motionEvent.actionMasked == MotionEvent.ACTION_DOWN) {
                itemTouchDelegate.startDragging(viewholder)
            }
            return@setOnTouchListener true
        }
        return viewholder
    }

    override fun getItemCount(): Int {
        return currencies.size
    }

    fun updateDataWithDiffCallback(newData: List<Currency>) {
        Log.e("CurrencyAdapter", newData.toString())

        diffCallback.setItems(currencies, newData)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        currencies.clear()
        currencies.addAll(newData)
        diffResult.dispatchUpdatesTo(this)
    }

    fun addItemToPosition(currency: Currency, pos: Int) {
        currencies.add(pos, currency)
        notifyItemInserted(pos)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(currency = currencies[position], onLongClickListener)
    }

    fun moveItem(from: Int, to: Int) {
        val fromCurr = currencies[from]
        currencies.removeAt(from)
        if (to < from) {
            currencies.add(to, fromCurr)
        } else {
            currencies.add(to - 1, fromCurr)
        }
    }

    fun deleteCurrency(position: Int) {
        currencies.removeAt(position)
        notifyItemRemoved(position)
    }

    fun deleteByLongClick(currency: Currency) {
        currencies.remove(currency)
        notifyItemRemoved(currencies.indexOf(currency))
    }

    fun sortAlphabetically() {
        currencies.sortBy {
            it.info
        }
        notifyDataSetChanged()
    }

    fun sortByAmount() {
        currencies.sortBy {
            it.amount
        }
        notifyDataSetChanged()
    }

    fun reset() {
        currencies.clear()
        notifyDataSetChanged()
    }
}