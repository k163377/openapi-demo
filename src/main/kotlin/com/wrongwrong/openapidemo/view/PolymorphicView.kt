package com.wrongwrong.openapidemo.view

import com.wrongwrong.openapidemo.enumable.SampleEnum
import io.swagger.v3.oas.annotations.media.DiscriminatorMapping
import io.swagger.v3.oas.annotations.media.Schema

@Schema(
    type = "object",
    subTypes = [EnumView::class, ClassView::class],
    discriminatorProperty = "sampleEnum",
    discriminatorMapping = [
        DiscriminatorMapping(value = "enum", schema = EnumView::class),
        DiscriminatorMapping(value = "class", schema = ClassView::class)
    ]
)
sealed class PolymorphicView {
    @get:Schema(required = true)
    abstract val sampleEnum: SampleEnum
    abstract val str: String
}

data class EnumView(override val str: String, val a1: Int) : PolymorphicView() {
    override val sampleEnum = SampleEnum.enum
}

data class ClassView(override val str: String, val a2: Int) : PolymorphicView() {
    override val sampleEnum = SampleEnum.`class`
}
