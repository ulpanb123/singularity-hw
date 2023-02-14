package kz.jusan.singularityhomeworks

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get

const val CORRECT_PIN = "1567"
const val PIN_LENGTH = 4

class MainActivity : AppCompatActivity() {
    private var currPin = ""
    private lateinit var pinEdit: PinCompoundView

    var errorColor: Int = Color.BLACK
    var pinColor: Int = Color.BLACK

    private var currEnteredNum = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initColors()
        initPinEdit()
        initNums()
        initDeleteBtn()
        initOK()
    }

    private fun initColors() {
        errorColor = ContextCompat.getColor(this, R.color.color_error)
        pinColor = ContextCompat.getColor(this, R.color.blue_light)
    }

    private fun initPinEdit() {
        pinEdit = findViewById(R.id.pin_compound)

    }

    private fun initNums() {
        val btnOne: TextView = findViewById(R.id.btn1)
        btnOne.setOnClickListener(this::onNumButtonClick)

        val btnTwo: TextView = findViewById(R.id.btn2)
        btnTwo.setOnClickListener(this::onNumButtonClick)

        val btnThree: TextView = findViewById(R.id.btn3)
        btnThree.setOnClickListener(this::onNumButtonClick)

        val btnFour: TextView = findViewById(R.id.btn4)
        btnFour.setOnClickListener(this::onNumButtonClick)

        val btnFive: TextView = findViewById(R.id.btn5)
        btnFive.setOnClickListener(this::onNumButtonClick)

        val btnSix: TextView = findViewById(R.id.btn6)
        btnSix.setOnClickListener(this::onNumButtonClick)

        val btnSeven: TextView = findViewById(R.id.btn7)
        btnSeven.setOnClickListener(this::onNumButtonClick)

        val btnEight: TextView = findViewById(R.id.btn8)
        btnEight.setOnClickListener(this::onNumButtonClick)

        val btnNine: TextView = findViewById(R.id.btn9)
        btnNine.setOnClickListener(this::onNumButtonClick)

        val btnZero: TextView = findViewById(R.id.btn0)
        btnZero.setOnClickListener(this::onNumButtonClick)
    }

    private fun initDeleteBtn() {
        val btnDelete: TextView = findViewById(R.id.btnDelete)
        btnDelete.setOnClickListener {
            currPin = currPin.dropLast(1)
            currEnteredNum--
            updatePinEdit("")
        }

        btnDelete.setOnLongClickListener {
            currPin = ""
            clearAllEntries()
            currEnteredNum = 0
            true
        }
    }

    private fun initOK() {
        val btnOK: TextView = findViewById(R.id.btnOK)
        btnOK.setOnClickListener {
            checkCorrectness()
        }
    }

    private fun onNumButtonClick(view: View) {
        if (view !is TextView)
            return

        val enteredNum = view.text
        currPin += enteredNum
        currEnteredNum++

        updatePinEdit(enteredNum.toString())
    }


    private fun updatePinEdit(enteredNum : String) {
        if (currPin.length > PIN_LENGTH) {
            currPin = currPin.substring(0, PIN_LENGTH)
        }

        when(currEnteredNum) {
            1 -> {
                pinEdit.setEntryPin1(enteredNum)
                pinEdit.entryBtn1.setBorderVisible(pinColor)
            }
            2 -> {
                pinEdit.setEntryPin2(enteredNum)
                pinEdit.entryBtn2.setBorderVisible(pinColor)
            }
            3 -> {
                pinEdit.setEntryPin3(enteredNum)
                pinEdit.entryBtn3.setBorderVisible(pinColor)
            }
            4 -> {
                pinEdit.setEntryPin4(enteredNum)
                pinEdit.entryBtn4.setBorderVisible(pinColor)
            }
        }

    }

    private fun clearAllEntries() {
        with(pinEdit) {
            entryBtn1
            entryBtn2
            entryBtn3
            entryBtn4
        }.setBorderInvisible()

        with(pinEdit) {
            entryBtn1
            entryBtn2
            entryBtn3
            entryBtn4
        }.text = ""


    }

    private fun checkCorrectness() {
        if (currPin.equals(CORRECT_PIN))
            Toast.makeText(this, R.string.correct_pin_toast, Toast.LENGTH_LONG).show()
//        else
//            pinEdit.setTextColor(errorColor)
    }
}