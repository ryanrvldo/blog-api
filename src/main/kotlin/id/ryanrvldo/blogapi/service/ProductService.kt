package id.ryanrvldo.blogapi.service

import id.ryanrvldo.blogapi.model.CreateProductRequest
import id.ryanrvldo.blogapi.model.ListProductRequest
import id.ryanrvldo.blogapi.model.ProductResponse
import id.ryanrvldo.blogapi.model.UpdateProductRequest

interface ProductService {

    fun create(createProductRequest: CreateProductRequest): ProductResponse

    fun get(id: String): ProductResponse

    fun update(id: String, updateProductRequest: UpdateProductRequest): ProductResponse

    fun delete(id: String)

    fun list(listProductRequest: ListProductRequest): List<ProductResponse>
}