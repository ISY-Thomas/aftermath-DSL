String basePath = 'example1'
String repo = 'tass-belgium/picotcp'
String slave = 'normal'

folder(basePath) {
    description 'This example shows basic folder/job creation.'
}

job("$basePath/pico-example-build") {
    description 'First example of a PicoTCP build'
    restrictToLabel slave
    scm {
        github repo
    }
    triggers {
        scm 'H/5 * * * *'
    }
    steps {
        shell 'make clean'
        shell 'make'
    }
}
