package de.amirrocker.operationcomposedesktop.domain

interface Headquarters {
    val buildings:List<Building<*, Unit>>
}

class RegimentalHeadquarter : Headquarters {

    override val buildings: List<Building<*, Unit>> by lazy {
        mutableListOf()
    }

    fun buildBarracks() : Barracks = Barracks().also { building -> (buildings as MutableList).add(building) }

    fun buildVehicleFactory(): VehicleFactory = VehicleFactory().also { factory -> (buildings as MutableList).add(factory) }

}

interface Building<in UnitType, out ProducedUnit> where UnitType : Enum<*>, ProducedUnit : Unit {
    fun build(type:UnitType):ProducedUnit
}

interface ScienceBuilding<in UnitType, out ProducedUnit> where UnitType : Enum<*>, ProducedUnit : ScienceUnit {
    fun build(type:UnitType):ProducedUnit
}


sealed class ResearchCenter : ScienceBuilding<ResearchUnits, Scientist> {
    override fun build(type: ResearchUnits): Scientist =
        when(type) {
            ResearchUnits.Researcher -> buildResearcher()
            ResearchUnits.FieldOperative -> buildFieldOperative()
            ResearchUnits.LaboratoryTech -> buildLaboratoryTech()
        }

    private fun buildResearcher(): Scientist = Scientist(AthleticLegs())
    private fun buildFieldOperative(): Scientist = Scientist(AthleticLegs())
    private fun buildLaboratoryTech(): Scientist = Scientist(AthleticLegs())

}

class Barracks : Building<InfantryUnits, Infantry> {
    override fun build(type: InfantryUnits): Infantry =
        when(type) {
            InfantryUnits.Rangers -> buildRanger()
            InfantryUnits.Grenadiers -> buildGrenadiers()
            InfantryUnits.Medics -> buildMedics()
            InfantryUnits.Robocops -> buildRobocops()
        }

    private fun buildRanger() = Soldier(M4AssaultRifle(), RegularLegs(), Ranks.LIEUTENANT)
    private fun buildGrenadiers() = Soldier(GrenadeLauncher(), AthleticLegs(), Ranks.LIEUTENANT)
    private fun buildMedics() = Soldier(M4AssaultRifle(), ExoSkeletonLegs(), Ranks.LIEUTENANT)
    private fun buildRobocops() = Soldier(GrenadeLauncher(), ExoSkeletonLegs(), Ranks.LIEUTENANT)
}

class VehicleFactory : Building<MechanizedUnits, Vehicle> {

    override fun build(type: MechanizedUnits): Vehicle =
        when(type) {
            MechanizedUnits.APC -> createAPC()
            MechanizedUnits.IFV -> createIFV()
            MechanizedUnits.SPAAG -> createSPAAG()
            MechanizedUnits.TANK -> createTank()
            MechanizedUnits.SUPPLY -> createSupply()
        }

    private fun createAPC(): APC = APC(GrenadeLauncher())

    private fun createIFV(): APC = APC(GrenadeLauncher()) // TODO give IFV a TOW missile

    private fun createSPAAG(): APC = APC(GrenadeLauncher()) // TODO a 4 x Barrel 0.5 Cal :)

    private fun createTank(): Tank = Tank(GrenadeLauncher()) // TODO a 110 mm smoothbore FSAPDS / HEATFS :) :)

    private fun createSupply(): APC = APC(M4AssaultRifle()) // TODO think on how to represent supply and training

}