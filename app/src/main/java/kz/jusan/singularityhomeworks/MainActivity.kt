package kz.jusan.singularityhomeworks

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

const val CORRECT_PIN = "1567"
const val PIN_LENGTH = 4

class MainActivity : AppCompatActivity() {
    private var currPin = ""
    private lateinit var pinEdit : TextView

    var errorColor : Int = Color.BLACK
    var pinColor : Int = Color.BLACK

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
        pinEdit = findViewById(R.id.pinCodeEdit)
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
        val btnDelete : TextView = findViewById(R.id.btnDelete)
        btnDelete.setOnClickListener{
            currPin = currPin.dropLast(1)
            updatePinEdit()
        }
    }

    private fun initOK() {
        val btnOK : TextView = findViewById(R.id.btnOK)
        btnOK.setOnClickListener{
            checkCorrectness()
        }
    }

    private fun onNumButtonClick(view : View) {
        if(view !is TextView)
            return

        val enteredNum = view.text
        currPin += enteredNum
        updatePinEdit()
    }


    private fun updatePinEdit() {
        if(currPin.length > PIN_LENGTH) {
            currPin = currPin.substring(0, PIN_LENGTH)
        }
        pinEdit.setText(currPin)
        pinEdit.setTextColor(pinColor)
    }

    private fun checkCorrectness() {
        if(currPin.equals(CORRECT_PIN)) {
            Toast.makeText(this, R.string.correct_pin_toast, Toast.LENGTH_LONG).show()
            val intent = Intent(this, ResultActivity::class.java)
            startActivity(intent)
        } else
            pinEdit.setTextColor(errorColor)
    }
}