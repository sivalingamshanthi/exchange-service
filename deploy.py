import sys
import requests

token = sys.argv[1]
apiBase = 'https://api.run.pivotal.io/v3'
spaceGuid = 'ca81d879-eeea-4653-8b67-9b21011a8e84'
appName = 'exchange-service'
appGuid = 'edf7e34d-7eef-42d0-9ed5-edaaaa0d4d86'
headers = {'Authorization': 'bearer ' + token}
jarPath ='build/libs/exchange-service-0.0.1-SNAPSHOT.jar'

def check(expectedStatusCode, actualStatusCode, message, response):
    if actualStatusCode == 401:
        print('401 unauthorized, invalid token' + '\n' + '\n')
        quit()

    if expectedStatusCode != actualStatusCode:
        print(message + '\n')
        print(actualStatusCode + '\n')
        print(response + '\n' + '\n')
        quit()

#create a new package
data = '{{ "type": "bits", "relationships": {{ "app": {{ "data": {{ "guid": "{}" }} }} }} }}'.format(appGuid)
r = requests.post(apiBase + '/packages', headers = headers, data = data)
check(201, r.status_code, 'unable to create a package', r.text)
print('created package')
packageGuid = r.json()['guid']

#upload bits to package
print('uploading bits...')
files = {'bits': open(jarPath, 'rb')}
r = requests.post(apiBase + '/packages/{}/upload'.format(packageGuid), headers = headers, files = files)
check(200, r.status_code, 'unable to upload bits', r.text)
print('uploaded bits')

#stage package and create build


#wait until build reaches staged

#get droplet

#assign droplet to app

#restart app

#remove stuff?