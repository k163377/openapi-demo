package com.wrongwrong.openapidemo.enumable

import com.fasterxml.jackson.annotation.JsonValue

enum class SampleEnum(label: String) {
    `enum`("label of enum"), `class`("label of class");

    @get:JsonValue
    val jsonValue = mapOf("name" to name, "label" to label)
}
