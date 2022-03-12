package de.amirrocker.operationcomposedesktop.poet

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.PropertySpec
import com.squareup.kotlinpoet.TypeSpec
import java.io.File
import java.util.logging.FileHandler
import kotlin.reflect.KClass

fun runThePoet() {

    println("run the poet")

    /**
     *
     */
    val file = FileSpec.builder("", "PoetSrcFile")
        .addType(soldierType())
        .build()

    // this was only a poc on kotlinpoet
    file.writeTo(System.out)

}

// what we want is a clean arch. structure based on a microview of CA
// this game context
/*
    data:
        - entities
    domain:
        - usecases
    presentation:
        - ui
 */




// POC
fun soldierType():TypeSpec {

    return TypeSpec.classBuilder("Soldier")
        .addProperty(soldierProperty("name", String::class))
        .addProperty(soldierProperty("health", Int::class))
        .addProperty(soldierProperty("stamina", Int::class))
        .primaryConstructor(soldierConstructor())
        .addFunction(moveFunction())
        .build()
}

private fun soldierProperty(propertyName: String, classType: KClass<*>, isMutable:Boolean = false ): PropertySpec =
    PropertySpec.builder(propertyName, classType)
        .initializer(propertyName)
        .also {spec ->  if(isMutable) spec.mutable() }
        .build()

private fun soldierConstructor():FunSpec =
    FunSpec.constructorBuilder()
        .addParameter("name", String::class)
        .addParameter("health", Int::class)
        .addParameter("stamina", Int::class)
        .addStatement("println(%P)", "constructor \$name")
        .build()


private fun moveFunction():FunSpec  =
    FunSpec.builder("move")
        .addCode("""
            if(health < 10) {
                println("almost dead")
            } else {
                println("still alive")
            }
        """.trimIndent())
        .build()


fun runACleanPoet() {

    println("run the poet")

    /**
     *
     */
    val file = FileSpec.builder("", "PoetSrcFile")
        .addType(soldierType())
        .build()

    // this was only a poc on kotlinpoet
    file.writeTo(System.out)

}
