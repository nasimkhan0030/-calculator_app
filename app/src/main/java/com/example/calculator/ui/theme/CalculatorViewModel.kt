package com.example.calculator.ui.theme

import android.util.Log
import androidx.lifecycle.ViewModel

class CalculatorViewModel:ViewModel() {
    fun onButttonClick(btn:String){
        Log.i("Button Clicked",btn)
    }
}