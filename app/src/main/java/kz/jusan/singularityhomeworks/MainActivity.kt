package kz.jusan.singularityhomeworks

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), ItemTouchDelegate {
    private lateinit var currencyAdapter: CurrencyAdapter
    private lateinit var rvCurrency: RecyclerView
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private var isDeleteClicked: Boolean = false
    private lateinit var itemToDelete: Currency


    private val itemTouchHelper by lazy {
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(UP or DOWN, LEFT or RIGHT) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                val from = viewHolder.adapterPosition
                val to = target.adapterPosition
                currencyAdapter.moveItem(from, to)
                currencyAdapter.notifyItemMoved(from, to)

                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val currPosition = viewHolder.adapterPosition
                currencyAdapter.deleteCurrency(currPosition)
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        initRecyclerView()
        populateWithData()
        initAddButton()
        initToolbar()
    }

    private fun initToolbar() {
        toolbar = findViewById(R.id.toolbar)
    }

    private fun initRecyclerView() {
        currencyAdapter =
            CurrencyAdapter(layoutInflater, this, object : CurrencyAdapter.OnLongClickListener {
                override fun onLongClick(itemView: View, currency: Currency) {
                    isDeleteClicked = true
                    itemToDelete = currency
                    invalidateOptionsMenu()
                }

            })
        val layoutManager = LinearLayoutManager(this)
        rvCurrency = findViewById(R.id.rv_main)

        rvCurrency.adapter = currencyAdapter
        rvCurrency.layoutManager = layoutManager
        itemTouchHelper.attachToRecyclerView(rvCurrency)
    }

    private fun populateWithData() {
        val currencies = mutableListOf<Currency>()
        currencies.add(Currency("1 500 000", R.drawable.img_kz, "Тенге, Казахстан"))
        currencies.add(Currency("2 3450", R.drawable.img_us, "Доллары, США"))
        currencies.add(Currency("2 3450", R.drawable.img_tr, "Лира, Турция"))
        currencies.add(Currency("2 3450", R.drawable.img_eu, "Евро, EC"))

        currencyAdapter.updateDataWithDiffCallback(currencies)
    }

    private fun initAddButton() {
        val btnAdd: Button = findViewById(R.id.btn_add)
        btnAdd.setOnClickListener {
            val newCurrency = Currency("1 500 000", R.drawable.img_kz, "Тенге, Казахстан")
            val position = 0
            currencyAdapter.addItemToPosition(currency = newCurrency, pos = position)

            val smoothScroller = object : LinearSmoothScroller(this) {
                override fun getVerticalSnapPreference(): Int = LinearSmoothScroller.SNAP_TO_START

            }
            smoothScroller.targetPosition = position

            rvCurrency.layoutManager?.startSmoothScroll(smoothScroller)
        }
    }

    override fun startDragging(viewholder: RecyclerView.ViewHolder) {
        itemTouchHelper.startDrag(viewholder)
    }

    override fun startSwiping(viewholder: RecyclerView.ViewHolder) {
        itemTouchHelper.startSwipe(viewholder)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        if (isDeleteClicked) {
            inflater.inflate(R.menu.menu_delete, menu)
            toolbar.title = "Item selected"
        } else {
            inflater.inflate(R.menu.menu_main, menu)
            toolbar.title = "Converter"
        }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.sort_by_alphabet -> {
                sortByAlphabet()
                item.isChecked = true
                true
            }
            R.id.sort_by_amount -> {
                sortByAmount()
                item.isChecked = true
                true
            }
            R.id.menu_reset -> {
                resetSort()
                true
            }
            R.id.menu_delete -> {
                currencyAdapter.deleteByLongClick(itemToDelete)
                isDeleteClicked = false
                invalidateOptionsMenu()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun sortByAlphabet() {
        currencyAdapter.sortAlphabetically()
    }

    private fun sortByAmount() {
        currencyAdapter.sortByAmount()
    }

    fun resetSort() {
        currencyAdapter.reset()
        populateWithData()
    }


}