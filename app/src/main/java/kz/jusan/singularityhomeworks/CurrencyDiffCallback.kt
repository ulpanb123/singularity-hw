package kz.jusan.singularityhomeworks

import androidx.recyclerview.widget.DiffUtil

class CurrencyDiffCallback : DiffUtil.Callback() {
    private var oldList = emptyList<Currency>()
    private var newList = emptyList<Currency>()

    fun setItems(oldList: List<Currency>, newList: List<Currency>) {
        this.oldList = oldList
        this.newList = newList
    }

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].info == newList[newItemPosition].info

    override fun areContentsTheSame(oldPosition: Int, newPosition: Int): Boolean {
        return oldList[oldPosition] == newList[newPosition]
    }

    override fun getChangePayload(oldPosition: Int, newPosition: Int): Any? {
        val fields = mutableSetOf<CurrencyPayload>()
        val oldItem = oldList[oldPosition]
        val newItem = newList[newPosition]

        if (oldItem.info != newItem.info) fields.add(CurrencyPayload.INFO)
        if (oldItem.flag != newItem.flag) fields.add(CurrencyPayload.FLAG)
        if (oldItem.amount != newItem.amount) fields.add(CurrencyPayload.AMOUNT)

        return when {
            fields.isNotEmpty() -> fields
            else -> super.getChangePayload(oldPosition, newPosition) // null
        }
    }
}