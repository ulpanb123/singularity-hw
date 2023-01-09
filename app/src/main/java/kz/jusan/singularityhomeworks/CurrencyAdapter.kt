package kz.jusan.singularityhomeworks

import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class CurrencyAdapter(private val layoutInflater: LayoutInflater,
                      private val itemTouchDelegate: ItemTouchDelegate) : RecyclerView.Adapter<CurrencyViewHolder>() {
    private val currencies : MutableList<Currency> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val layoutId = R.layout.item_currency
        val view = layoutInflater.inflate(layoutId, parent, false)
        val viewholder = CurrencyViewHolder(view)

        viewholder.itemView.setOnTouchListener { _, motionEvent ->
            if(motionEvent.actionMasked == MotionEvent.ACTION_DOWN) {
                itemTouchDelegate.startDragging(viewholder)
            }
            return@setOnTouchListener true
        }
        return viewholder
    }

    override fun getItemCount(): Int {
        return currencies.size
    }

    fun updateData(newData : List<Currency>) {
        Log.e("CurrencyAdapter", newData.toString())
        currencies.clear()
        currencies.addAll(newData)
        notifyDataSetChanged()
    }

    fun addItemToPosition(currency: Currency, pos : Int) {
        currencies.add(pos, currency)
        notifyItemInserted(pos)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(currency = currencies[position])
    }

    fun moveItem(from : Int, to : Int) {
        val fromCurr = currencies[from]
        currencies.removeAt(from)
        if(to < from) {
            currencies.add(to, fromCurr)
        } else {
            currencies.add(to-1, fromCurr)
        }
    }

    fun deleteCurrency(position : Int) {
        currencies.removeAt(position)

    }
}