String basePath = 'example3'
String repo = 'tass-belgium/picotcp'
String slave = 'normal'
String job1_descr = 'Perform a PicoTCP build'
String job2_descr = 'Run unit tests and make on PicoTCP'
String job3_descr = 'Run autotests and make on PicoTCP'

folder(basePath) {
    description 'This example shows how to create a job in its own folder. With a buildflow job to control it. And the exclusion plugin.'
}

    job("$basePath/pico-build") {
        description(job1_descr)
        label(slave)
        scm {
            github repo, branch.name
        }
        steps {
            shell 'make clean'
            shell 'make'
        }
    }

    job("$basePath/pico-units") {
        description(job2_descr)
        label(slave)
        scm {
            github repo, branch.name
        }
        wrappers{
            exclusionResources('test')
        }
        steps {
            shell 'make clean'
            shell 'make units ARCH=faulty'
            criticalBlock{
                shell 'test/units.sh'
            }
        }
    }

    job("$basePath/pico-autotest") {
        description(job3_descr)
        label(slave)
        scm {
            github repo, branch.name
        }
        wrappers{
            exclusionResources('test')
        }
        steps {
            shell 'make clean'
            shell 'make make test'
            criticalBlock{
                shell './test/autotest.sh'
            }
        }
    }

    buildFlowJob("$basePath/pico-buildflow") {
    triggers {
        scm 'H/5 * * * *'
    }
    buildFlow("""
    build("pico-build")
    parallel(
      build("pico-units")
      build("pico-autotest")
    )
    """)
    }
