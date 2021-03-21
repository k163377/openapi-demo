package com.wrongwrong.openapidemo.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.builders.ResponseBuilder
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket

@Configuration
class SpringFoxConfig {
    @Bean
    fun api(): Docket = Docket(DocumentationType.OAS_30)
        // デフォルトで表示されるメッセージの無効化
        .useDefaultResponseMessages(false)
        // デフォルトレスポンス
        .globalResponses(
            HttpMethod.GET,
            listOf(
                ResponseBuilder()
                    .code("401")
                    .description("認証切れに伴うログイン画面へのリダイレクト。")
                    .build(),
                ResponseBuilder()
                    .code("500")
                    .description("不正な操作によるシステムエラー。またはサーバーサイドの実装の問題によるエラー。")
                    .build()
            )
        )
        .globalResponses(
            HttpMethod.POST,
            listOf(
                ResponseBuilder()
                    .code("400")
                    .description("レスポンスにエラーオブジェクトが含まれればバリデーションエラー。それ以外はPOST内容やクエリパラメータ設定の不備によるパースエラー。")
                    .build(),
                ResponseBuilder()
                    .code("401")
                    .description("認証切れに伴うログイン画面へのリダイレクト。")
                    .build(),
                ResponseBuilder()
                    .code("500")
                    .description("不正な操作によるシステムエラー。またはサーバーサイドの実装の問題によるエラー。")
                    .build()
            )
        )
        // 生成対象とするAPIのパス設定、一旦全パス指定
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any())
        .build()
}
