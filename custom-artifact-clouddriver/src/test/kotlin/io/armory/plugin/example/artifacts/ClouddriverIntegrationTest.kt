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

import com.netflix.spinnaker.clouddriver.api.test.clouddriverFixture
import dev.minutest.junit.JUnit5Minutests
import dev.minutest.rootContext
import strikt.api.expect
import strikt.assertions.isNotNull

class ClouddriverIntegrationTest : JUnit5Minutests {

    fun tests() = rootContext<ClouddriverIntegrationTestFixture> {
        context("a running Clouddriver instance") {
            clouddriverFixture {
                ClouddriverIntegrationTestFixture()
            }

            test("test") {
                println(artifactCredentialsRepository.allCredentials)
                expect {
                    that(artifactCredentialsRepository.getCredentials("test1", "artifacts-example")).isNotNull()
                }
            }

        }
    }

}
