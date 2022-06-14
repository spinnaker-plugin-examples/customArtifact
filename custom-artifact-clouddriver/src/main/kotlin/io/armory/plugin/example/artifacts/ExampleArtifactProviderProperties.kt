package io.armory.plugin.example.artifacts

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("artifacts.example")
data class ExampleArtifactProviderProperties(
        var accounts: List<ExampleArtifactAccount>? = null
)
