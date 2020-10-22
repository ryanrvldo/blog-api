package id.ryanrvldo.blogapi.repository

import id.ryanrvldo.blogapi.entity.ApiKey
import org.springframework.data.jpa.repository.JpaRepository

interface ApiKeyRepository : JpaRepository<ApiKey, String> {
}