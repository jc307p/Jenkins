import java.nio.file.Paths
import java.util.Properties

def call(String configFile){
//  echo "configFile is ${configFile}"
  def basePath = 'com/planetpope/config/'
  def fullPath = basePath + configFile
  def configContent = libraryResource fullPath
//  echo "configContent is ${configContent}"
//  def configClass = config.getClass()
//  echo "config class is ${configClass}"

  Properties config = new Properties()
  config.load(new StringReader(configContent))

  String genericVariables = config.getProperty("genericVariables")
  echo "genericVaribles is ${genericVariables}"
  String token = config.getProperty("token")
  String causeString = config.getProperty("causeString")
  String printContributedVariables = config.getProperty("printContributedVariables")
  String printPostContent = config.getProperty("printPostContent")
  String silentResponse = config.getProperty("silentResponse")
  String regexpFilterText = config.getProperty("regexpFilterText")
  String regexpFilterExpression = config.getProperty("regexpFilterExpression")
  
//  echo "causeString is ${causeString}"

//  triggersConfig.each {trigger ->
    properties([
      pipelineTriggers([
        [
          $class:'GenericTrigger',
//          genericVariables: "${genericVariables}",
          token: "${token}",
          causeString: "${causeString}",
//          printContributedVariables: trigger.printContributedVariables,
//          printPostContent: trigger.printPostContent,
//          silentResponse: trigger.silentResponse,
//          regexpFilterText: trigger.regexpFilterText,  
          regexpFilterExpression: "${regexpFilterExpression}"
        ]
      ])
    ])
//  }
}
  
