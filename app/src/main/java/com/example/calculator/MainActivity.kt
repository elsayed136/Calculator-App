package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Numbers
        tvZero.setOnClickListener { appendOnExpresstion("0", true) }
        tvOne.setOnClickListener { appendOnExpresstion("1", true) }
        tvTwo.setOnClickListener { appendOnExpresstion("2", true) }
        tvThree.setOnClickListener { appendOnExpresstion("3", true) }
        tvFour.setOnClickListener { appendOnExpresstion("4", true) }
        tvFive.setOnClickListener { appendOnExpresstion("5", true) }
        tvSix.setOnClickListener { appendOnExpresstion("6", true) }
        tvSeven.setOnClickListener { appendOnExpresstion("7", true) }
        tvEight.setOnClickListener { appendOnExpresstion("8", true) }
        tvNine.setOnClickListener { appendOnExpresstion("9", true) }
        tvDot.setOnClickListener {
            val text = tvExpression.text.toString()
            if (text.contains(".")) {
                appendOnExpresstion("", true)
            } else {
                appendOnExpresstion(".", true)
            }
        }

        //Operators
        tvPlus.setOnClickListener {
            val text = tvExpression.text.toString()
            val lastIndex = text.takeLast(1)
            if (lastIndex == "+" || lastIndex == "-" || lastIndex == "*" || lastIndex == "/") {
                appendOnExpresstion("", false)
            } else {
                appendOnExpresstion("+", false)
            }
        }
        tvMinus.setOnClickListener {
            val text = tvExpression.text.toString()
            val lastIndex = text.takeLast(1)
            if (lastIndex == "+" || lastIndex == "-" || lastIndex == "*" || lastIndex == "/") {
                appendOnExpresstion("", false)
            } else {
                appendOnExpresstion("-", false)
            }
        }
        tvMul.setOnClickListener {
            val text = tvExpression.text.toString()
            val lastIndex = text.takeLast(1)
            if (lastIndex == "+" || lastIndex == "-" || lastIndex == "*" || lastIndex == "/") {
                appendOnExpresstion("", false)
            } else {
                appendOnExpresstion("*", false)
            }
        }
        tvDivide.setOnClickListener {
            val text = tvExpression.text.toString()
            val lastIndex = text.takeLast(1)
            if (lastIndex == "+" || lastIndex == "-" || lastIndex == "*" || lastIndex == "/") {
                appendOnExpresstion("", false)
            } else {
                appendOnExpresstion("/", false)
            }
        }
        tvOpen.setOnClickListener { appendOnExpresstion("(", false) }
        tvClose.setOnClickListener { appendOnExpresstion(")", false) }

        tvClear.setOnClickListener {
            tvExpression.text = ""
            tvResult.text = ""
        }

        tvBack.setOnClickListener {
            val string = tvExpression.text.toString()
            if (string.isNotEmpty()) {
                tvExpression.text = string.substring(0, string.length - 1)
            }
            tvResult.text = ""
        }

        tvEquals.setOnClickListener {
            try {
                val expression = ExpressionBuilder(tvExpression.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble()) {
                    tvResult.text = longResult.toString()
                } else {
                    tvResult.text = result.toString()
                }

            } catch (e: Exception) {
                Log.d("Exception", "message :${e.message}")
            }
        }


    }

    fun appendOnExpresstion(string: String, canClear: Boolean) {
        if (tvResult.text.isNotEmpty()) {
            tvExpression.text = ""
        }
        if (canClear == true) {
            tvResult.text = ""
            tvExpression.append(string)
        } else {
            tvExpression.append(tvResult.text)
            tvExpression.append(string)
            tvResult.text = ""
        }
    }
}
