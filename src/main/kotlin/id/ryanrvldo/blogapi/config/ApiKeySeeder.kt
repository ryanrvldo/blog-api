package id.ryanrvldo.blogapi.config

import id.ryanrvldo.blogapi.entity.ApiKey
import id.ryanrvldo.blogapi.repository.ApiKeyRepository
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.stereotype.Component

// Executed when application running
@Component
class ApiKeySeeder(val apiKeyRepository: ApiKeyRepository) : ApplicationRunner {

    val apiKey = "secret-api-key"

    override fun run(args: ApplicationArguments?) {
        if (!apiKeyRepository.existsById(apiKey)) {
            val apiKeyEntity = ApiKey(apiKey)
            apiKeyRepository.save(apiKeyEntity)
        }
    }

}