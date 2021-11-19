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