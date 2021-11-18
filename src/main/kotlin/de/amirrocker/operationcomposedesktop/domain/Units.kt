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
