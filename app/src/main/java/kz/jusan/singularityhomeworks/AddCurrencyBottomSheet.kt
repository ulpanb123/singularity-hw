package kz.jusan.singularityhomeworks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class AddCurrencyBottomSheet : BottomSheetDialogFragment (){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.bottom_sheet_add, container, false)

    companion object {
        const val TAG = "AddCurrencyBottomSheet"
    }
}