package com.example.livedatapercobaan

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
class MainActivityViewModel: ViewModel (){
    private lateinit var timer: CountDownTimer
    private val detik = MutableLiveData<Int>()
    var selesai = MutableLiveData<Boolean>()
    var waktu= MutableLiveData<Long>()

    fun getDetik (): LiveData<Int>{
        return detik
    }
    fun startTimer (){
        timer = object
            :CountDownTimer(waktu.value!!.toLong()*1000L, 1000L){

            override fun onFinish() {
                detik.value = 0
                selesai.value = true
            }
            override fun onTick(millisUntilFinished: Long) {
                val waktuTersisa = millisUntilFinished/1000L
                detik.value = waktuTersisa.toInt()
            }
        }.start()
    }
    fun stopTimer (){
        timer.cancel()
    }
}