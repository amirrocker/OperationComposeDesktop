package de.amirrocker.operationcomposedesktop.domain

data class ControlData(
    val turnLeft: TurnAction,
    val turnRight: TurnAction,
    val moveForward: MoveAction,
    val moveBackward: MoveAction
) {}

/* TODO we have no Direction as Entity, yet! Set it up */
interface TurnAction : Legs

interface MoveAction : Legs

//data class TurnLeftAction(
//    val turnValue : Int = 1
//): TurnAction {
////    override fun move(): DistanceTraveled = 0.0
//    override fun move(): DistanceTraveled = 0.0
//    override fun turn(): SidesTurned = turnValue
//}
//
//data class TurnRightAction(
//    val turnValue : Int = 1
//): TurnAction {
//    override fun move(): DistanceTraveled = 0.0
//    override fun turn(): SidesTurned = turnValue
//}
//
//data class MoveForwardAction(
//    val moveValue : Double = 0.0001
//): MoveAction {
//    override fun move(): DistanceTraveled = moveValue
//    override fun turn(): SidesTurned  = 0
//}
//
//data class MoveBackwardAction(
//    val moveValue : Double = 0.0001
//): MoveAction {
//    override fun move(): DistanceTraveled = -moveValue
//    override fun turn(): SidesTurned  = 0
//}
//
//
//val pattern = listOf(
//        ControlData(turnLeft = TurnLeftAction(1), turnRight = TurnRightAction(0), moveForward = MoveForwardAction(0.0), moveBackward = MoveBackwardAction(0.0)) ,
//        ControlData(turnLeft = TurnLeftAction(0), turnRight = TurnRightAction(0), moveForward = MoveForwardAction(0.1), moveBackward = MoveBackwardAction(0.0)) ,
//        ControlData(turnLeft = TurnLeftAction(0), turnRight = TurnRightAction(0), moveForward = MoveForwardAction(0.1), moveBackward = MoveBackwardAction(0.0)) ,
//        ControlData(turnLeft = TurnLeftAction(0), turnRight = TurnRightAction(1), moveForward = MoveForwardAction(0.0), moveBackward = MoveBackwardAction(0.0)) ,
//        ControlData(turnLeft = TurnLeftAction(0), turnRight = TurnRightAction(0), moveForward = MoveForwardAction(0.1), moveBackward = MoveBackwardAction(0.0)) ,
//        ControlData(turnLeft = TurnLeftAction(1), turnRight = TurnRightAction(0), moveForward = MoveForwardAction(0.0), moveBackward = MoveBackwardAction(0.0)) ,
//        ControlData(turnLeft = TurnLeftAction(0), turnRight = TurnRightAction(0), moveForward = MoveForwardAction(0.0), moveBackward = MoveBackwardAction(0.1)) ,
//        ControlData(turnLeft = TurnLeftAction(0), turnRight = TurnRightAction(0), moveForward = MoveForwardAction(0.0), moveBackward = MoveBackwardAction(0.1)) ,
//        ControlData(turnLeft = TurnLeftAction(0), turnRight = TurnRightAction(0), moveForward = MoveForwardAction(0.0), moveBackward = MoveBackwardAction(0.1)) ,
//        ControlData(turnLeft = TurnLeftAction(1), turnRight = TurnRightAction(0), moveForward = MoveForwardAction(0.0), moveBackward = MoveBackwardAction(0.0)) ,
//        ControlData(turnLeft = TurnLeftAction(0), turnRight = TurnRightAction(0), moveForward = MoveForwardAction(0.1), moveBackward = MoveBackwardAction(0.0)) ,
//    )


