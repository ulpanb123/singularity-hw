package kz.jusan.singularityhomeworks.converter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.textfield.TextInputEditText
import kz.jusan.singularityhomeworks.R

class AddCurrencyBottomSheet : BottomSheetDialogFragment() {

    private lateinit var addButton: TextView
    private lateinit var currencyNameEditText: TextInputEditText
    private lateinit var inTengeEditText: TextInputEditText

    interface BottomSheetListener {
        fun onAddClicked(name: String, amount: String)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.bottom_sheet_add, container, false)

        with(view) {
            addButton = findViewById(R.id.btn_add)
            currencyNameEditText = findViewById(R.id.et_curr_name)
            inTengeEditText = findViewById(R.id.et_amount)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addButton.setOnClickListener {
            (parentFragment as? BottomSheetListener)?.onAddClicked(
                name = currencyNameEditText.text.toString(),
                amount = inTengeEditText.text.toString()
            )
            dismiss()
        }
    }

}