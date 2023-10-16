def call(Map config=[:]) {
  def rawBody = libraryResource 'com/planetpope/api/jira/createIssue.json'
  def binding = [
    key: "${config.key}",
    summary: "${config.summary}",
    description: "${config.description}",
    issueTypeName: "${config.issueTypeName}"
  ]
  def render = renderTemplate(rawBody,binding)
  
  echo "Render value is : ${render}"
  bat """
  curl -D- -u $JIRA_CREDENTIALS $JIRA_URL/rest/api/2/issue/DARP-54784/comment -X POST --data \"{\\"body\\": \\"This is new comment from Jenkins.\\"}\" -H \"Content-Type:application/json\"
  """
}
