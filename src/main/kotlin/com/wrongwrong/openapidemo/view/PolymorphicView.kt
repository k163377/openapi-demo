package com.wrongwrong.openapidemo.view

import com.wrongwrong.openapidemo.enumable.SampleEnum
import io.swagger.annotations.ApiModel

@ApiModel(subTypes = [EnumView::class, ClassView::class])
sealed class PolymorphicView {
    abstract val sampleEnum: SampleEnum
    abstract val str: String
}

@ApiModel(parent = PolymorphicView::class)
data class EnumView(override val str: String, val a1: Int) : PolymorphicView() {
    override val sampleEnum = SampleEnum.enum
}

@ApiModel(parent = PolymorphicView::class)
data class ClassView(override val str: String, val a2: Int) : PolymorphicView() {
    override val sampleEnum = SampleEnum.`class`
}
