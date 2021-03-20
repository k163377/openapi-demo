package com.wrongwrong.openapidemo.enumable

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

enum class SampleEnum(label: String) {
    `enum`("label of enum"), `class`("label of class");

    @get:JsonValue
    val jsonValue = mapOf("name" to name, "label" to label)

    companion object {
        @JvmStatic
        @JsonCreator
        fun forName(name: String) = values().find { it.name == name }
    }
}
