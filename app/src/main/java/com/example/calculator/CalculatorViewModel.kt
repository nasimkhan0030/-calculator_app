package com.example.calculator

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.mozilla.javascript.Context
import org.mozilla.javascript.Scriptable
import java.lang.Exception

class CalculatorViewModel:ViewModel() {
    private val _equationText = MutableLiveData("")
    var equationText :LiveData<String> =_equationText

    private val  _resultText = MutableLiveData("0")
    val resultText : LiveData<String> =_resultText
    fun onButttonClick(btn:String){
        _equationText.value?.let {
            if(btn=="AC") {
                _equationText.value=""
                _resultText.value="0"
                return
            }
            if(btn=="C"){
                if(it.isNotEmpty()){
                    _equationText.value=it.substring(0,it.length-1)
                }
                return
            }
            if(btn=="="){
                if (it.isEmpty()){
                    _equationText.value=""
                    _resultText.value="0"
                    return

                }
                try {
                    _resultText.value= calculateResult(_equationText.value.toString())
                    return
                }catch (_:Exception){}


            }
            _equationText.value=it+btn

        }


    }

    private fun calculateResult(equation:String):String{
        val context : Context=Context.enter()
        context.optimizationLevel=-1
        val scriptable:Scriptable=context.initStandardObjects()
        var  finalResult=context.evaluateString(scriptable,equation,"Javascript",1,null).toString()
        if (finalResult.endsWith(".0")){
            finalResult=finalResult.replace(".0","")
        }
        return finalResult
    }
}