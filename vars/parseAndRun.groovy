def parseAndRun(String A) {
    def A_dict = new groovy.json.JsonSlurper().parseText("${A}")
    echo "${A_dict}"
}
