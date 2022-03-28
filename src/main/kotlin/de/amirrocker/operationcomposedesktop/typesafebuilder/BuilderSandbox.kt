package de.amirrocker.operationcomposedesktop.typesafebuilder

import kotlinx.html.HtmlTagMarker


/**
 * this code is based on the kotlinlang documentation of TypesafeBuilders.
 * https://kotlinlang.org/docs/type-safe-builders.html#how-it-works
 * Note that it seems to be based on KotlinJS since HTML is not a valid kotlin
 * object - unless you import kotlinx-html-jvm, but a simple HTML() constructor is
 * afaict not existent.
 * So I use my own html object for now - another option to look at could be jsoup.
 */

@HtmlTagMarker
interface HtmlElement {
    val name:String
    fun render(builder:StringBuilder, indent: String)
}

class TextElement(override val name:String = "", val text: String): HtmlElement {
    override fun render(builder: StringBuilder, indent: String) {
        builder.append("$indent$text\n")
    }
}

@HtmlTagMarker
abstract class Tag(override val name: String) : HtmlElement {

    private val _children: MutableList<HtmlElement> = mutableListOf()
    val children: List<HtmlElement> = _children

    private val _attributes : MutableMap<String, String> = hashMapOf()
    val attributes: Map<String, String> = _attributes

    fun <T: Tag>initTag(tag: T, init:T.()->Unit): T {
        tag.init()
        _children.add(tag)
        return tag
    }

    fun addChild(t:HtmlElement) {
        _children.add(t)
    }

    override fun render(builder: StringBuilder, indent: String) {
        _children.forEach {
            it.render(builder, indent + " ")
        }
    }

    override fun toString(): String {
        val builder = StringBuilder()
        render(builder, "toString: ")
        return builder.toString()
    }


}

abstract class TagWithText(override val name: String) : Tag(name) {
    operator fun String.unaryPlus() {
        addChild(TextElement(text = this))
    }
}

data class Html(override var name:String = "html", var title:String = "") : TagWithText("html") {

    fun head(init: Head.()->Unit):Head = initTag(Head(), init)

    fun body(init: Body.() -> Unit):Body = initTag(Body(), init)

}

data class Head(override var name:String = "head", var title:String = "") : TagWithText(name)

data class Title(override var name:String = "title", val text:String = "") : TagWithText(name)

data class Body(override var name:String = "body", var text:String = "") : TagWithText(name)

fun html(init:Html.()->Unit):Html =
    Html().apply(init)

fun title(init:Title.()->Unit):Title = Title().apply(init)




