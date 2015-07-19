package org.venustus.voiceassistant.output

import java.io.{FileOutputStream, File, InputStream}

/**
 * Created by venkat on 18/07/15.
 */
class FileOutputHandler(file: File) extends SpeechOutputHandler {

    val fileOutputStream = new FileOutputStream(file)

    override def processSpeech(in: InputStream) = {
        val bytes = new Array[Byte](2 * 1024)
        try {
            Iterator continually (in read (bytes)) takeWhile (-1 !=) foreach (read => fileOutputStream.write(bytes, 0, read))
        }
        finally {
            fileOutputStream flush()
            fileOutputStream close()
        }
    }
}
