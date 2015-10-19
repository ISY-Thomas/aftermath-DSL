String basePath = 'example1'
String repo = 'tass-belgium/picotcp'

folder(basePath) {
    description 'This example shows basic folder/job creation.'
}

job("$basePath/pico-example-build") {
    description 'First example of a PicoTCP build'
    scm {
        github repo
    }
    triggers {
        scm 'H/5 * * * *'l
    }
    steps {
        shell 'make clean'
        shell 'make'
    }
}
