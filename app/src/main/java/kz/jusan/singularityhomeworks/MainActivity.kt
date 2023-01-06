package kz.jusan.singularityhomeworks

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var currencyAdapter : CurrencyAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        populateWithData()
        initAddButton()
    }

    private fun initRecyclerView() {
        currencyAdapter = CurrencyAdapter(layoutInflater)
        val layoutManager = LinearLayoutManager(this)
        val rvCurrency : RecyclerView = findViewById(R.id.rv_main)

        rvCurrency.adapter = currencyAdapter
        rvCurrency.layoutManager = layoutManager
    }

    private fun populateWithData() {
        val currencies  = mutableListOf<Currency>()
        currencies.add(Currency("1 500 000", R.drawable.img_kz, "Тенге, Казахстан"))
        currencies.add(Currency("2 3450", R.drawable.img_us, "Доллары, США"))
        currencies.add(Currency("2 3450", R.drawable.img_tr, "Лира, Турция"))
        currencies.add(Currency("2 3450", R.drawable.img_eu, "Евро, EC"))

        currencyAdapter.updateData(currencies)
    }

    private fun initAddButton() {
        val btnAdd : Button = findViewById(R.id.btn_add)
        btnAdd.setOnClickListener{
            val newCurrency = Currency("1 500 000", R.drawable.img_kz, "Тенге, Казахстан")
            currencyAdapter.addItemToPosition(currency = newCurrency, pos = 0)
        }
    }


}