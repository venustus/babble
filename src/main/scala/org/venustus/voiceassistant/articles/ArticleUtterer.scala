package org.venustus.voiceassistant.articles

import org.venustus.voiceassistant.SpeechSynthesisEngine
import org.venustus.voiceassistant.items.{Utterable, Utterer}
import org.venustus.voiceassistant.output.SpeechOutputHandler

/**
 * Created by venkat on 18/07/15.
 */
class ArticleUtterer extends Utterer {

    override def utter(article: Utterable, sg: SpeechSynthesisEngine, oh: SpeechOutputHandler): Unit = {
        if(!article.isInstanceOf[Article]) throw new IllegalAccessException("I can only utter articles")
        sg.synthesizeSpeech(article.asInstanceOf[Article].getContent.substring(0, 8100), oh)
    }
}
