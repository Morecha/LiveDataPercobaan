package com.example.livedatapercobaan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
//import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        val tv_waktu = findViewById<TextView>(R.id.tv_waktu)
        val et_waktu = findViewById<EditText>(R.id.et_waktu)
        val btn_mulai = findViewById<Button>(R.id.btn_mulai)
        val btn_berhenti = findViewById<Button>(R.id.btn_berhenti)
        val btn_reset = findViewById<Button>(R.id.btn_reset)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        viewModel.getDetik().observe(this, Observer {
            tv_waktu.text = it.toString()
        })

        viewModel.selesai.observe(this, Observer {
            if(it){
                Toast.makeText(this, "Selesai",
                    Toast.LENGTH_SHORT).show()
            }
        })

        btn_mulai.setOnClickListener {
            if(et_waktu.text.isEmpty() || et_waktu.text.toString()=="0"){
                Toast.makeText(this, "Invalid Number", Toast.LENGTH_SHORT).show()
            }else{
                viewModel.waktu.value = et_waktu.text.toString().toLong()
                viewModel.startTimer()
            }
        }
        btn_berhenti.setOnClickListener {
            viewModel.stopTimer()
        }
        btn_reset.setOnClickListener {
            viewModel.stopTimer()
            tv_waktu.text = "0"
        }
    }
}