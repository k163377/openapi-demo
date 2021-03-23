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
    // デフォルトレスポンス設定
    private fun Docket.myGlobalResponses(): Docket {
        val `301Response` = ResponseBuilder()
            .code("301")
            .description("認証切れに伴うログイン画面へのリダイレクト")
            .build()

        val `500Response` = ResponseBuilder()
            .code("500")
            .description("不正な操作によるシステムエラー、またはサーバーサイドの実装の問題によるエラー")
            .build()

        val otherResponse = ResponseBuilder()
            .code("other")
            .description("通信の切断など、アプリケーションでハンドリングしていないエラー")
            .build()

        return this.globalResponses(
            HttpMethod.GET,
            listOf(
                `301Response`,
                ResponseBuilder()
                    .code("400")
                    .description("クエリパラメータ設定の不備によるパースエラー")
                    .build(),
                `500Response`,
                otherResponse
            )
        ).globalResponses(
            HttpMethod.POST,
            listOf(
                `301Response`,
                ResponseBuilder()
                    .code("400")
                    .description("レスポンスにエラーオブジェクトが含まれればバリデーションエラー、それ以外はPOST内容やクエリパラメータ設定の不備によるパースエラー")
                    .build(),
                ResponseBuilder()
                    .code("409")
                    .description("DB上のデータとPOSTされた内容が食い違ったことによるコンフリクトエラー")
                    .build(),
                `500Response`,
                otherResponse
            )
        )
    }

    @Bean
    fun api(): Docket = Docket(DocumentationType.SWAGGER_2)
        .myGlobalResponses()
        // 生成対象とするAPIのパス設定、一旦全パス指定
        .select()
        .apis(RequestHandlerSelectors.any())
        .paths(PathSelectors.any())
        .build()
}
