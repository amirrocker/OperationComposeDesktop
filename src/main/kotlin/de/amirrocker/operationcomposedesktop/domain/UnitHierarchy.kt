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
 * Command Structure:
 * The command structure is modeled based on resources such as
 * https://www.vetfriends.com/resources/us_military_structure_chart.cfm
 *
 * What Command Units are there:
 * 1. Battalion Command
 * 2. Company Command
 * 3. Platoon Command
 * 4. Squad Commander - Squad Commander is also a Command Unit but embedded into the Squad. Unlike all other
 * command units, which are separate units on the map, the Squad Commander is integrated into the Squad and
 * no command unit is rendered on the map. But command mechanics will still apply to squads.
 *
 * 1. Battalion Command
 * 300-1000 Soldiers, 4-6 Companies
 * 2. Company Command
 * 60-190 Soldiers, 3-4 Platoons
 * 3. Platoon Command
 * 20-50 Soldiers, 2-4 Squads
 * 4. Squad Commander
 * 1 Command Soldier, 3 Staff Soldiers ( DeputyCommander, XO(Gunny), RadioOperator )
 *
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

typealias RankDescriptor = String

sealed interface CommandUnit {

    val name: String

    val commander: Soldier

    val staff : Map<RankDescriptor, Soldier>

}

object UNDEFINED : CommandUnit {
    override val name: String
        get() = "UNDEFINED"
    override val commander: Soldier
        get() = Soldier.UNDEFINED
    override val staff: Map<RankDescriptor, Soldier>
        get() = emptyMap()
}

data class BattalionCommand(
    override val name: String,
    override val commander: Soldier,
    override val staff : Map<RankDescriptor, Soldier>,
) : CommandUnit

data class CompanyCommand(
    override val name: String,
    override val commander: Soldier,
    override val staff : Map<RankDescriptor, Soldier>,
) : CommandUnit

data class PlatoonCommand(
    override val name: String,
    override val commander: Soldier,
    override val staff : Map<RankDescriptor, Soldier>,
) : CommandUnit

data class SquadCommander(
    override val name: String,
    override val commander: Soldier,
    override val staff : Map<RankDescriptor, Soldier>,
) : CommandUnit







