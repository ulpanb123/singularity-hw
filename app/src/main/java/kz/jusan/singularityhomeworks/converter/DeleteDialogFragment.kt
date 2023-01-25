package kz.jusan.singularityhomeworks.converter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import kz.jusan.singularityhomeworks.R

class DeleteDialogFragment(private val onDeleteListener: OnDeleteListener) : DialogFragment() {

    interface OnDeleteListener {
        fun onDelete()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_delete, container, false)

        with(view) {
            findViewById<TextView>(R.id.cancel_button).setOnClickListener {
                dismiss()
            }
            findViewById<TextView>(R.id.delete_button).setOnClickListener {
                onDeleteListener.onDelete()
                dismiss()
            }

        }
        return view
    }
}