def getConfig(String configFile) {
  def workspace = new File('.').toURI()
  def config = new File(workspace.resolve(configFile).toURL().toURI())
  if (config.exists()) {
    config.withReader { reader ->
      new GroovyShell().evaluate(reader)
    }
  } else {
      error('Configuration file "${configFile}" not found')
  }
}

def call(String configFile){
  def triggersConfig = getConfig(configFile)

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
  
