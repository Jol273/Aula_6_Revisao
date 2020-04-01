package com.example.aula6_final

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class HistoryActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        val operations = mutableListOf(Operation("1+1",2.0),Operation("2+3",5.0))

        //val operations = intent.getParcelableArrayListExtra<Operation>(EXTRA_HISTORY)
        val orientation = getResources().getConfiguration().orientation
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            list_historic.adapter = HistoryAdapter(this, R.layout.item_expression, operations)
        }
    }
}
