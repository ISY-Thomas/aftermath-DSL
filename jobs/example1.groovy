String basePath = 'example1'
String repo = 'tass-belgium/picotcp'
String branch = 'development'
String slave = 'normal'
String fold_descr =  'This example shows basic folder/job creation.'
String job_descr = 'First example of a PicoTCP build'

folder(basePath) {
    description(fold_descr)
}

job("$basePath/pico-example-build") {
    description(job_descr)
    label(slave)
    scm {
        github repo branch
    }
    triggers {
        scm 'H/5 * * * *'
    }
    steps {
        shell 'make clean'
        shell 'make'
    }
}
