package de.amirrocker.operationcomposedesktop.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp


/**
 * this is the actual map showing the topology and terrain. For now it is simply a grey square as a placeholder.
 * TODO figure an artstyle and what to show
 */
@Composable
fun WorldMap() {

    val mapWidth = numColumns.times(40).dp
    val mapHeight = numRows.times(40).dp

    Box(
        modifier = Modifier
            .clip(RectangleShape)
            .size(mapWidth, mapHeight)
            .background(Color.LightGray)
    ) {}
}


data class WorldMapData(
    val game:Game,
    val color: Color = Color.LightGray // TODO this is only a first sketch! - the map needs much more than just color!
) {}



