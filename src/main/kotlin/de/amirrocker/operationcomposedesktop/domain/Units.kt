package de.amirrocker.operationcomposedesktop.domain

// units

typealias Longitude = Long
typealias Latitude = Long

typealias WeaponDamage = Int
typealias DistanceTraveled = Int
typealias SupportGiven = Int
typealias ResearchDone = Int


sealed interface Unit {
    fun attack(longitude: Longitude, latitude: Latitude)
    fun move(longitude: Longitude, latitude: Latitude)
}

sealed interface ScienceUnit : Unit {
    fun research(): ResearchDone
}

sealed interface SupportUnit : Unit {
    fun support():SupportGiven
}

/**
 * unit types
 */
interface Infantry : Unit

interface Vehicle : Unit


class Soldier(
    private val weapon:Weapon,
    private val legs:Legs
) : Infantry {

    // just to make the point....
    object VALUES {
        const val SOME_VALUE = 99.0
    }

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

// TODO add other gadgets
class Scientist(
    val legs: Legs
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
    val wheels: Wheels = Wheels.DEFAULT_MOTOR
) : Vehicle {

    override fun attack(longitude: Longitude, latitude: Latitude) {
        val damagedCaused = weapon.causeDamage()
        println("APC attacked enemy for damage: $damagedCaused")
    }

    override fun move(longitude: Longitude, latitude: Latitude) {
        val distanceTraveled = wheels.move()
        println("APC moved: $distanceTraveled m")
    }
}

class Tank(
    val weapon: Weapon,
    val wheels: Wheels = Wheels.DEFAULT_MOTOR
) : Vehicle {
    override fun attack(longitude: Longitude, latitude: Latitude) {
        val damagedCaused = weapon.causeDamage()
        println("TANK attacked enemy for damage: $damagedCaused")
    }

    override fun move(longitude: Longitude, latitude: Latitude) {
        val distanceTraveled = wheels.move()
        println("TANK moved: $distanceTraveled m")
    }
}

