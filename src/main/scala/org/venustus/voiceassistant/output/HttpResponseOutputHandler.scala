package org.venustus.voiceassistant.output

import java.io.InputStream
import javax.servlet.http.HttpServletResponse

/**
 * Created by venkat on 18/07/15.
 */
class HttpResponseOutputHandler(httpRes: HttpServletResponse) extends SpeechOutputHandler {

    val servletOutputStream = httpRes getOutputStream()

    override def processSpeech(in: InputStream) = {
        val bytes = new Array[Byte](2 * 1024)
        Iterator continually (in read (bytes)) takeWhile (-1 !=) foreach (read => servletOutputStream write (bytes, 0, read))
    }

}
