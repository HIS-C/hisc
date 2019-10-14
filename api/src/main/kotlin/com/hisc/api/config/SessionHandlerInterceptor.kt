package com.hisc.api.config

import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class SessionHandlerInterceptor : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
//        val token: String = request.getHeader(HttpHeaders.AUTHORIZATION)
//            ?: throw RuntimeException("no auth")
//        return token == "hisc1013"
        return true
    }
}
