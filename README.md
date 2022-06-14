![CI](https://github.com/spinnaker-plugin-examples/springExamplePlugin/workflows/CI/badge.svg)

This plugin exercises the Spring capabilities of Spinnaker plugins. There are no PF4J extension points or dependencies. Instead everything is Spring components.

It tests the following cases and all of it works without having to use Kork plugin's "unsafe" mode:
* Component and Primary Spring annotations are recognized
* The Configuration Spring annotation will be recognized in a plugin bean so you can create beans that way
* Spring package scanning of your plugin
* New plugin beans are wired together
* App beans are autowired into plugin beans
* Plugin beans are autowired into the app beans and replace app beans if primary (this allows modifying existing spinnaker behavior)
* Spring properties are recognized
* Controllers add new endpoints
* New dependencies that are not in Spinnaker can be used in your plugin beans

Your plugin needs to extend SpringLoaderPlugin and implement getPackagesToScan(). See SpringExamplePlugin for an example.

<h2>Usage</h2>

1) Run `./gradlew releaseBundle`
2) Put the `/build/distributions/<project>-<version>.zip` into the [configured plugins location for your service](https://pf4j.org/doc/packaging.html).
3) Configure the Spinnaker service. Put the following in the service yml to enable the plugin and configure the extension.
```
spinnaker:
  extensibility:
    plugins:
      Armory.SpringExamplePlugin:
        enabled: true

newproperties:
  test: 'test1'
```

Or use the [examplePluginRepository](https://github.com/spinnaker-plugin-examples/examplePluginRepository) to avoid copying the plugin `.zip` artifact.

To debug the plugin inside a Spinnaker service (like Orca) using IntelliJ Idea follow these steps:

1) Run `./gradlew releaseBundle` in the plugin project.
2) Copy the generated `.plugin-ref` file under `build` in the plugin project submodule for the service to the `plugins` directory under root in the Spinnaker service that will use the plugin .
3) Link the plugin project to the service project in IntelliJ (from the service project use the `+` button in the Gradle tab and select the plugin build.gradle).
4) Configure the Spinnaker service the same way specified above.
5) Create a new IntelliJ run configuration for the service that has the VM option `-Dpf4j.mode=development` and does a `Build Project` before launch.
6) Debug away...
