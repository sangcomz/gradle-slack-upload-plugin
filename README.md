```
                         _  _                    _               _              
   __ _  _ __  __ _   __| || |  ___         ___ | |  __ _   ___ | | __          
  / _` || '__|/ _` | / _` || | / _ \ _____ / __|| | / _` | / __|| |/ /          
 | (_| || |  | (_| || (_| || ||  __/|_____|\__ \| || (_| || (__ |   <           
  \__, ||_|   \__,_| \__,_||_| \___|       |___/|_| \__,_| \___||_|\_\          
  |___/         _                    _                _                _        
  _   _  _ __  | |  ___    __ _   __| |        _ __  | | _   _   __ _ (_) _ __  
 | | | || '_ \ | | / _ \  / _` | / _` | _____ | '_ \ | || | | | / _` || || '_ \ 
 | |_| || |_) || || (_) || (_| || (_| ||_____|| |_) || || |_| || (_| || || | | |
  \__,_|| .__/ |_| \___/  \__,_| \__,_|       | .__/ |_| \__,_| \__, ||_||_| |_|
        |_|                                   |_|               |___/           
```
# gradle-slack-upload-plugin

## What's New in 0.0.2? :tada:
- [Release] Release `gradle-slack-upload-plugin` :tada:

## How to Use

### Get Slack bots API Token

<img src="/image/slackbotapi.png">

### Usage
```groovy
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath "xyz.sangcomz:gradle-slack-upload-plugin:0.0.2"
    }
}
```

```groovy
apply plugin: 'xyz.sangcomz.gradle'

slackUploader {
    token "your_slack_bot_api_token"
    channels "your_channel1,your_channel2"
    filePath "your_file_path"
    title "your_file_title"
    initialComment "your_initial_comment"
}

//example
slackUploader {
    token "my_slack_bot_api_token"
    channels "wowchannel"
    title "File Title"
    initialComment "Upload Sample.txt"
    filePath "sample.txt"
}
```

<img src="/image/example.png">

# Contribute
Welcome any contributions.

# License

    Copyright 2020 Seokwon Jeong

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.



