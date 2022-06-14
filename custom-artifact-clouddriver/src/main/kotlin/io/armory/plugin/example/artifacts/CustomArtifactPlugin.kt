package io.armory.plugin.example.artifacts

import com.netflix.spinnaker.kork.plugins.api.spring.SpringLoaderPlugin
import org.pf4j.PluginWrapper
import org.slf4j.LoggerFactory

class CustomArtifactPlugin(wrapper: PluginWrapper) : SpringLoaderPlugin(wrapper) {

    private val logger = LoggerFactory.getLogger(CustomArtifactPlugin::class.java)

    override fun getPackagesToScan(): List<String> {
        return listOf(
                "io.armory.plugin.example.artifacts"
        )
    }

    override fun start() {
        logger.info("SpringExamplePlugin.start()")
    }

    override fun stop() {
        logger.info("SpringExamplePlugin.stop()")
    }
}
