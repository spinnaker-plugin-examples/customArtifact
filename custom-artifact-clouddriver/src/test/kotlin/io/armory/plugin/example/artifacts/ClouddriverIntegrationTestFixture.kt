/*
 * Copyright 2022 Armory, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.armory.plugin.example.artifacts

import com.netflix.spinnaker.clouddriver.api.test.ClouddriverFixture
import com.netflix.spinnaker.clouddriver.artifacts.ArtifactCredentialsRepository
import com.netflix.spinnaker.kork.plugins.internal.PluginJar
import java.io.File
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.test.context.TestPropertySource

@TestPropertySource(properties = ["spring.config.location=classpath:clouddriver-test-app.yml"])
@AutoConfigureMockMvc
class ClouddriverIntegrationTestFixture : ClouddriverFixture() {

  @Autowired
  lateinit var artifactCredentialsRepository: ArtifactCredentialsRepository

  init {
    val pluginId = "Armory.CustomArtifact"
    val plugins = File("build/plugins").also {
      it.delete()
      it.mkdir()
    }

    PluginJar.Builder(plugins.toPath().resolve("$pluginId.jar"), pluginId)
      .pluginClass(CustomArtifactPlugin::class.java.name)
      .pluginVersion("1.0.0")
      .build()
  }
}
