package com.example.currency

import android.os.Bundle
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

class MainActivity : AppCompatActivity() {
    // can tao 2 thuoc tinh cua lop luu 2 loai tien duoc chon hien tai

    lateinit var editTextMoneyAmount1:EditText
    lateinit var editTextMoneyAmount2:EditText
    lateinit var textViewMoneySymbol1: TextView
    lateinit var textViewMoneySymbol2: TextView
    lateinit var spinerMoneyType1: Spinner
    lateinit var spinnerMoneyType2: Spinner
    lateinit var textViewCurrency: TextView

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

        val dollar = Money("United States - Dollar", 1.0,"$")
        val dong = Money("Vietnam - Dong",23185.00,"VND")
        val euro = Money("Europe",1.083,"€")
        val yuan = Money("China - Yuan",0.1377,"¥")
        val yen = Money("Japan - Yen",0.006675,"¥")

        val moneys = listOf(dollar,dong,euro,yuan,yen)

        val moneysName = moneys.map { it.name }



        val adapter:ArrayAdapter<String> = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
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
                TODO("Not yet implemented")
                // update thuoc tinh 1
                // goi den phuong thuc cap nhap hien thi currency textview
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }



    }
}