import java.nio.file.Paths

def getConfig(String configFile) {
  def configPath = Paths.get(".", "com", "planetpope", "config", configFile)
  def config = configPath.toFile()
  echo "config is ${config}"
  
  if (config.exists()) {
    config.withReader { reader ->
      new GroovyShell().evaluate(reader)
    }
  } else {
      error('Configuration file "${configFile}" not found')
  }
}

def call(String configFile){
  echo "configFile is ${configFile}"
  def config = libraryResource 'com/planetpope/config/config_Jira_Cloud.txt'
  echo "config is ${config}"
  def triggersConfig = config.toFile()

  triggersConfig.each {trigger ->
    properties([
      pipelineTriggers([
        [
          $class:'GenericTrigger',
          genericVariables: trigger.genericVariables,
          token: trigger.token,
          causeString: trigger.causeString,
          printContributedVariables: trigger.printContributedVariables,
          printPostContent: trigger.printPostContent,
          silentResponse: trigger.silentResponse,
          regexpFilterText: trigger.regexpFilterText,  
          regexpFilterExpression: trigger.regexpFilterExpression 
        ]
      ])
    ])
  }
}
  
