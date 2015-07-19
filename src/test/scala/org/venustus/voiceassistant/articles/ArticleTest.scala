package org.venustus.voiceassistant.articles

import org.scalatest.{FlatSpec, Matchers}

/**
 * Created by venkat on 18/07/15.
 */
class ArticleTest extends FlatSpec with Matchers {

    "Article content extractor" should "extract main content" in {
        val article = new Article("https://www.washingtonpost.com/news/morning-mix/wp/2015/07/17/winning-formula-usa-tops-international-math-olympiad-for-first-time-in-21-years/")
        println(article.getContent)
        val article2 = new Article("https://medium.com/firm-narrative/want-a-better-pitch-watch-this-328b95c2fd0b")
        println(article2.getContent)
        val article3 = new Article("https://blogs.janestreet.com/introducing-incremental/?utm_source=rss&utm_medium=rss&utm_campaign=introducing-incremental")
        println(article3.getContent)
    }

}
