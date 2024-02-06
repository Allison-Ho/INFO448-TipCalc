package edu.uw.ischool.qnho.tipcalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.math.BigDecimal
import java.math.RoundingMode
import android.text.TextWatcher
import android.text.Editable
import android.util.Log
import java.text.DecimalFormat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputField = findViewById<EditText>(R.id.amount_field);
        val btn = findViewById<Button>(R.id.btn);
        val btn_10 = findViewById<Button>(R.id.btn_10);
        val btn_15 = findViewById<Button>(R.id.btn_15);
        val btn_18 = findViewById<Button>(R.id.btn_18);
        val btn_20 = findViewById<Button>(R.id.btn_20);

        var tipAmount = 0.00

        btn.isEnabled = false;

        inputField.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                btn.isEnabled = inputField.text.trim() != ""
                val test = inputField.text.trim()

                if(test == ""){
                    Log.i("WHY", "IT IS EMPTY THOUGH")
                }
                Log.i("TEST", "input value:$test what is going on? ${btn.isEnabled}")
            }
        })

        btn.setOnClickListener {
            if(inputField.text.trim() != ""){
                val amount = inputField.text.toString().toDouble();
                val withTip = calcTip(amount, tipAmount);
                val toast = Toast.makeText(this, "Tip amount: $withTip", Toast.LENGTH_SHORT);
                toast.show();
            }
        }

        btn_10.setOnClickListener {
            tipAmount = 0.10;
        }

        btn_15.setOnClickListener {
            tipAmount = 0.15;
        }

        btn_18.setOnClickListener {
            tipAmount = 0.18;
        }

        btn_20.setOnClickListener {
            tipAmount = 0.20;
        }
    }
}


fun calcTip(total: Double, tipAmount: Double): String {
    var tip = 0.00
    tip = if(tipAmount == 0.00){
        0.15
    }else{
        tipAmount
    }

    val withTip = total * tip
    return formatCurrency(withTip.toString())
}

fun formatCurrency(num: String): String {
    val formatter = DecimalFormat("'$'0.00");
    return formatter.format(num.toDouble())
}