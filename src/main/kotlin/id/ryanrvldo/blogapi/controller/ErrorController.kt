package id.ryanrvldo.blogapi.controller

import id.ryanrvldo.blogapi.error.NotFoundException
import id.ryanrvldo.blogapi.error.UnauthorizedException
import id.ryanrvldo.blogapi.model.WebResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class ErrorController {

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun validationHandler(exception: ConstraintViolationException): WebResponse<String> {
        return WebResponse(
            code = 400,
            status = "BAD REQUEST",
            data = exception.message!!
        )
    }

    @ExceptionHandler(value = [NotFoundException::class])
    fun notFound(exception: NotFoundException): WebResponse<String> = WebResponse(
        code = 404,
        status = "NOT FOUND",
        data = "Product is not found."
    )

    @ExceptionHandler(value = [UnauthorizedException::class])
    fun unauthorized(exception: UnauthorizedException): WebResponse<String> = WebResponse(
        code = 401,
        status = "UNAUTHORIZED",
        data = "Please put your X-Api-Key."
    )

}