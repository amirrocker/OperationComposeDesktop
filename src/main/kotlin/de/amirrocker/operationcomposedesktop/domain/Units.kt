package de.amirrocker.operationcomposedesktop.domain

// units

typealias Longitude = Long
typealias Latitude = Long

typealias WeaponDamage = Int
typealias DistanceTraveled = Double

/* we deal with hex tiles so we have six possible directions to turn to */
typealias SidesTurned = Int
typealias SupportGiven = Int
typealias ResearchDone = Int

/**
 * the different Unit types that can be used to represent the units on the map.
 * Unit : the base class for each unit in the game. Each map item that one can interact with is-a Unit.
 *
 * ScienceUnit : each unit that can do any type of science stuff is-a ScienceUnit.
 * (e.g. Engineer-Troops, ABC-Troops, EWO(Electronic Warfare Operators)-Troops)
 *
 * SupportUnit : each unit that has a support-role, such as On- and Off-map Artillery, Medics, Ammo- and Fuel-Bowsers, are
 * support units.
 *
 * note that this is a naive first sketch
 */
sealed interface Unit {
    fun attack(longitude: Longitude, latitude: Latitude)
    fun move(longitude: Longitude, latitude: Latitude)
}

sealed interface ScienceUnit : Unit {
    fun research(): ResearchDone
}

sealed interface SupportUnit : Unit {
    fun support(): SupportGiven
}

/**
 * unit types
 */
interface Infantry : Unit

interface Vehicle : Unit

open class Soldier(
    private val weapon: Weapon,
    private val legs: Legs,
) : Infantry {

    override fun attack(longitude: Longitude, latitude: Latitude) {
        // find suitable target
        // aim
        // shoot
        val damageCaused = weapon.causeDamage()
        println("Soldier attacked enemy for damage: $damageCaused hp")
    }

    override fun move(longitude: Longitude, latitude: Latitude) {
        // compute facing
        // compute direction
        // move at speed
        val distanceTraveled = legs.move()
        println("Soldier moved: $distanceTraveled m")
    }
}


class DirectionableSoldier : Soldier(
    M4AssaultRifle(),
    AthleticLegs()
), Directionable {

    override val orientation: Float
        get() = if (orientation.isNaN()) {
            0.0f
        } else {
            orientation
        }
}

interface Directionable {
    val orientation: Float  // some angle value ?
}

// TODO add other gadgets
class Scientist(
    val legs: Legs,
) : ScienceUnit, Infantry {

    override fun move(longitude: Longitude, latitude: Latitude) {
        val distanceTraveled = legs.move()
        println("Soldier moved: $distanceTraveled m")
    }

    override fun research(): ResearchDone {
        println("no research done - not yet implemented")
        return 0
    }

    override fun attack(longitude: Longitude, latitude: Latitude) {
        println("")
    }
}

/**
 * The Vehicle classes
 */
class APC(
    val weapon: Weapon,
    val wheels: Wheels = Wheels.DEFAULT_MOTOR,
) : Vehicle {

    override fun attack(longitude: Longitude, latitude: Latitude) {
        val damagedCaused = weapon.causeDamage()
        println("APC attacked enemy for damage: $damagedCaused")
    }

    override fun move(longitude: Longitude, latitude: Latitude) {
        val distanceTraveled = wheels.move()
        println("APC moved: $distanceTraveled m")
    }
    companion object {
        val UNDEFINED = APC(NoWeapon())
    }
}

class Tank(
    val weapon: Weapon,
    val wheels: Wheels = Wheels.DEFAULT_MOTOR,
) : Vehicle {
    override fun attack(longitude: Longitude, latitude: Latitude) {
        val damagedCaused = weapon.causeDamage()
        println("TANK attacked enemy for damage: $damagedCaused")
    }

    override fun move(longitude: Longitude, latitude: Latitude) {
        val distanceTraveled = wheels.move()
        println("TANK moved: $distanceTraveled m")
    }
    companion object {
        val UNDEFINED = Tank(NoWeapon())
    }
}

