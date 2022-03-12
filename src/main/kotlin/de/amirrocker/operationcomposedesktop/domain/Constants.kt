package de.amirrocker.operationcomposedesktop.domain

object Constants {

    /**
     * Note: const values are not allowed inside a class unless
     * they are wrapped inside a object.
     */
    const val WEAPON_DAMAGE_DEFAULT : WeaponDamage = 100
    const val GRENADE_WEAPON_DAMAGE : WeaponDamage = 250
    const val M4_ASSAULT_RIFLE_WEAPON_DAMAGE : WeaponDamage = 175

    const val EXO_SKELETON_LEGS_TRAVELING_DISTANCE: DistanceTraveled = 1.75
    const val REGULAR_LEGS_TRAVELING_DISTANCE : DistanceTraveled = .50

    const val EXO_SKELETON_LEGS_TURNING_DISTANCE: SidesTurned = 3
    const val REGULAR_LEGS_TURNING_DISTANCE : SidesTurned = 1

    const val DEFAULT_LEAP_FROG_DISTANCE = 5.0
    const val DEFAULT_ONE_SIDE_TURN = 1

}