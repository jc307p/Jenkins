import java.nio.file.Paths
import java.util.Properties

def call(String configFile){
  
  def basePath = 'com/planetpope/config/'
  def fullPath = basePath + configFile
  def configContent = libraryResource fullPath
  
  //Setting up Properties class to enable reading config file
  Properties config = new Properties()
  config.load(new StringReader(configContent))

  //Setting up variables from the config file
  //Unable to do genericVariables. GenericWorkflowTrigger requires this to be a list and there needs to be a looping mechanism as we add more key:value pairs if there are additional variables we would like to capture
  String genericVariables = config.getProperty("genericVariables")
  echo "genericVaribles is ${genericVariables}"
  String token = config.getProperty("token")
  String causeString = config.getProperty("causeString")
  String printContributedVariables = config.getProperty("printContributedVariables")
  String printPostContent = config.getProperty("printPostContent")
  String silentResponse = config.getProperty("silentResponse")
  String regexpFilterText = config.getProperty("regexpFilterText")
  String regexpFilterExpression = config.getProperty("regexpFilterExpression")
  
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
  
