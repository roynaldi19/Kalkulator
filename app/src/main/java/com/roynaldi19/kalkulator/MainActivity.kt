package com.roynaldi19.kalkulator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.roynaldi19.kalkulator.room.History
import com.roynaldi19.kalkulator.room.HistoryDataBase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    val dataBase by lazy { HistoryDataBase(this) }
    lateinit var historyAdapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        historyAdapter = HistoryAdapter(arrayListOf(), object : HistoryAdapter.onAdapterListener {
            override fun onDelete(history: History) {
                CoroutineScope(Dispatchers.IO).launch {
                    dataBase.historyDao().deleteHistory(history)
                    updateRv()
                }
            }
        })

        list_history.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = historyAdapter
        }

        button_plus.setOnClickListener {
            val nilai1 = edit_nilai1.text.toString().trim()
            val nilai2 = edit_nilai2.text.toString().trim()
            val operator = "+"
            val hasil = nilai1.toDouble() + nilai2.toDouble()
            tv_result.text = hasil.toString()

            CoroutineScope(Dispatchers.IO).launch {
                dataBase.historyDao().addHistory(
                    History(
                        0,
                        nilai1.toDouble(),
                        nilai2.toDouble(),
                        operator,
                        hasil
                    )
                )
                updateRv()
            }

        }

        button_minus.setOnClickListener {
            val nilai1 = edit_nilai1.text.toString().trim()
            val nilai2 = edit_nilai2.text.toString().trim()
            val operator = "-"
            val hasil = nilai1.toDouble() - nilai2.toDouble()
            tv_result.text = hasil.toString()

            CoroutineScope(Dispatchers.IO).launch {
                dataBase.historyDao().addHistory(
                    History(
                        0,
                        nilai1.toDouble(),
                        nilai2.toDouble(),
                        operator,
                        hasil
                    )
                )
                updateRv()
            }

        }

        button_multiply.setOnClickListener {
            val nilai1 = edit_nilai1.text.toString().trim()
            val nilai2 = edit_nilai2.text.toString().trim()
            val operator = "x"
            val hasil = nilai1.toDouble() * nilai2.toDouble()
            tv_result.text = hasil.toString()

            CoroutineScope(Dispatchers.IO).launch {
                dataBase.historyDao().addHistory(
                    History(
                        0,
                        nilai1.toDouble(),
                        nilai2.toDouble(),
                        operator,
                        hasil
                    )
                )
                updateRv()
            }

        }

        button_divide.setOnClickListener {
            val nilai1 = edit_nilai1.text.toString().trim()
            val nilai2 = edit_nilai2.text.toString().trim()
            val operator = ":"
            val hasil = nilai1.toDouble() / nilai2.toDouble()
            tv_result.text = hasil.toString()

            CoroutineScope(Dispatchers.IO).launch {
                dataBase.historyDao().addHistory(
                    History(
                        0,
                        nilai1.toDouble(),
                        nilai2.toDouble(),
                        operator,
                        hasil
                    )
                )
                updateRv()
            }

        }
    }

    fun updateRv() {
        CoroutineScope(Dispatchers.IO).launch {
            val historys = dataBase.historyDao().getHistorys()

            withContext(Dispatchers.Main) {
                historyAdapter.setData(historys)
            }
        }
    }

    override fun onStart() {
        super.onStart()
        updateRv()
    }


}