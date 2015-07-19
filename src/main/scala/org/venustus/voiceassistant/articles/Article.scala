package org.venustus.voiceassistant.articles

import org.jsoup.Jsoup
import org.jsoup.nodes.{Element, TextNode, Node}
import org.jsoup.parser.Parser
import org.venustus.voiceassistant.items.Utterable
import scala.collection.JavaConverters._

/**
 * Created by venkat on 18/07/15.
 */
class Article(url: String) extends Utterable {
    val doc = (Jsoup connect (url)).get()
    val textualTagNames = Set("p", "i", "b", "em", "li", "ol")
    val excludeTagNames = Set("pre")
    val articleElems = doc.select("article")
    val articleContentRoot: Node = extractContentRoot(doc)._3.get

    def getContent: String = extractContent(articleContentRoot)

    def extractContent(n: Node): String = {
        if(n.isInstanceOf[TextNode]) n.asInstanceOf[TextNode].getWholeText
        else if(n.isInstanceOf[Element] && (excludeTagNames contains (n.asInstanceOf[Element].tagName))) ""
        else {
            ("" /: n.childNodes.asScala)((acc, child) => {
                acc + " " + extractContent(child)
            })
        }

    }

    def extractContentRoot(n: Node): (Int, Int, Option[Node]) = {
        if(n.isInstanceOf[TextNode]) {
            val textSize = n.asInstanceOf[TextNode].getWholeText.length
            (textSize, textSize, Some(n))
        }
        else if(n.isInstanceOf[Element]) {
            val elem = n.asInstanceOf[Element]
            var maxTextSize = 0
            var curTextSize = 0
            var maxElem: Option[Node] = None
            if(excludeTagNames contains (elem.tagName)) (0, 0, None)
            else {
                for (child <- elem.childNodes.asScala) {
                    val (childMax, childTotal, childMaxElem) = extractContentRoot(child)
                    if (childMax > maxTextSize) {
                        maxTextSize = childMax
                        maxElem = childMaxElem
                    }
                    if (child.isInstanceOf[TextNode] ||
                        (child.isInstanceOf[Element] &&
                            (textualTagNames contains (child.asInstanceOf[Element].tagName)))) {
                        curTextSize += childTotal
                    }
                }
                if (curTextSize > maxTextSize) (curTextSize, curTextSize, Some(n))
                else (maxTextSize, curTextSize, maxElem)
            }
        }
        else (0, 0, None)
    }

}
