package com.wrongwrong.openapidemo.view

import com.wrongwrong.openapidemo.enumable.SampleEnum

sealed class PolymorphicView {
    abstract val sampleEnum: SampleEnum
    abstract val str: String
}

data class EnumView(override val str: String, val a1: Int) : PolymorphicView() {
    override val sampleEnum = SampleEnum.enum
}

data class ClassView(override val str: String, val a2: Int) : PolymorphicView() {
    override val sampleEnum = SampleEnum.`class`
}
