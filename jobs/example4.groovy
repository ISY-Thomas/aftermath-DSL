String basePath = 'example1'
String repo = 'tass-belgium/picotcp'
String branch = 'development'
String slave = 'normal'
String fold_descr =  'This example shows basic folder/job creation.'
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
  description("Build monitor for all the pico test job.")
  jobs{
    name("pico-builds")
    regex("*pico*")
  }
}
