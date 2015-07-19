/**
 * Created by venkat on 18/07/15.
 */

window["commandManager"] = function(appName, commands, failureCallback) {
    var recognition,
        listening = false,
        startListeningForNextCommand = false,
        that = {
            "receiveNextCommand": function () {
                if(!listening) {
                    recognition.start();
                    listening = true;
                }
                else {
                    startListeningForNextCommand = true;
                }
            }
        },
        speakUnknownCommand = function() {
            var audioElem = document.createElement("audio");
            audioElem.setAttribute("autoplay", "true");
            audioElem.setAttribute("preload", "auto");
            audioElem.setAttribute("src", "/tts/tts?t=" + encodeURIComponent("Sorry, I did not get that. Try again!"));
            document.body.appendChild(audioElem);
        };

    if(!('webkitSpeechRecognition' in window)) {
        console.log("Speech recognition not supported in browser");
        return false;
    }

    recognition = new webkitSpeechRecognition();
    recognition.continuous = true;
    recognition.lang = "en-US";
    recognition.onstart = function() {
        console.log("Started listening");
    };
    recognition.onresult = function(event) {
        var transcript = "";
        for (var i = event.resultIndex; i < event.results.length; ++i) {
            if (event.results[i].isFinal) {
                transcript += event.results[i][0].transcript;
            }
        }
        console.log("Got transcript: " + transcript);

        if(typeof commands[transcript.trim()] === "function") {
            commands[transcript.trim()]();
        }
        else {
            console.log("Unkown command");
            failureCallback();
            speakUnknownCommand();
        }
        if(transcript.trim() === "shutdown") {
            console.log("Shutting down");
            recognition.stop();
        }
        return transcript;
    };
    recognition.onerror = function(event) {
        console.log("Error occurred while recognizing speech");
    };
    recognition.onend = function() {
        listening = false;
        console.log("Stopped Listening");
        if(startListeningForNextCommand) {
            startListeningForNextCommand = false;
            that.receiveNextCommand();
        }
    };

    return that;
};