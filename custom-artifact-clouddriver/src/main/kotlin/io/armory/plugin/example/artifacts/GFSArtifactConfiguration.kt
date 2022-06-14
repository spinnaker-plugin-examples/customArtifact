package io.armory.plugin.example.artifacts

import com.netflix.spinnaker.credentials.CredentialsTypeProperties
import com.netflix.spinnaker.kork.plugins.api.spring.ExposeToApp
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(ExampleArtifactProviderProperties::class)
class GFSArtifactConfiguration {

    @Bean
    @ExposeToApp
    open fun exampleCredentialsProperties(exampleArtifactProviderProperties: ExampleArtifactProviderProperties): CredentialsTypeProperties<ExampleArtifactCredentials, ExampleArtifactAccount> {
        return CredentialsTypeProperties.builder<ExampleArtifactCredentials, ExampleArtifactAccount>()
        .type(ExampleArtifactCredentials.credentialsType)
                .credentialsClass(ExampleArtifactCredentials::class.java)
                .credentialsDefinitionClass(ExampleArtifactAccount::class.java)
                .defaultCredentialsSource { exampleArtifactProviderProperties.accounts!! }
                .credentialsParser { a ->
                    try {
                        ExampleArtifactCredentials(a)
                    } catch (e: Exception) {
                        null
                    }
                }
                .build()
    }

}
