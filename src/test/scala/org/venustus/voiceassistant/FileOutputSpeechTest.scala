package org.venustus.voiceassistant

import java.io.File

import org.scalatest.{Matchers, FlatSpec}
import org.venustus.voiceassistant.impl.IvonaSpeechEngine
import org.venustus.voiceassistant.output.FileOutputHandler

/**
 * Created by venkat on 18/07/15.
 */
class FileOutputSpeechTest extends FlatSpec with Matchers {
    val ivonaEngine = new IvonaSpeechEngine()
    val outputDir = new File("/Users/venkat/Documents/Projects/social-voice-assistant/resources/audio")
    "File output of speech" should "work" in {
        ivonaEngine synthesizeSpeech ("Hello World! This is our first text to speech synthesis",
                                        new FileOutputHandler(new File(outputDir, "/test.mp3")))
    }

}
