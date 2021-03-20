package com.wrongwrong.openapidemo.form

import com.wrongwrong.openapidemo.enumable.SampleEnum

data class SampleForm(
    val sampleEnum: SampleEnum,
    val field: Int?
)
