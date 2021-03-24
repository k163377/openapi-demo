package com.wrongwrong.openapidemo.controller

import com.wrongwrong.openapidemo.enumable.SampleEnum
import com.wrongwrong.openapidemo.form.SampleForm
import com.wrongwrong.openapidemo.view.ClassView
import com.wrongwrong.openapidemo.view.EnumView
import com.wrongwrong.openapidemo.view.PolymorphicView
import com.wrongwrong.openapidemo.view.SampleView
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Encoding
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.http.MediaType
import io.swagger.v3.oas.annotations.parameters.RequestBody as SwaggerRequestBody
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
class DemoController {
    /**
     * @return パラメータに設定したEnumに対応するView
     */
    @Secured(value = [ "ROLE_SAMPLE" ])
    @GetMapping("/sample/get")
    fun getSample(@RequestParam enum: SampleEnum): SampleView {
        return SampleView(enum)
    }

    @GetMapping("/sample/get/polymorphic")
    fun getPolymorphic(enum: SampleEnum): PolymorphicView = when (enum) {
        SampleEnum.enum -> EnumView("foo", 1)
        SampleEnum.`class` -> ClassView("bar", 2)
    }

    @PostMapping("/sample/get/polymorphic")
    fun postPolymorphic(form: PolymorphicView) {}

    @PostMapping("/sample/post", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    @SwaggerRequestBody(content = [
        Content(encoding = [
            Encoding(name = "form", contentType = "application/json")
        ])
    ])
    fun postSample(
        @RequestPart("file") file: MultipartFile,
        @RequestPart("form")
        @Parameter(schema = Schema(type = "object", format = "binary"))form: SampleForm
    ) {}
}
