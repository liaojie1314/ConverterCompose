package com.example.unitconvertercompose.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.unitconvertercompose.model.RecentResult
import com.example.unitconvertercompose.utils.ConverterUtil
import com.example.unitconvertercompose.utils.ConverterUtil.CONVERTER_TYPES
import java.time.temporal.TemporalAmount

class ConverterScreenViewModel:ViewModel() {
    val converterTypes = CONVERTER_TYPES
    val result = mutableStateOf(0.0f)
    val recentList= mutableStateOf(mutableListOf<RecentResult>())

    fun getConverterValues(
        converterType:String
    ):List<String>{
        return ConverterUtil.getConverterValues(converterType = converterType)
    }
    fun validateFields(
        converterType: String,
        convertFrom:String,
        convertTo:String
    ):Boolean{
        return (converterType.isBlank() or convertFrom.isBlank() or convertTo.isBlank())
    }
    fun calculateResult(
        converterType: String,
        convertFrom: String,
        convertTo: String,
        amount: Double=0.0
    ){
        result.value=ConverterUtil.convert(
            converterType = converterType,
            convertFrom = convertFrom,
            convertTo = convertTo,
            amount = amount
        ).toBigDecimal().toFloat()

        recentList.value.add(
            RecentResult(
                converterType,
                convertFrom,
                convertTo,
                amount.toString(),
                result.value.toString()
            )
        )
    }
}