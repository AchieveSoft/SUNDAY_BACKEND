package com.achievesoft.sunday.controllers

import com.achievesoft.sunday.models.requests.auth.LoginRequest
import com.achievesoft.sunday.models.requests.auth.RegisterRequest
import com.achievesoft.sunday.models.responses.BaseResponse
import com.achievesoft.sunday.services.AuthService
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

@RestController
class AuthController(private val authService: AuthService) {
    @PostMapping("/api/auth/register")
    fun register(@RequestBody req: RegisterRequest): BaseResponse<Any> = authService.register(req)

    @PostMapping("/api/auth/login")
    fun login(@RequestBody req: LoginRequest): BaseResponse<String> = authService.login(req)
}
