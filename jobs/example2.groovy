String basePath = 'example2'
String repo = 'tass-belgium/picotcp'
String branch = 'development'
String slave = 'normal'
String fold_descr =  'Folder containing PicoTCP job generated from shell script.'
String job_descr = 'Running units and autotest on PicoTCP.'

folder(basePath) {
    description(fold_descr)
}

job("$basePath/pico-example-tests") {
    description(job_descr)
    label(slave)
    scm {
        github(repo,branch)
    }
    steps {
        shell readFileFromWorkspace('resources/runAllTests.sh')
    }
}

buildMonitorView('PicoTCP'){
  description("Build monitor for all the pico test jobs.")
  jobs{
    name("pico-builds")
    recurse()
    regex("(.*?)")
  }
}
