package de.amirrocker.operationcomposedesktop.domain

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

const val mockMapSquareSize = 40

class WorldMapTest {

    private val MAP_WIDTH_UNITS = 2000
    private val MAP_HEIGHT_UNITS = 2000


    @Test
    internal fun `test we can create a worldmap`() {

        val worldMap = WorldMap.initializeWorldMap {
            width = MAP_WIDTH_UNITS
            height = MAP_HEIGHT_UNITS
            squareSize = mockMapSquareSize
            numberOfTiles = this.calculateNumberOfTiles()
            this
        }
        requireNotNull(worldMap) { "worldMap must not be null" }
        assertEquals(MAP_WIDTH_UNITS, worldMap.width, "Expect $MAP_WIDTH_UNITS but was ${worldMap.width}")
        assertEquals(MAP_HEIGHT_UNITS, worldMap.height, "Expect $MAP_WIDTH_UNITS but was ${worldMap.width}")
        assertEquals(mockMapSquareSize, worldMap.squareSize, "Expect $mockMapSquareSize but was ${worldMap.squareSize}")
        assertEquals(MAP_WIDTH_UNITS / mockMapSquareSize,
            worldMap.numberOfTiles.first,
            "Expect ${MAP_WIDTH_UNITS / mockMapSquareSize} but was ${worldMap.numberOfTiles.first}")
        assertEquals(MAP_HEIGHT_UNITS / mockMapSquareSize,
            worldMap.numberOfTiles.second,
            "Expect ${MAP_HEIGHT_UNITS / mockMapSquareSize} but was ${worldMap.numberOfTiles.second}")

    }

    @Test
    internal fun `given a worldMap when setupUnits then expect a valid force setup`() {

        val worldMap = WorldMap.initializeWorldMap {
            width = MAP_WIDTH_UNITS
            height = MAP_HEIGHT_UNITS
            squareSize = mockMapSquareSize
            numberOfTiles = this.calculateNumberOfTiles()
            this
        }.setupUnits {
            blueForce = ForceAccumulator.createBlueForce {
                BlueForce(
                    orderOfBattle = OrderOfBattle(
                        commander = {
                            BattalionCommand(
                                name = "First Battalion",
                                commander = Soldier(M4AssaultRifle(), AthleticLegs(), Ranks.PRIVATE),
                                staff = mapOf(
                                    "Sgt" to Soldier(M4AssaultRifle(), AthleticLegs(), Ranks.PRIVATE)
                                )
                            )
                        }
                    )
                )
            }
            this
        }

        requireNotNull(worldMap) { "worldMap must not be null" }
//        assertEquals(MAP_WIDTH_UNITS, worldMap.width, "Expect $MAP_WIDTH_UNITS but was ${worldMap.width}")
//        assertEquals(MAP_HEIGHT_UNITS, worldMap.height, "Expect $MAP_WIDTH_UNITS but was ${worldMap.width}")
//        assertEquals(mockMapSquareSize, worldMap.squareSize, "Expect $mockMapSquareSize but was ${worldMap.squareSize}")
//        assertEquals(MAP_WIDTH_UNITS/ mockMapSquareSize, worldMap.numberOfTiles.first, "Expect ${MAP_WIDTH_UNITS/ mockMapSquareSize} but was ${worldMap.numberOfTiles.first}")
//        assertEquals(MAP_HEIGHT_UNITS/ mockMapSquareSize, worldMap.numberOfTiles.second, "Expect ${MAP_HEIGHT_UNITS/ mockMapSquareSize} but was ${worldMap.numberOfTiles.second}")

    }

    @Test
    internal fun `given commander in order of battle then test for validity`() {

        val orderOfBattle = OrderOfBattle(
            commander = {
                BattalionCommand(
                    name = "First Battalion",
                    commander = Soldier(M4AssaultRifle(), AthleticLegs(), Ranks.PRIVATE),
                    staff = mapOf(
                        "Sgt" to Soldier(M4AssaultRifle(), AthleticLegs(), Ranks.PRIVATE)
                    )
                )
            }
        )
    }

    @Test
    internal fun `given commander when staff then expect valid staff map`() {
        // given
        val commandUnitName = "First Battalion"
        val commandSoldier = Soldier(M4AssaultRifle(), AthleticLegs(), Ranks.COLONEL)
        // when
        val battalionCommand = BattalionCommand(
            name = commandUnitName,
            commander = commandSoldier,
            staff = mapOf(
                "XO" to Soldier(M4AssaultRifle(), AthleticLegs(), Ranks.LIEUTENANT),
                "RadioOperator" to Soldier(M4AssaultRifle(), AthleticLegs(), Ranks.PRIVATE)
            )
        )
        // then ... what ? :)

    }

    @Test
    internal fun `given order of battle when create soldier then expect soldier in order of battle`() {
        // given
        val soldierStub = Soldier(M4AssaultRifle(), ExoSkeletonLegs(), Ranks.PRIVATE)
        // when
        val orderOfBattle = OrderOfBattle(
            soldier = soldierStub
        )
        // then
        assertEquals(soldierStub, orderOfBattle.soldier, "Expect $soldierStub but was ${orderOfBattle.soldier}")
    }
}


// Trashbin

/*

    /*.setupUnits {
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
        println("WorldMap.blueForce ${worldMap.blueForce}")
        println("WorldMap.opForce ${worldMap.opForce}")
        println("WorldMap.width ${worldMap.width}")
        println("WorldMap.height ${worldMap.height}")
        println("WorldMap.squareSize ${worldMap.squareSize}")
        println("WorldMap.numberOfTiles ${worldMap.numberOfTiles}")*/

 */