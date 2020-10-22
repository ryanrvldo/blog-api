package id.ryanrvldo.blogapi.model

data class WebResponse<T>(
    val code: Int,
    val status: String,
    val data: T
)