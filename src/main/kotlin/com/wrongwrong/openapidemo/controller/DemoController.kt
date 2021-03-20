package com.wrongwrong.openapidemo.controller

import com.wrongwrong.openapidemo.enumable.SampleEnum
import com.wrongwrong.openapidemo.form.SampleForm
import com.wrongwrong.openapidemo.view.SampleView
import org.springframework.web.bind.annotation.*

@RestController
class DemoController {
    @GetMapping("/sample/get")
    fun getSample(@RequestParam enum: SampleEnum): SampleView {
        return SampleView(enum)
    }

    @PostMapping("/sample/post")
    fun postSample(@RequestBody form: SampleForm) {}
}
