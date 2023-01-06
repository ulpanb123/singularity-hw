package kz.jusan.singularityhomeworks

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

//private const val CURRENCY_VIEW_TYPE = 1
//private const val ADD_VIEW_TYPE = 2
//

class CurrencyAdapter(private val layoutInflater: LayoutInflater) : RecyclerView.Adapter<CurrencyViewHolder>() {
    private val currencies : MutableList<Currency> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val layoutId = R.layout.item_currency
        val view = layoutInflater.inflate(layoutId, parent, false)
        return CurrencyViewHolder(view)
    }

//    override fun getItemViewType(position: Int): Int {
//        return if(position == currencies.size)
//            ADD_VIEW_TYPE
//        else
//            CURRENCY_VIEW_TYPE
//    }


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
}