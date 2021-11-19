package de.amirrocker.operationcomposedesktop.domain

import de.amirrocker.operationcomposedesktop.domain.Constants.DEFAULT_LEAP_FROG_DISTANCE
import de.amirrocker.operationcomposedesktop.domain.Constants.EXO_SKELETON_LEGS_TRAVELING_DISTANCE
import de.amirrocker.operationcomposedesktop.domain.Constants.GRENADE_WEAPON_DAMAGE
import de.amirrocker.operationcomposedesktop.domain.Constants.M4_ASSAULT_RIFLE_WEAPON_DAMAGE
import de.amirrocker.operationcomposedesktop.domain.Constants.REGULAR_LEGS_TRAVELING_DISTANCE

/**
 * this should be renamed :)
 * TODO rethink the name
 */
interface Legs {
    fun move():DistanceTraveled
}

interface Wheels : Legs {
    object DEFAULT_MOTOR : Wheels {
        override fun move(): DistanceTraveled = DEFAULT_LEAP_FROG_DISTANCE // the default travel distance for wheeled units
    }
}

interface Weapon {
    fun causeDamage():WeaponDamage
}

/**
 * The weapon classes
 */
class GrenadeLauncher : Weapon {
    override fun causeDamage(): WeaponDamage = GRENADE_WEAPON_DAMAGE
}

class M4AssaultRifle : Weapon {
    override fun causeDamage(): WeaponDamage = M4_ASSAULT_RIFLE_WEAPON_DAMAGE
}

/**
 * the movement classes
 * with future soldiers :)
 */
class ExoSkeletonLegs : Legs {
    override fun move(): DistanceTraveled = EXO_SKELETON_LEGS_TRAVELING_DISTANCE
}

class RegularLegs : Legs {
    override fun move(): DistanceTraveled = REGULAR_LEGS_TRAVELING_DISTANCE
}

class AthleticLegs : Legs {
    override fun move(): DistanceTraveled = REGULAR_LEGS_TRAVELING_DISTANCE * 2
}
