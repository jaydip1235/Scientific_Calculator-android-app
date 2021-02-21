package com.example.calculator

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder

class ScientificActivity : AppCompatActivity() {

    var sin: TextView? = null
    var cos: TextView? = null
    var tan: TextView? = null
    var log2: TextView? = null
    var log10: TextView? = null
    var log: TextView? = null
    var expo: TextView? = null
    var sqrt: TextView? = null
    var cbrt: TextView? = null
    var asin: TextView? = null
    var acos: TextView? = null
    var atan: TextView? = null
    var abs: TextView? = null
    var ceil: TextView? = null
    var floor: TextView? = null
    var open_brac: TextView? = null
    var close_brac: TextView? = null
    var back: ImageView? = null
    var expression: TextView? = null
    var result: TextView? = null
    var ac: TextView? = null
    var backspace: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scientific)



        sin = findViewById(R.id.sin)
        cos = findViewById(R.id.cos)
        tan = findViewById(R.id.tan)
        log2 = findViewById(R.id.log2)
        log10 = findViewById(R.id.log10)
        log = findViewById(R.id.log)
        expo = findViewById(R.id.exp)
        sqrt = findViewById(R.id.sqrt)
        cbrt = findViewById(R.id.cbrt)
        asin = findViewById(R.id.asin)
        acos = findViewById(R.id.acos)
        atan = findViewById(R.id.atan)
        abs = findViewById(R.id.abs)
        ceil = findViewById(R.id.ceil)
        floor = findViewById(R.id.floor)
        open_brac = findViewById(R.id.open_brac)
        close_brac = findViewById(R.id.close_brac)
        back = findViewById(R.id.back)
        expression = findViewById(R.id.expression)
        result = findViewById(R.id.result)
        ac = findViewById(R.id.clear)
        backspace = findViewById(R.id.backspace)



        sin?.setOnClickListener{appendText("sin", false)}
        cos?.setOnClickListener{appendText("cos", false)}
        tan?.setOnClickListener{appendText("tan", false)}
        log?.setOnClickListener{appendText("log", false)}
        log2?.setOnClickListener{appendText("log2", false)}
        log10?.setOnClickListener{appendText("log10", false)}
        ceil?.setOnClickListener{appendText("ceil", false)}
        floor?.setOnClickListener{appendText("floor", false)}
        expo?.setOnClickListener{appendText("exp", false)}
        sqrt?.setOnClickListener{appendText("sqrt", false)}
        cbrt?.setOnClickListener{appendText("cbrt", false)}
        asin?.setOnClickListener{appendText("asin", false)}
        acos?.setOnClickListener{appendText("acos", false)}
        atan?.setOnClickListener{appendText("atan", false)}
        open_brac?.setOnClickListener{appendText("(", false)}
        close_brac?.setOnClickListener{appendText(")", false)}

        var exp = intent.getStringExtra("exp")
        expression?.setText(exp)
        var res = intent.getStringExtra("res")
        result?.setText(res)


        back?.setOnClickListener{
            var intent = Intent(this,MainActivity::class.java)

            intent.putExtra("expr",expression?.getText().toString())
            intent.putExtra("resu",result?.getText().toString())

            startActivity(intent)
        }



        backspace?.setOnClickListener {
            result?.text = ""
            result?.hint = ""
            val value = expression?.text
            if (value?.isNotEmpty()!!) {
                expression?.text = value.substring(0, value.length - 1)
            }
        }


        ac?.setOnClickListener {
            expression?.text = ""
            result?.text = ""
            result?.hint = ""
        }



    }
    private fun appendText(value :String, toBeCleared : Boolean){
        if(expression?.length()!!>24){
            Toast.makeText(this,"Max length exceeded",LENGTH_LONG).show()
            return
        }

        if(result?.text != ""){
            expression?.text  = ""
        }
        if (toBeCleared) {
            result?.text = ""
            expression?.append(value)
        } else {
            expression?.append(result?.text)
            expression?.append(value)
            result?.text = ""
        }
        result?.hint = expression?.text
    }
}