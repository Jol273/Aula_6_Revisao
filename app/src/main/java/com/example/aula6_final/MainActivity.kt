package com.example.aula6_final

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.os.PersistableBundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.parcel.Parcelize
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_expression.view.*
import net.objecthunter.exp4j.ExpressionBuilder
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

const val EXTRA_HISTORY = "com.example.aula6_final"

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName

    val operations = mutableListOf(Operation("1+1",2.0),Operation("2+3",5.0))

    private val VISOR_KEY = "visor"

    var text = SimpleDateFormat("HH:mm:ss").format(Date())
    private val duration = Toast.LENGTH_SHORT
    var lastExpression = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG,"o metodo onCreate foi invocado")
        setContentView(R.layout.activity_main)

        val orientation = getResources().getConfiguration().orientation
        /*if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            list_historic.adapter = HistoryAdapter(this, R.layout.item_expression, operations)
        }*/

        button_division.setOnClickListener{
            Log.i(TAG, "Click no botão /")
            onClickSymbol("/")
            val toast = Toast.makeText(this, "$text button_division", duration)
            toast.show()
            //text_visor.append("!")
        }

        button_multiplication.setOnClickListener {
            Log.i(TAG,"Click no botão *")
            onClickSymbol("*")
            val toast = Toast.makeText(this, "$text button_multiplication", duration)
            toast.show()
            //text_visor.append("?")
        }

        button_subtraction.setOnClickListener {
            Log.i(TAG,"Click no botão -")
            onClickSymbol("-")
            val toast = Toast.makeText(this, "$text button_#", duration)
            toast.show()
            //text_visor.append("#")
        }

        button_adition.setOnClickListener{
            Log.i(TAG, "Click no botão +")
            onClickSymbol("+")
            val toast = Toast.makeText(this, "$text button_adition", duration)
            toast.show()
        }

        button_9.setOnClickListener{
            Log.i(TAG,"Click no botão 7")
            onClickSymbol("9")
            val toast = Toast.makeText(this, "$text button_6", duration)
            toast.show()
        }

        button_8.setOnClickListener{
            Log.i(TAG,"Click no botão 5")
            onClickSymbol("8")
            val toast = Toast.makeText(this, "$text button_5", duration)
            toast.show()
        }

        button_7.setOnClickListener{
            Log.i(TAG,"Click no botão 7")
            onClickSymbol("7")
            val toast = Toast.makeText(this, "$text button_4", duration)
            toast.show()
        }

        button_6.setOnClickListener{
            Log.i(TAG,"Click no botão 6")
            onClickSymbol("6")
            val toast = Toast.makeText(this, "$text button_6", duration)
            toast.show()
        }

        button_5.setOnClickListener{
            Log.i(TAG,"Click no botão 5")
            onClickSymbol("5")
            val toast = Toast.makeText(this, "$text button_5", duration)
            toast.show()
        }

        button_4.setOnClickListener{
            Log.i(TAG,"Click no botão 4")
            onClickSymbol("4")
            val toast = Toast.makeText(this, "$text button_4", duration)
            toast.show()
        }

        button_3.setOnClickListener{
            Log.i(TAG,"Click no botão 3")
            onClickSymbol("3")
            val toast = Toast.makeText(this, "$text button_3", duration)
            toast.show()
        }

        button_2.setOnClickListener{
            Log.i(TAG,"Click no botão 2")
            onClickSymbol("2")
            val toast = Toast.makeText(this, "$text button_2", duration)
            toast.show()
        }

        button_1.setOnClickListener{
            Log.i(TAG,"Click no botão 1")
            onClickSymbol("1")
            val toast = Toast.makeText(this, "$text button_1", duration)
            toast.show()
        }

        button_0.setOnClickListener{
            Log.i(TAG,"Click no botão 0")
            onClickSymbol("0")
            val toast = Toast.makeText(this, "$text button_0", duration)
            toast.show()
        }

        button_dot.setOnClickListener{
            Log.i(TAG,"Click no botão .")
            onClickSymbol(".")
            val toast = Toast.makeText(this, "$text button_dot", duration)
            toast.show()
        }

        button_clear.setOnClickListener{
            Log.i(TAG,"Click no botão C")
            text_visor.text = "0"
            val toast = Toast.makeText(this, "$text button_clear", duration)
            toast.show()
        }

        button_backspace.setOnClickListener{
            Log.i(TAG,"Click no botão >")
            var str = text_visor.text
            if(str.length > 1) {
                str = str.substring(0,str.length - 1)
                text_visor.text = str
            } else if(str.length <= 1){
                text_visor.text = "0"
            }
            val toast = Toast.makeText(this, "$text button_backspace", duration)
            toast.show()
        }

        button_equals.setOnClickListener { onClickEquals() }

        button_last.setOnClickListener {
            Log.i(TAG,"Click no botão Ultima Conta")
            text_visor.text = lastExpression.toString();
        }

        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            button_history.setOnClickListener {
                onClickHistory()
            }
        }
    }

    fun onClickHistory(){
        val intent = Intent(this, HistoryActivity::class.java)
        //intent.apply { putParcelableArrayListExtra(EXTRA_HISTORY, ArrayList(operations)) }
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        Log.i(TAG,"o método onDestroy foi invocado")
        super.onDestroy()
    }

    private fun onClickSymbol(symbol: String){
        if(text_visor.text == "0"){
            text_visor.text = symbol
        } else{
            text_visor.append(symbol)
        }
    }

    private fun onClickEquals(){
        lastExpression = text_visor.text.toString()
        Log.i(TAG,"Click no botão =")
        val expression = ExpressionBuilder(text_visor.text.toString()).build()
        val result = expression.evaluate()
        text_visor.text = result.toString()
        operations.add(Operation(lastExpression, result))
        Log.i(TAG, "O resultado da expressão é ${text_visor.text}")
        val toast = Toast.makeText(this, "$text button_equals", duration)
        toast.show()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        text_visor.text = savedInstanceState?.getString(VISOR_KEY)
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        outState.run { putString(VISOR_KEY, text_visor.text.toString()) }
        super.onSaveInstanceState(outState, outPersistentState)
    }
}


class HistoryAdapter(context: Context, private val layout: Int, items: MutableList<Operation>) : ArrayAdapter<Operation>(context, layout, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(layout,parent,false)
        view.text_expression.text = getItem(position)?.expresssion
        view.text_result.text = getItem(position)?.result.toString()
        return view
    }
}

class Operation(val expresssion : String, val result: Double)