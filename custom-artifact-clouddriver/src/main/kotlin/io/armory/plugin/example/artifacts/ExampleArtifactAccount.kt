package io.armory.plugin.example.artifacts

import com.netflix.spinnaker.clouddriver.artifacts.config.ArtifactAccount
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
data class ExampleArtifactAccount(private val name: String) : ArtifactAccount {
    override fun getName(): String {
        return name
    }
}