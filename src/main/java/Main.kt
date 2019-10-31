import net.lingala.zip4j.ZipFile
import net.lingala.zip4j.model.ZipParameters
import net.lingala.zip4j.model.enums.EncryptionMethod
import java.io.File

fun main(args: Array<String>) {

    if (args.size != 1) {
        System.err.println("wrong args!")
        return
    }

    val dir = getTargetDir(args[0])

    val outputFile = "${dir.name}.zip"

    val password = passGenerate()

    val zipParam = ZipParameters().apply {
        isEncryptFiles = true
        encryptionMethod = EncryptionMethod.ZIP_STANDARD
    }

    ZipFile(outputFile, password.toCharArray()).addFolder(dir, zipParam)

    println("password")
    println(password)
}

fun getTargetDir(s: String): File {

    val absolutePathFile = File(s)

    if (File(s).exists()) {
        return File(absolutePathFile.canonicalPath)
    }

    throw IllegalArgumentException(" Illegal file path")
}

fun passGenerate(): String {
    return (1..12).map { ('!'..'}').random() }.joinToString("")
}