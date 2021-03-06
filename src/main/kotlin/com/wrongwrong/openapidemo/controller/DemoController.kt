package com.wrongwrong.openapidemo.controller

import com.wrongwrong.openapidemo.enumable.SampleEnum
import com.wrongwrong.openapidemo.form.SampleForm
import com.wrongwrong.openapidemo.view.ClassView
import com.wrongwrong.openapidemo.view.EnumView
import com.wrongwrong.openapidemo.view.PolymorphicView
import com.wrongwrong.openapidemo.view.SampleView
import io.swagger.annotations.ApiParam
import org.springframework.security.access.annotation.Secured
import org.springframework.web.bind.annotation.*

@RestController
class DemoController {
    /**
     * @return パラメータに設定したEnumに対応するView
     */
    @Secured(value = [ "ROLE_SAMPLE" ])
    @GetMapping("/sample/get")
    fun getSample(@RequestParam @ApiParam(allowableValues = "Hoge,Fuga") enum: SampleEnum): SampleView {
        return SampleView(enum)
    }

    @GetMapping("/sample/get/polymorphic")
    fun getPolymorphic(enum: SampleEnum): PolymorphicView = when (enum) {
        SampleEnum.enum -> EnumView("foo", 1)
        SampleEnum.`class` -> ClassView("bar", 2)
    }

    @PostMapping("/sample/post")
    fun postSample(@RequestBody form: SampleForm) {}
}
