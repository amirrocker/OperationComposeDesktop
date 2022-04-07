package de.amirrocker.operationcomposedesktop.domain

import de.amirrocker.operationcomposedesktop.ui.mapSquareSize
import kotlin.math.max

object WorldMap {
    var width: Int = 0
    var height: Int = 0
    var squareSize: Int = 0
    var numberOfTiles: Pair<Int, Int> = Pair(0, 0)

    var blueForce: Force = BlueForce.UNDEFINED
    var opForce: Force = OpForce.UNDEFINED
}

fun WorldMap.initializeWorldMap(init: WorldMap.() -> WorldMap): WorldMap = init(this)

fun WorldMap.setupUnits(setup: WorldMap.() -> WorldMap): WorldMap = setup(this)

fun WorldMap.calculateNumberOfTiles(): Pair<Int, Int> = Pair(
    max(1, width / mapSquareSize),
    max(1, height / mapSquareSize)
)

abstract class Force(
    protected open val orderOfBattle: OrderOfBattle,
)

data class BlueForce(
    override val orderOfBattle: OrderOfBattle = OrderOfBattle.UNDEFINED,
) : Force(orderOfBattle) {
    companion object {
        val UNDEFINED = BlueForce()
    }
}

data class OpForce(
    override val orderOfBattle: OrderOfBattle = OrderOfBattle.UNDEFINED,
) : Force(orderOfBattle) {
    companion object {
        val UNDEFINED = OpForce()
    }
}

data class OrderOfBattle(
    val commander: CommandUnit.() -> CommandUnit = { de.amirrocker.operationcomposedesktop.domain.UNDEFINED },
    val soldier: Soldier = Soldier.UNDEFINED,
    val tank: Tank = Tank.UNDEFINED,
    val apc: APC = APC.UNDEFINED,
) {
    companion object {
        val UNDEFINED = OrderOfBattle()
    }
}

object ForceAccumulator {
    fun createOpForce(init: ForceAccumulator.() -> OpForce) = init()
    fun createBlueForce(init: ForceAccumulator.() -> BlueForce) = init()
}


fun initWorld() {
    val worldMap = WorldMap.initializeWorldMap {
        width = 2000
        height = 2000
        squareSize = mapSquareSize
        numberOfTiles = this.calculateNumberOfTiles()
        this
    }.setupUnits {
        blueForce = ForceAccumulator.createBlueForce {
            BlueForce(
                OrderOfBattle(
                    commander = {
                        UNDEFINED
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
                        UNDEFINED
                    },
                )
            )
        }
        this
    }

    println("WorldMap $worldMap")
    println("WorldMap.blueForce ${worldMap.blueForce}")
    println("WorldMap.opForce ${worldMap.opForce}")
    println("WorldMap.width ${worldMap.width}")
    println("WorldMap.height ${worldMap.height}")
    println("WorldMap.squareSize ${worldMap.squareSize}")
    println("WorldMap.numberOfTiles ${worldMap.numberOfTiles}")

}





