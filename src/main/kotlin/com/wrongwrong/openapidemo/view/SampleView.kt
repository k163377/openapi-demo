package com.wrongwrong.openapidemo.view

import com.wrongwrong.openapidemo.enumable.SampleEnum

data class SampleView(val sampleEnum: SampleEnum?) {
    val hash: Int = sampleEnum.hashCode()
}
