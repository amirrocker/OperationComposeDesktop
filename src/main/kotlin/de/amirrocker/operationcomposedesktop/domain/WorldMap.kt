package de.amirrocker.operationcomposedesktop.domain

import de.amirrocker.operationcomposedesktop.ui.mapSquareSize

object WorldMap {
    var width: Int = 0
    var height: Int = 0
    var squareSize: Int = 0

    var blueForce: Force = BlueForce.UNDEFINED
    var opForce: Force = OpForce.UNDEFINED
}

fun WorldMap.initializeWorldMap(init:WorldMap.()->WorldMap):WorldMap = init(this)

fun WorldMap.setupUnits(setup:WorldMap.()->WorldMap):WorldMap = setup(this)

abstract class Force(
    protected open val orderOfBattle: OrderOfBattle
) {
}

data class BlueForce(
    override val orderOfBattle: OrderOfBattle = OrderOfBattle.UNDEFINED
) : Force(orderOfBattle) {
    companion object {
        val UNDEFINED = BlueForce()
    }
}

data class OpForce(
    override val orderOfBattle: OrderOfBattle = OrderOfBattle.UNDEFINED
) : Force(orderOfBattle) {
    companion object {
        val UNDEFINED = OpForce()
    }
}

data class OrderOfBattle(
    val commander: CommandUnit.()->CommandUnit = {CommandUnit.UNDEFINED},
    val soldier: DirectionableSoldier = DirectionableSoldier(),
    val tank: Tank = Tank.UNDEFINED,
    val apc: APC = APC.UNDEFINED
) {
    companion object {
        val UNDEFINED = OrderOfBattle()
    }
}

object ForceAccumulator {

    fun createOpForce( init: ForceAccumulator.() -> OpForce) = init()

    fun createBlueForce(init:ForceAccumulator.() -> BlueForce) = init()

}


fun initWorld() {
    val worldMap = WorldMap.initializeWorldMap {
        width = 2000
        height = 2000
        squareSize = mapSquareSize
        this
    }.setupUnits {
        blueForce = ForceAccumulator.createBlueForce {
            BlueForce(
                OrderOfBattle(
                    commander = {
                        CommandUnit.UNDEFINED
                    }
                )
            )
        }
        this
    }.setupUnits {
        opForce = ForceAccumulator.createOpForce {
            OpForce(
                OrderOfBattle(
                    commander = {
                        CommandUnit.UNDEFINED
                    },
                )
            )
        }
        this
    }
    println("WorldMap $worldMap")
    println("WorldMap ${worldMap.blueForce}")
    println("WorldMap ${worldMap.opForce}")
    println("WorldMap ${worldMap.width}")
    println("WorldMap ${worldMap.height}")
    println("WorldMap ${worldMap.squareSize}")
}



