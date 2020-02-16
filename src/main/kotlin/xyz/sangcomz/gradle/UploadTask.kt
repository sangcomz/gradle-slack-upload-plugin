package xyz.sangcomz.gradle


import com.github.kittinunf.fuel.core.FileDataPart
import com.github.kittinunf.fuel.core.Parameters
import com.github.kittinunf.fuel.httpUpload
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.io.File

open class UploadTask : DefaultTask() {

    init {
        group = "slack file upload"
        description = "File Uploader"
    }

    @TaskAction
    fun task() {
        val slackUploadPluginExtension =
            project.extensions.getByType(SlackUploadPluginExtension::class.java)
        uploadFile(File(slackUploadPluginExtension.filePath), getParameters(slackUploadPluginExtension))
    }

    private fun uploadFile(file: File, parameters: Parameters) {
        "https://slack.com/api/files.upload"
            .httpUpload(parameters)
            .add(
                FileDataPart(file, "file")
            )
            .response { request, response, result ->
                println(request)
                println(response)
                println(result)
            }.get()
    }

    private fun getParameters(slackUploadPluginExtension: SlackUploadPluginExtension): ArrayList<Pair<String, Any>> {
        val token = slackUploadPluginExtension.token ?: run {
            throw IllegalArgumentException("token is null")
        }
        return arrayListOf<Pair<String, Any>>().apply {
            add("token" to token)
            slackUploadPluginExtension.channels?.let {
                add("channels" to it)
            }
            slackUploadPluginExtension.initialComment?.let {
                add("initial_comment" to it)
            }
            slackUploadPluginExtension.title?.let {
                add("title" to it)
            }
        }
    }
}