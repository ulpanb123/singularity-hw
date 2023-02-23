package kz.jusan.singularityhomeworks.converter

import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.core.app.ActivityCompat.invalidateOptionsMenu
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kz.jusan.singularityhomeworks.ItemTouchDelegate
import kz.jusan.singularityhomeworks.R
import kz.jusan.singularityhomeworks.RetrofitBuilder

private const val API_KEY = "uLBF5RFMHhabvS7WaKbwzcgCpT3hdVso"

class ConverterFragment : Fragment(), ItemTouchDelegate,
    AddCurrencyBottomSheet.BottomSheetListener {

    private lateinit var currencyAdapter: CurrencyAdapter
    private lateinit var rvCurrency: RecyclerView
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private var isDeleteClicked: Boolean = false
    private lateinit var itemToDelete: Currency
    private var currentProp: MutableList<Double> = mutableListOf()
    private var isDataAvailable = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_converter, container, false)
    }

    private val itemTouchHelper by lazy {
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView(view)
        makeApiRequest()
        populateWithData()
        initAddButton(view)
        initToolbar(view)
        initPinnedEt(view)
    }

    private fun initToolbar(view: View) {
        toolbar = requireActivity().findViewById(R.id.toolbar)
    }

    private fun initRecyclerView(view: View) {
        currencyAdapter =
            CurrencyAdapter(layoutInflater, this, object : CurrencyAdapter.OnLongClickListener {
                override fun onLongClick(itemView: View, currency: Currency) {
                    isDeleteClicked = true
                    itemToDelete = currency
                    invalidateOptionsMenu(requireActivity())
                }

            })
        val layoutManager = LinearLayoutManager(requireContext())
        rvCurrency = view.findViewById(R.id.rv_main)

        rvCurrency.adapter = currencyAdapter
        rvCurrency.layoutManager = layoutManager
        itemTouchHelper.attachToRecyclerView(rvCurrency)
    }

    private fun populateWithData() {
        val currencies = mutableListOf<Currency>()

        currencies.add(Currency("", R.drawable.img_kz, "Тенге, Казахстан"))
        currencies.add(Currency("", R.drawable.img_us, "Доллары, США"))
        currencies.add(Currency("", R.drawable.img_tr, "Лира, Турция"))
        currencies.add(Currency("", R.drawable.img_eu, "Евро, EC"))

        currencyAdapter.updateDataWithDiffCallback(currencies)
    }

    private fun updateCurrencies(amount: Double) {
        val currencies = mutableListOf<Currency>()

        currencies.add(Currency(amount.toString(), R.drawable.img_kz, "Тенге, Казахстан"))
        currencies.add(
            Currency(
                (amount * currentProp[1]).toString(),
                R.drawable.img_us,
                "Доллары, США"
            )
        )
        currencies.add(
            Currency(
                (amount * currentProp[2]).toString(),
                R.drawable.img_tr,
                "Лира, Турция"
            )
        )
        currencies.add(
            Currency(
                (amount * currentProp[3]).toString(),
                R.drawable.img_eu,
                "Евро, EC"
            )
        )

        currencyAdapter.updateDataWithDiffCallback(currencies)
    }

    private fun makeApiRequest() {
        currentProp.add(1.0)

        MainScope().launch {
            withContext(Dispatchers.Default) {
                RetrofitBuilder.apiService.convertCurrency(API_KEY, "KZT", "USD", 1.0).body()
                    .apply {
                        currentProp.add(1, this!!.result)
                    }
                RetrofitBuilder.apiService.convertCurrency(API_KEY, "KZT", "TRY", 1.0).body()
                    .apply {
                        currentProp.add(2, this!!.result)
                    }

                RetrofitBuilder.apiService.convertCurrency(API_KEY, "KZT", "EUR", 1.0).body()
                    .apply {
                        currentProp.add(3, this!!.result)
                    }
                isDataAvailable = true
            }
        }
    }

    private fun initAddButton(view: View) {
        val btnAdd: Button = view.findViewById(R.id.btn_add)
        btnAdd.setOnClickListener {
            val addBottomSheet = AddCurrencyBottomSheet()
            addBottomSheet.show(childFragmentManager, null)
        }
    }

    private fun initPinnedEt(view: View) {
        val pinnedEt: TextInputEditText = view.findViewById(R.id.et_amount)
        pinnedEt.doOnTextChanged { text, start, before, count ->
            if (text.isNullOrEmpty()) {
                populateWithData()
                return@doOnTextChanged
            }
            updateCurrencies(amount = text.toString().toDouble())
        }
    }

    override fun startDragging(viewholder: RecyclerView.ViewHolder) {
        itemTouchHelper.startDrag(viewholder)
    }

    override fun startSwiping(viewholder: RecyclerView.ViewHolder) {
        itemTouchHelper.startSwipe(viewholder)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val inflater: MenuInflater = requireActivity().menuInflater
        if (isDeleteClicked) {
            inflater.inflate(R.menu.menu_delete, menu)
            toolbar.title = "Item selected"
        } else {
            inflater.inflate(R.menu.menu_converter, menu)
            toolbar.title = "Converter"
        }
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
                showDeleteDialog()
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

    private fun resetSort() {
        currencyAdapter.reset()
        populateWithData()
    }

    private fun showDeleteDialog() {

        DeleteDialogFragment(object : DeleteDialogFragment.OnDeleteListener {
            override fun onDelete() {
                currencyAdapter.deleteByLongClick(itemToDelete)
            }
        }).show(parentFragmentManager, null)
        isDeleteClicked = false
        invalidateOptionsMenu(activity)
    }

    override fun onAddClicked(name: String, amount: String) {
        val newCurrency = Currency(amount = amount, flag = R.drawable.img_kz, info = name)
        val position = 0
        currencyAdapter.addItemToPosition(currency = newCurrency, pos = position)

        val smoothScroller = object : LinearSmoothScroller(requireContext()) {
            override fun getVerticalSnapPreference(): Int = LinearSmoothScroller.SNAP_TO_START

        }
        smoothScroller.targetPosition = position

        rvCurrency.layoutManager?.startSmoothScroll(smoothScroller)
    }
}