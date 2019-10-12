package com.hisc.api.vo

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class ResponseVo<T>(
    val data: T?,
    val resultMessage: String? = null,
    val resultCode: ResultCode = ResultCode.SUCCESS,
    val ts: Long = System.currentTimeMillis()
)

enum class ResultCode {
    SUCCESS, // 200
    FAIL, // 4xx
    ERROR // 5xx
}
