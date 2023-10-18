def parseAndRun(String A){
  def A_dict = new groovy.json.JsonSlurper().parseText("{${A}")

  def script(Map<String, String> kwargs) {
    kwargs.each { key, value ->
        println "${key}: ${value}"
    }
  }
  script(A_dict)
}
