package id.ryanrvldo.blogapi.service.impl

import id.ryanrvldo.blogapi.entity.Product
import id.ryanrvldo.blogapi.error.NotFoundException
import id.ryanrvldo.blogapi.model.CreateProductRequest
import id.ryanrvldo.blogapi.model.ListProductRequest
import id.ryanrvldo.blogapi.model.ProductResponse
import id.ryanrvldo.blogapi.model.UpdateProductRequest
import id.ryanrvldo.blogapi.repository.ProductRepository
import id.ryanrvldo.blogapi.service.ProductService
import id.ryanrvldo.blogapi.validation.ValidationUtil
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

@Service
class ProductServiceImpl(
    val productRepository: ProductRepository,
    val validationUtil: ValidationUtil
) : ProductService {

    override fun create(createProductRequest: CreateProductRequest): ProductResponse {
        validationUtil.validate(createProductRequest)

        val product = Product(
            id = createProductRequest.id!!,
            name = createProductRequest.name!!,
            price = createProductRequest.price!!,
            quantity = createProductRequest.quantity!!,
            createdAt = Date(),
            updatedAt = null
        )

        productRepository.save(product)

        return convertProductToProductResponse(product)
    }

    override fun get(id: String): ProductResponse {
        val product = findProductByIdOrThrowNotFound(id)
        return convertProductToProductResponse(product)
    }

    override fun update(id: String, updateProductRequest: UpdateProductRequest): ProductResponse {
        val product = findProductByIdOrThrowNotFound(id)

        validationUtil.validate(updateProductRequest)

        product.apply {
            name = updateProductRequest.name!!
            price = updateProductRequest.price!!
            quantity = updateProductRequest.quantity!!
            updatedAt = Date()
        }

        productRepository.save(product)

        return convertProductToProductResponse(product)
    }

    override fun delete(id: String) {
        val product = findProductByIdOrThrowNotFound(id)
        productRepository.delete(product)
    }

    override fun list(listProductRequest: ListProductRequest): List<ProductResponse> {
        val page = productRepository.findAll(PageRequest.of(listProductRequest.page, listProductRequest.size))
        val products: List<Product> = page.get().collect(Collectors.toList())

        return products.map { convertProductToProductResponse(it) }
    }

    private fun convertProductToProductResponse(product: Product): ProductResponse = ProductResponse(
        id = product.id,
        name = product.name,
        price = product.price,
        quantity = product.quantity,
        createdAt = product.createdAt,
        updatedAt = product.updatedAt
    )

    private fun findProductByIdOrThrowNotFound(id: String): Product {
        return productRepository.findByIdOrNull(id) ?: throw  NotFoundException()
    }
}