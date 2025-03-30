package com.example.currency

import android.icu.util.Currency
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    // can tao 2 thuoc tinh cua lop luu 2 loai tien duoc chon hien tai

    var moneyType1: Int = 1
    var moneyType2: Int = 2
    var moneyCurrency2div1: Double = 0.0
    var choose = 1


    lateinit var editTextMoneyAmount1:EditText
    lateinit var editTextMoneyAmount2:EditText
    lateinit var textViewMoneySymbol1: TextView
    lateinit var textViewMoneySymbol2: TextView
    lateinit var spinerMoneyType1: Spinner
    lateinit var spinnerMoneyType2: Spinner
    lateinit var textViewCurrency: TextView
    lateinit var moneys: MutableList<Money>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editTextMoneyAmount1 = findViewById(R.id.edit_text_money_amount_1)
        editTextMoneyAmount2 = findViewById(R.id.edit_text_money_amount_2)
        textViewMoneySymbol1 = findViewById(R.id.text_view_symbol_money_1)
        textViewMoneySymbol2 = findViewById(R.id.text_view_symbol_money_2)
        spinerMoneyType1 = findViewById(R.id.spinner_money_type_1)
        spinnerMoneyType2 = findViewById(R.id.spinner_money_type_2)
        textViewCurrency = findViewById(R.id.text_view_currency)

        val dollar = Money("United States - Dollar", 1.0,"$","USD")
        val dong = Money("Vietnam - Dong",23185.00,"d","VND")
        val euro = Money("Europe - Euro",0.9234,"€","EUR")
        val yuan = Money("China - Yuan",7.2628,"¥","CNY")
        val yen = Money("Japan - Yen",149.81,"¥","JPY")

        moneys = mutableListOf(dollar,dong,euro,yuan,yen)
1
        val moneysName = moneys.map { it.name }




        val adapter:ArrayAdapter<String> = ArrayAdapter(
            this,
            R.layout.money_type,
            R.id.textView_display,
            moneysName
        )

        spinerMoneyType1.adapter = adapter
        spinerMoneyType1.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                // update thuoc tinh 1
                moneyType1 = position
                updateMoneyCurrency()

                // goi den phuong thuc cap nhap hien thi currency textview
                updateDisplayCurrency()
                updateDisplayMoneySymbol()
                setCurrency()
                Log.v("Tag","setCurrency from 2 spinner")

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

        spinnerMoneyType2.adapter = adapter
        spinnerMoneyType2.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {


                moneyType2 = position
                updateMoneyCurrency()

                updateDisplayCurrency()
                updateDisplayMoneySymbol()
                setCurrency()
                Log.v("Tag","setCurrency from 2 spinner")

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

        editTextMoneyAmount1.setOnFocusChangeListener { v, hasFocus ->
            choose = 1
            updateDisplayCurrency()
        }

        editTextMoneyAmount2.setOnFocusChangeListener { v, hasFocus ->
            choose = 2
            updateDisplayCurrency()
        }

        editTextMoneyAmount1.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {

                Log.v("Tag","setCurrency from 1 textchange")
                if(choose == 1){
                    setCurrency()
                }

            }

        })

        editTextMoneyAmount2.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                Log.v("Tag","setCurrency from 1 textchange")

                if(choose == 2){
                    setCurrency()
                }

            }

        })



    }

    fun updateDisplayCurrency(){
        if(choose == 1){
            textViewCurrency.setText("1 ${moneys[moneyType1].aka} = ${(moneyCurrency2div1*10000.0).roundToInt()/10000.0} ${moneys[moneyType2].aka}")
        }
        if(choose ==2){
            textViewCurrency.setText("1 ${moneys[moneyType2].aka} = ${(1/moneyCurrency2div1*10000.0).roundToInt()/10000.0} ${moneys[moneyType1].aka}")
        }

    }

    fun updateDisplayMoneySymbol(){
        textViewMoneySymbol1.setText(moneys[moneyType1].symbol)
        textViewMoneySymbol2.setText(moneys[moneyType2].symbol)
    }


    fun setCurrency() {
        val amount1 = editTextMoneyAmount1.text.toString()
        val amount2 = editTextMoneyAmount2.text.toString()

        if (choose == 1) {
            if (amount1.isNotEmpty()) {
                editTextMoneyAmount2.setText("${(amount1.toDouble() * moneyCurrency2div1 * 100.0).roundToInt()/100.0}")
            } else {
                editTextMoneyAmount2.setText("")
            }
        } else if (choose == 2) {
            if (amount2.isNotEmpty()) {
                editTextMoneyAmount1.setText("${(amount2.toDouble() / moneyCurrency2div1 * 100.0).roundToInt()/100.0}")
            } else {
                editTextMoneyAmount1.setText("")
            }
        }
    }


    fun updateMoneyCurrency(){
        moneyCurrency2div1 = moneys[moneyType2].oneDollarEqual/moneys[moneyType1].oneDollarEqual
    }
}