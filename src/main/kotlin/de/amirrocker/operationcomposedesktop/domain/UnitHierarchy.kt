package de.amirrocker.operationcomposedesktop.domain

interface HQ {

}

interface FightingUnit {
    fun advanceToContact()
}

/**
 * All Units usually have multiple roles or responsibilities. A ReconUnit is also a FightingUnit
 */
interface ReconUnit {

}

/**
 * each command unit has a certain command radius. The command radius is highly dependent on tech. sophistication, so
 * the time period this scenario is supposed to happen should be defined early on, while at the moment tending to a
 * modern time period such as the cold war.
 * Cold war command radius depends on
 *
 * - primary transmitter
 * - secondary transmitter
 * - tertiary transmitter
 * Each HQ can use up to three radio transmitters.
 * Once all three transmitters are destroyed or ECM of OpFor is severely impacting radio transmissions,
 * all HQ's automatically fall back on Runners. Using Runners incurs a time penalty when passing Orders
 * down the ranks.
 *
 * Depending on what type of comms are used, the command radius can differ significantly. Placement of units and
 * surrounding landscape can also incur penalties on comms level and even disrupt signals completely. Scenarios
 * where terrain blocks signals using Runners can sometimes be the preferable way to establish any comms with a
 * forward unit.
 *
 */
interface CommandUnit {
    companion object {
        val UNDEFINED = object : CommandUnit {}
    }
}


