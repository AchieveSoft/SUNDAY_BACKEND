package com.achievesoft.sunday.services

import com.achievesoft.sunday.models.User
import com.achievesoft.sunday.models.requests.auth.LoginRequest
import com.achievesoft.sunday.models.requests.auth.RegisterRequest
import com.achievesoft.sunday.models.responses.BaseResponse
import com.achievesoft.sunday.repositories.UserRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class AuthService(private val userRepository: UserRepository) {
    fun register(registerData: RegisterRequest): BaseResponse<Any> {
        return try {
            userRepository.save(
                User(
                    userCode = UUID.randomUUID().toString(),
                    email = registerData.email,
                    password = registerData.password
                )
            )
            BaseResponse.success(message = "register success")
        } catch (err: Exception) {
            BaseResponse.success(message = "register failed: ${err.message}")
        }
    }

    fun login(loginData: LoginRequest): BaseResponse<String> {
        userRepository.findByEmailAndPassword(loginData.email, loginData.password)?.let {
            return BaseResponse.success(message = "success")
        }
        return BaseResponse.failed(message = "invalid credential")
    }
}