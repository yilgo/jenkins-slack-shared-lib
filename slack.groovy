import groovy.json.JsonOutput
def notify(String channel, String message, String icon_emoji = ":jenkins:", String proxy = ""){
  
  def secrets = [
  [
    path: "secret/global/slack/channels/${channel}", engineVersion: 2, secretValues: [
      [envVar: "ENDPOINT", vaultKey: "endpoint"],
    ]
  ]
  ]
  
  
  def slack_payload = JsonOutput.toJson([text      : message,
                                  //channel   : channel,
                                  username  : "jenkins-alert@${JENKINS_INSTANCE_ENV}",
                                  icon_emoji: icon_emoji])
  
  withVault([vaultSecrets: secrets]){
  def params = [
              url: "${env.ENDPOINT}",
              contentType: "APPLICATION_JSON",
              httpMode: "POST",
              requestBody: slack_payload]
  
  (proxy != null && proxy != "") ? params["httpProxy"] = proxy : params      
  
  def response = httpRequest params
  }
  
  }
  
  return this
