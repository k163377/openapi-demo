package com.wrongwrong.openapidemo.enumable

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.ExampleObject

enum class SampleEnum(label: String) {
    `enum`("label of enum"), `class`("label of class");

    @get:JsonValue
    val jsonValue = mapOf("name" to name, "label" to label)

    companion object {
        @JvmStatic
        @JsonCreator
        fun forName(
            @Parameter(examples = [
                ExampleObject(value = "enum"), ExampleObject(value = "class")
            ])
            name: String
        ) = values().find { it.name == name }
    }
}
