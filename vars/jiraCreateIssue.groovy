def call(Map config=[:]) {
  def rawBody = libraryResource 'com/planetpope/api/jira/createIssue.json'
  def binding = [
    key: "${config.key}",
    summary: "${config.summary}",
    description: "${config.description}",
    issueTypeName: "${config.issueTypeName}"
  ]
  def render = renderTemplate(rawBody,binding)
  echo "${render}"
  bat('curl -D- -u TEST_Jenkins:test1234 -X POST --data "{\"fields\": {\"project\":{\"key\": \"DAIOT\"},\"summary\": \"Issue Created from Jenkins\",\"description\": \"This issue is created from Jenkins\",\"issuetype\": {\"name\": \"DA IOT Defect\"}}}" -H "Content-Type: application/json" https://darp-jira-sbx.test.att.com//rest/api/2/issue')

}
