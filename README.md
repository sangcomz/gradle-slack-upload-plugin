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

## What's New in 1.0.0? :tada:
- [Feature] Support Multi File

## How to Use

### Get Slack bots API Token

<img src="/image/slackbotapi.png">

### Usage
<details open><summary>Kotlin</summary>

```kotlin
buildscript {
    repositories {
        maven {
            url = uri("/Users/sangcomz/projects/gradle-slack-upload-plugin/repo")
        }
    }

    dependencies {
        classpath("xyz.sangcomz:gradle-slack-upload-plugin:0.0.4")
    }
}
```

</details>

<details><summary>Groovy</summary>

```groovy
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath "xyz.sangcomz:gradle-slack-upload-plugin:1.0.0"
    }
}
```

</details>

<details open><summary>Kotlin</summary>

```kotlin
apply {
    plugin("xyz.sangcomz.gradle")
}

configure<xyz.sangcomz.gradle.SlackUploadPluginExtension> {
    token = "your_slack_bot_api_token" 
    channels = "your_channel1,your_channel2"
    title = "your_file_title" (optional)
    initialComment = "your_initial_comment" (optional)
    filePath = "your_file_path" 
    filePaths = arrayOf("your_file_path1.txt", "your_file_path2.txt", "your_file_path3.txt")
    zipName = "your_zip_file_name" (optional)
    zipFilePath = "your_zip_file_path" (optional)
    deleteZipFileAfterUpload = false (optional)
}
```

</details>

<details><summary>Groovy</summary>

```groovy
apply plugin: 'xyz.sangcomz.gradle'

slackUploader {
    token = "your_slack_bot_api_token" 
    channels = "your_channel1,your_channel2"
    title = "your_file_title" (optional)
    initialComment = "your_initial_comment" (optional)
    filePath = "your_file_path" 
    filePaths = ["your_file_path1.txt", "your_file_path2.txt", "your_file_path3.txt"]
    zipName = "your_zip_file_name" (optional)
    zipFilePath = "your_zip_file_path" (optional)
    deleteZipFileAfterUpload = false (optional)
}
```

</details>

```groovy
slackUploader {
    token = "my_slack_bot_api_token"
    channels = "wowchannel"
    title = "File Title"
    initialComment = "Upload Sample.txt"
    //filePath = "sample.txt"
    filePaths = ["sample.txt", "sample2.txt", "sample3.txt"]
    zipName = "wowUploada"
    zipFilePath = "build/zip"
    deleteZipFileAfterUpload = false
}
```
filePath or filePaths require input.

#### FilePath Upload Result
<img src="/image/example.png" width="50%">

#### FilePaths Upload Result
<img src="/image/examplezip.png" width="50%">

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



