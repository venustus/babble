package org.venustus.voiceassistant.web

import java.io.IOException
import java.net.URLDecoder
import javax.servlet.http.{HttpServletResponse, HttpServletRequest, HttpServlet}

import org.venustus.voiceassistant.articles.{Article, ArticleUtterer}
import org.venustus.voiceassistant.impl.IvonaSpeechEngine
import org.venustus.voiceassistant.output.HttpResponseOutputHandler

/**
 * Created by venkat on 18/07/15.
 */
class ArticleUttererServlet extends HttpServlet {
    override def doGet(req: HttpServletRequest, res: HttpServletResponse) = {
        val url = URLDecoder decode (req getParameter ("u"), "UTF-8")
        val ivonaEngine = new IvonaSpeechEngine()
        try {
            new ArticleUtterer().utter(new Article(url), ivonaEngine, new HttpResponseOutputHandler(res))
        }
        catch {
            case ioe: IOException => {
                println("Error while converting text to speech")
                res setStatus (500)
            }
        }
    }
}
