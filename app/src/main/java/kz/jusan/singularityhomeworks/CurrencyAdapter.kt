package kz.jusan.singularityhomeworks

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

private const val CURRENCY_VIEW_TYPE = 1
private const val ADD_VIEW_TYPE = 2


class CurrencyAdapter(private val layoutInflater: LayoutInflater) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val currencies : MutableList<Currency> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutId = if(viewType == ADD_VIEW_TYPE) {
            R.layout.item_btn
        } else {
            R.layout.item_currency
        }
        val view = layoutInflater.inflate(layoutId, parent, false)

        return if(viewType == ADD_VIEW_TYPE) {
            AddButtonViewHolder(view)
        } else
            CurrencyViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return if(position == currencies.size)
            ADD_VIEW_TYPE
        else
            CURRENCY_VIEW_TYPE
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if(holder is CurrencyViewHolder) {
            holder.bind(currency = currencies[position])
        }
    }

    override fun getItemCount(): Int {
        return currencies.size + 1
    }

    fun updateData(newData : List<Currency>) {
        Log.e("CurrencyAdapter", newData.toString())
        currencies.clear()
        currencies.addAll(newData)
    }
}