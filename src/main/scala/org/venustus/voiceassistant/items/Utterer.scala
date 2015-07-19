package org.venustus.voiceassistant.items

import org.venustus.voiceassistant.SpeechSynthesisEngine
import org.venustus.voiceassistant.output.SpeechOutputHandler

/**
 * Created by venkat on 18/07/15.
 */
trait Utterer {

    def utter(item: Utterable, sg: SpeechSynthesisEngine, outputHandler: SpeechOutputHandler)

}
