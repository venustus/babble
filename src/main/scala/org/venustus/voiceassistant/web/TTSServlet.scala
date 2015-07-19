package org.venustus.voiceassistant.web

import java.io.IOException
import java.net.URLDecoder
import javax.servlet.http.{HttpServletRequest, HttpServletResponse, HttpServlet}

import org.venustus.voiceassistant.impl.IvonaSpeechEngine
import org.venustus.voiceassistant.output.{HttpResponseOutputHandler, SpeechOutputHandler}

/**
 * Created by venkat on 18/07/15.
 */
class TTSServlet extends HttpServlet {
    override def doGet(req: HttpServletRequest, res: HttpServletResponse) = {
        val text = URLDecoder decode (req getParameter ("t"), "UTF-8")
        val ivonaEngine = new IvonaSpeechEngine()
        try {
            ivonaEngine synthesizeSpeech(text, new HttpResponseOutputHandler(res))
        }
        catch {
            case ioe: IOException => {
                println("Error while converting text to speech")
                res setStatus (500)
            }
        }
    }
}
