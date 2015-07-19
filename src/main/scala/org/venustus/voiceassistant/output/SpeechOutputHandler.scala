package org.venustus.voiceassistant.output

import java.io.InputStream

/**
 * Created by venkat on 18/07/15.
 */
trait SpeechOutputHandler {
    def processSpeech(in: InputStream)
}
