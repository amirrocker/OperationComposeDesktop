package de.amirrocker.operationcomposedesktop.ui

import de.amirrocker.operationcomposedesktop.typesafebuilder.HtmlElement
import de.amirrocker.operationcomposedesktop.typesafebuilder.Tag
import de.amirrocker.operationcomposedesktop.typesafebuilder.html
import de.amirrocker.operationcomposedesktop.typesafebuilder.title
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TypeSafeBuildersTest {

//    @Test
//    fun `given a kotlinlang documentation when reading then expect learning as result`() {
//        // given
//        val title = "a basic html node"
//        val html = html {
//            this.title = title
//        }
//
//        // then
//        assertEquals(title, html.title, "expect a value of $title but was ${html.title}")
//    }
//
//    @Test
//    internal fun `given head then title is needed`() {
//        val headTitle = "head title"
//        val html = html {
//            val head = head {
//                this.title = headTitle
//            }
//            assertEquals(headTitle, head.title, "Expect valid head title $headTitle but was ${head.title} ")
//        }
//    }
//
//    @Test
//    internal fun `given body then body text must be inited`() {
//        val bodyText = "body text"
//        val html = html {
//            val body = body {
//                this.text = bodyText
//            }
//            assertEquals(bodyText, body.text, "Expect valid head title $bodyText but was ${body.text} ")
//        }
//    }
//
//    @Test
//    internal fun `given a complete html object then body and header text must be inited`() {
//        val bodyText = "body text"
//        val headTitle = "head title"
//        val html = html {
//            val head = head {
//                this.title = headTitle
//            }
//            val body = body {
//                this.text = bodyText
//            }
//            assertEquals(headTitle, head.title, "Expect valid head title $headTitle but was ${head.title}")
//            assertEquals(bodyText, body.text, "Expect valid body text $bodyText but was ${body.text} ")
//        }
//    }

//    @Test
//    internal fun `given html when adding head inside head then throw exception`() {
//
//        // something is not correct - the @DslMarker Annotation does not prevent the head in head...
//        val html = html {
//            head {
//                head {
//                    "sldfkjalsjflaskjf a"
//                }
//            }
//        }
//
//    }

    @Test
    internal fun `test we can render the html`() {
        val obj = object : HtmlElement {
            override val name: String
                get() = "some string name"

            override fun render(builder: StringBuilder, indent: String) {
                println("render")
            }
        }
        assertTrue(obj.name == "some string name", "expect some name but was ${obj.name}")

        val clazz = object : Tag("some name") {
            override val name: String
                get() = super.name
        }

        assertTrue(clazz.name == "some name", "expect some name but was ${clazz.name}")
    }

    @Test
    internal fun `given html when creating head then expect head instance with title builder`() {
        val htmlTitle = "html title"
        val headTitle = "head title"
        val html = html {
            +htmlTitle
            head {
                title { +headTitle }
            }
        }
        println(html)


    }

    @Test
    internal fun `given html when creating head then expect head instance`() {

        val html = html {
            head {
                title { +"Title text to append" }
            }
//            body {
//                p {+"paragraph 0 text to append" }
//                h1 {+"Headline text to append" }
//                p {+"paragraph 1 text to append" }
//                p {+"paragraph 2 text to append" }
//
//            }
        }
    }
}