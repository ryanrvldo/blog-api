package id.ryanrvldo.blogapi.repository

import id.ryanrvldo.blogapi.entity.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, String> {

}