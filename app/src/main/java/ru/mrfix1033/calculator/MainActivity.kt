package ru.mrfix1033.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private lateinit var clearButton: Button
    private lateinit var exitButton: Button

    private lateinit var firstOperandET: EditText
    private lateinit var secondOperandET: EditText

    private lateinit var plusButton: Button
    private lateinit var minusButton: Button

    private lateinit var resultTextView: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setVariables()
        setClickListeners()
    }

    private fun onClickOnOperators(v: View?) {
        if (firstOperandET.text.isEmpty() || secondOperandET.text.isEmpty()) {
            resultTextView.text = "Заполните оба поля ввода"
            return
        }

        val operation = Operation(firstOperandET.text.toString(), secondOperandET.text.toString())

        val result = when (v) {
            plusButton -> try {
                operation.plus()
            } catch (exception: IllegalArgumentException) {
                exception.message
            }
            minusButton -> try {
                operation.minus()
            } catch (exception: IllegalArgumentException) {
                exception.message
            }
            else -> {
                resultTextView.text = "Некорректная операция"
                return
            }
        }
        resultTextView.text = result
    }

    private fun onClickOnUtilsButton(v: View?) = when (v) {
        clearButton -> {
            firstOperandET.text.clear()
            secondOperandET.text.clear()
            resultTextView.text = "Результат"
        }
        exitButton -> finish()
        else -> resultTextView.text = "Некорректная операция"
    }

    private fun setVariables() {
        clearButton = findViewById(R.id.clearButton)
        exitButton = findViewById(R.id.exitButton)

        firstOperandET = findViewById(R.id.firstOperandET)
        secondOperandET = findViewById(R.id.secondOperandET)

        plusButton = findViewById(R.id.plusButton)
        minusButton = findViewById(R.id.minusButton)

        resultTextView = findViewById(R.id.resultTextView)
    }

    private fun setClickListeners() {
        clearButton.setOnClickListener(::onClickOnUtilsButton)
        exitButton.setOnClickListener(::onClickOnUtilsButton)

        plusButton.setOnClickListener(::onClickOnOperators)
        minusButton.setOnClickListener(::onClickOnOperators)
    }
}