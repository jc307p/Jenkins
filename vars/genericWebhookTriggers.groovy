import java.nio.file.Paths
import java.util.Properties

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
  def basePath = 'com/planetpope/config/'
  def fullPath = basePath + configFile
  def configContent = libraryResource fullPath
  echo "configContent is ${configContent}"
//  def configClass = config.getClass()
//  echo "config class is ${configClass}"

  Properties config = new Properties()
  config.load(new StringReader(configContent))

  String causeString = config.getProperty("causeString")
  echo "causeString is ${causeString}"

//  triggersConfig.each {trigger ->
//    properties([
//      pipelineTriggers([
//        [
//          $class:'GenericTrigger',
//          genericVariables: trigger.genericVariables,
//          token: trigger.token,
//          causeString: trigger.causeString,
//          printContributedVariables: trigger.printContributedVariables,
//          printPostContent: trigger.printPostContent,
//          silentResponse: trigger.silentResponse,
//          regexpFilterText: trigger.regexpFilterText,  
//          regexpFilterExpression: trigger.regexpFilterExpression 
//        ]
//      ])
//    ])
//  }
}
  
