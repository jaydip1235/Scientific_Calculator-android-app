package com.example.calculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    var one: TextView? = null
    var two: TextView? = null
    var three: TextView? = null
    var four: TextView? = null
    var five: TextView? = null
    var six: TextView? = null
    var seven: TextView? = null
    var eight: TextView? = null
    var nine: TextView? = null
    var zero: TextView? = null
    var plus: TextView? = null
    var minus: TextView? = null
    var multiply: TextView? = null
    var divide: TextView? = null
    var modulo: TextView? = null
    var equals: TextView? = null
    var adv: TextView? = null
    var decimal: TextView? = null
    var expression: TextView? = null
    var result: TextView? = null
    var ac: TextView? = null
    var backspace: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        one = findViewById(R.id.one)
        two = findViewById(R.id.two)
        three = findViewById(R.id.three)
        four = findViewById(R.id.four)
        five = findViewById(R.id.five)
        six = findViewById(R.id.six)
        seven = findViewById(R.id.seven)
        eight = findViewById(R.id.eight)
        nine = findViewById(R.id.nine)
        zero = findViewById(R.id.zero)
        plus = findViewById(R.id.plus)
        minus = findViewById(R.id.minus)
        multiply = findViewById(R.id.multiply)
        divide = findViewById(R.id.division)
        modulo = findViewById(R.id.modulo)
        equals = findViewById(R.id.equals)
        adv = findViewById(R.id.adv)
        decimal = findViewById(R.id.decimal)
        expression = findViewById(R.id.expression)
        result = findViewById(R.id.result)
        ac = findViewById(R.id.clear)
        backspace = findViewById(R.id.backspace)


        one?.setOnClickListener { appendText("1", true) }
        two?.setOnClickListener { appendText("2", true) }
        three?.setOnClickListener { appendText("3", true) }
        four?.setOnClickListener { appendText("4", true) }
        five?.setOnClickListener { appendText("5", true) }
        six?.setOnClickListener { appendText("6", true) }
        seven?.setOnClickListener { appendText("7", true) }
        eight?.setOnClickListener { appendText("8", true) }
        nine?.setOnClickListener { appendText("9", true) }
        zero?.setOnClickListener { appendText("0", true) }

        plus?.setOnClickListener { appendText("+", false) }
        minus?.setOnClickListener { appendText("-", false) }
        multiply?.setOnClickListener { appendText("*", false) }
        divide?.setOnClickListener { appendText("/", false) }
        modulo?.setOnClickListener { appendText("%", false) }

        decimal?.setOnClickListener { appendText(".", true) }


        var exp = intent.getStringExtra("expr")
        expression?.setText(exp)
        var res = intent.getStringExtra("resu")
        result?.setText(res)

        equals?.setOnClickListener {
            try {
                val expr = ExpressionBuilder(expression?.text.toString()).build()
                val answer = expr.evaluate()
                result?.text = answer.toString()
            } catch (e: Exception) {
                result?.text = e.message
            }
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

        var text : TextView? = expression

        adv?.setOnClickListener{
            var intent = Intent(this,ScientificActivity::class.java)

            intent.putExtra("exp",expression?.getText().toString())
            intent.putExtra("res",result?.getText().toString())

            startActivity(intent)

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