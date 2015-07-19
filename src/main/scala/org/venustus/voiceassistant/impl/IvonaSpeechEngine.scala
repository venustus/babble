package org.venustus.voiceassistant.impl

import java.io.OutputStream

import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider
import com.ivona.services.tts.IvonaSpeechCloudClient
import com.ivona.services.tts.model.{Voice, Input, CreateSpeechRequest}
import org.venustus.voiceassistant.SpeechSynthesisEngine
import org.venustus.voiceassistant.output.SpeechOutputHandler

/**
 * Created by venkat on 18/07/15.
 */
class IvonaSpeechEngine extends SpeechSynthesisEngine {

    private val speechCloudClient = new IvonaSpeechCloudClient(
        new ClasspathPropertiesFileCredentialsProvider("ivona/IvonaCredentials.properties"))
    speechCloudClient setEndpoint ("https://tts.eu-west-1.ivonacloud.com")

    override def synthesizeSpeech(text: String, outputHandler: SpeechOutputHandler) = {
        val createSpeechRequest = new CreateSpeechRequest()
        val input = new Input()
        val voice = new Voice()

        voice.setName("Salli")
        input.setData(text)

        createSpeechRequest.setInput(input)
        createSpeechRequest.setVoice(voice)

        val speech = speechCloudClient createSpeech (createSpeechRequest)

        println("\nSuccess sending request:");
        println(" content type:\t" + speech.getContentType());
        println(" request id:\t" + speech.getTtsRequestId());
        println(" request chars:\t" + speech.getTtsRequestCharacters());
        println(" request units:\t" + speech.getTtsRequestUnits());

        val in = speech getBody()
        try {
            outputHandler processSpeech (speech getBody())
        }
        finally {
            if(in != null) in close()
        }
    }
}
