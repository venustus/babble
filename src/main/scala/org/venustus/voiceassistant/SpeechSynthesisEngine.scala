package org.venustus.voiceassistant

import org.venustus.voiceassistant.output.SpeechOutputHandler

/**
 * Created by venkat on 18/07/15.
 */
trait SpeechSynthesisEngine {

    def synthesizeSpeech(text: String, outputHandler: SpeechOutputHandler)

}
