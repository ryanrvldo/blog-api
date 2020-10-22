package id.ryanrvldo.blogapi.controller

import id.ryanrvldo.blogapi.model.*
import id.ryanrvldo.blogapi.service.ProductService
import org.springframework.web.bind.annotation.*

@RestController
class ProductController(val productService: ProductService) {

    @PostMapping(
        value = ["/api/products"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun createProduct(@RequestBody requestBody: CreateProductRequest): WebResponse<ProductResponse> {
        val productResponse = productService.create(requestBody)
        return createWebResponse(productResponse)
    }

    @GetMapping(
        value = ["/api/products/{idProduct}"],
        produces = ["application/json"]
    )
    fun getProduct(@PathVariable("idProduct") id: String): WebResponse<ProductResponse> {
        val productResponse = productService.get(id)
        return createWebResponse(productResponse)
    }

    @PutMapping(
        value = ["/api/products/{idProduct}"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun updateProduct(
        @PathVariable("idProduct") id: String,
        @RequestBody updateProductRequest: UpdateProductRequest
    ): WebResponse<ProductResponse> {
        val productResponse = productService.update(id, updateProductRequest)
        return createWebResponse(productResponse)
    }

    @DeleteMapping(
        value = ["/api/products/{idProduct}"],
        produces = ["application/json"]
    )
    fun deleteProduct(@PathVariable("idProduct") id: String): WebResponse<String> {
        productService.delete(id)
        return createWebResponse("$id has been successfully deleted.")
    }

    @GetMapping(
        value = ["/api/products"],
        produces = ["application/json"]
    )
    fun listProducts(
        @RequestParam(value = "size", defaultValue = "10") size: Int,
        @RequestParam(value = "page", defaultValue = "0") page: Int
    ): WebResponse<List<ProductResponse>> {
        val request = ListProductRequest(size, page)
        val responses = productService.list(request)
        return createWebResponse(responses)
    }

    private fun <T> createWebResponse(data: T) = WebResponse(
        code = 200,
        status = "OK",
        data = data
    )

}