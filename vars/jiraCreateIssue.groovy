def call(Map config=[:]) {
  def rawBody = libraryResource 'com/planetpope/api/jira/createIssue.json'
  def binding = [
    key: "${config.key}",
    summary: "${config.summary}",
    description: "${config.description}",
    issueTypeName: "${config.issueTypeName}"
  ]
  def render = renderTemplate(rawBody,binding)
  
bat ("curl -D- -u $JIRA_CREDENTIALS -X POST --data {\"body\": \"This is new comment from Jenkins.\"} -H \"Content-Type:application/json\" $JIRA_URL/rest/api/2/issue/DARP-54784/comment")
}
