def call(Map config=[:]) {
  def rawBody = libraryResource 'com/planetpope/api/jira/createIssue.json'
  def binding = [
    key: "${config.key}",
    summary: "${config.summary}",
    description: "${config.description}",
    issueTypeName: "${config.issueTypeName}"
  ]
  def render = renderTemplate(rawBody,binding)
  
  bat """
    curl -D- -u $JIRA_CREDENTIALS $JIRA_URL/rest/api/latest/issue/ -H "Content-Type:application/json" -X POST --data "${render}" 
  """
}
