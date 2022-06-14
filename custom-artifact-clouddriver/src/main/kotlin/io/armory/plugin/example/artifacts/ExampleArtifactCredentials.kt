package io.armory.plugin.example.artifacts

import com.netflix.spinnaker.clouddriver.artifacts.config.ArtifactCredentials
import com.netflix.spinnaker.kork.artifacts.model.Artifact
import java.io.ByteArrayInputStream
import java.io.InputStream


class ExampleArtifactCredentials(val account: ExampleArtifactAccount) : ArtifactCredentials {

    companion object Static {
        val credentialsType = "artifacts-example"
    }

    override fun download(artifact: Artifact): InputStream {
        return ByteArrayInputStream("example".toByteArray())
    }

    override fun getTypes(): List<String> {
        return listOf("example/object")
    }


    override fun getName(): String {
        return account.name
    }

    override fun getType(): String {
        return "artifacts-example"
    }

}