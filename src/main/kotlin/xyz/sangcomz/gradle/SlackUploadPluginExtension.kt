package xyz.sangcomz.gradle

open class SlackUploadPluginExtension(
    var token: String? = null,
    var initialComment: String? = null,
    var title: String? = null,
    var channels: String? = null,
    var filePath: String? = null,
    var zipName: String? = null,
    var filePaths: Array<String>? = null,
    var zipFilePath: String? = null,
    var deleteZipFileAfterUpload: Boolean = false
)