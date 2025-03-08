package com.achievesoft.sunday.models.responses

data class BaseResponse<T>(
    val success: Boolean,
    val message: String?,
    val data: T?
) {
    companion object {
        fun <T> success(message: String? = null, data: T? = null): BaseResponse<T>
            = BaseResponse<T>(true, message, data)

        fun <T> failed(message: String? = null, data: T? = null): BaseResponse<T>
            = BaseResponse<T>(false, message, data)
    }
}
