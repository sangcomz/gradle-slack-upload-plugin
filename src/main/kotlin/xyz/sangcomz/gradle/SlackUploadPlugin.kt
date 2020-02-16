package xyz.sangcomz.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project

class SlackUploadPlugin : Plugin<Project> {
    override fun apply(target: Project): Unit = target.run {
        extensions.create("slackUploader", SlackUploadPluginExtension::class.java)
        tasks.register("uploadFileToSlack", UploadTask::class.java)
    }
}