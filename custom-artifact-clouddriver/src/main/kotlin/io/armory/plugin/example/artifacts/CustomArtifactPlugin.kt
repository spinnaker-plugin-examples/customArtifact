package io.armory.plugin.example.artifacts

import com.netflix.spinnaker.kork.plugins.api.spring.SpringLoader
import com.netflix.spinnaker.kork.plugins.api.spring.SpringLoaderPlugin
import org.pf4j.PluginWrapper
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.support.BeanDefinitionRegistry
import java.util.*

class CustomArtifactPlugin(wrapper: PluginWrapper) : SpringLoaderPlugin(wrapper) {

    private val logger = LoggerFactory.getLogger(CustomArtifactPlugin::class.java)

    override fun registerBeanDefinitions(registry: BeanDefinitionRegistry) {
        super.registerBeanDefinitions(registry)
        val springLoaderBeanName = wrapper.pluginId + "." + SpringLoader::class.java.name
        // load plugin beans before artifactCredentialsRepository init
        makeAppBeanDependOnPluginBeans("artifactCredentialsRepository", springLoaderBeanName, registry)
    }
    protected fun makeAppBeanDependOnPluginBeans(dependantBeanName: String,
                                                 springLoaderBeanName: String,
                                                 registry: BeanDefinitionRegistry) {
        val requestMappingHandlerMapping = registry.getBeanDefinition(dependantBeanName)
        val mappingDependsOn = (requestMappingHandlerMapping.dependsOn ?: arrayOf<String>()) + springLoaderBeanName
        requestMappingHandlerMapping.setDependsOn(*mappingDependsOn)
    }

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
