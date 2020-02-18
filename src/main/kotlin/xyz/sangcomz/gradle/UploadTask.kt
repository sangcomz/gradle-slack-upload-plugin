package xyz.sangcomz.gradle


import com.github.kittinunf.fuel.core.FileDataPart
import com.github.kittinunf.fuel.core.Parameters
import com.github.kittinunf.fuel.core.isSuccessful
import com.github.kittinunf.fuel.httpUpload
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.io.*
import java.util.regex.Pattern
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

open class UploadTask : DefaultTask() {

    init {
        group = "slack file upload"
        description = "File Uploader"
    }

    @TaskAction
    fun task() {
        val slackUploadPluginExtension =
            project.extensions.getByType(SlackUploadPluginExtension::class.java)

        val uploadFile = if (!slackUploadPluginExtension.filePaths.isNullOrEmpty()) {
            zip(
                slackUploadPluginExtension.filePaths!!,
                slackUploadPluginExtension.zipName,
                slackUploadPluginExtension.zipFilePath
            )
        } else {
            File(slackUploadPluginExtension.filePath)
        }

        uploadFile(
            uploadFile,
            getParameters(slackUploadPluginExtension),
            slackUploadPluginExtension.deleteZipFileAfterUpload
        )
    }

    private fun uploadFile(file: File, parameters: Parameters, isZipFileDeleteAfterUpload: Boolean) {
        "https://slack.com/api/files.upload"
            .httpUpload(parameters)
            .add(
                FileDataPart(file, "file")
            )
            .response { request, response, result ->
                result.fold({
                    if (isZipFileDeleteAfterUpload
                        && file.extension.isExtensionZip()
                    )
                        file.delete()
                    println(response.body().asString("application/json"))
                }, {
                    println(it)
                })
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

    private fun zip(files: Array<String>, zipFileName: String?, zipFilePath: String?): File {
        val name = "${zipFileName ?: "upload"}.zip"
        val path = if (zipFilePath.isNullOrEmpty()) "" else "$zipFilePath/"
        File(path).mkdirs()
        ZipOutputStream(BufferedOutputStream(FileOutputStream(path + name))).use { out ->
            val data = ByteArray(1024)
            for (file in files) {
                FileInputStream(file).use { fi ->
                    BufferedInputStream(fi).use { origin ->
                        val entry = ZipEntry(file)
                        out.putNextEntry(entry)
                        while (true) {
                            val readBytes = origin.read(data)
                            if (readBytes == -1) {
                                break
                            }
                            out.write(data, 0, readBytes)
                        }
                    }
                }
            }
        }
        return File(path + name)
    }

    private fun String.isExtensionZip() = this == "zip"
}

