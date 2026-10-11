package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AppLanguage
import com.example.data.model.FoodSpot
import com.example.data.repository.SampleData
import com.example.ui.theme.*

data class MapPinItem(
    val id: String,
    val title: String,
    val category: String,
    val xRatio: Float,
    val yRatio: Float,
    val color: Color,
    val rating: Double,
    val distance: String
)

@Composable
fun KiezMapComponent(
    kiezName: String,
    language: AppLanguage,
    onSpotClick: (String) -> Unit
) {
    var selectedCategory by remember { mutableStateOf("Alle") }

    val pins = remember {
        listOf(
            MapPinItem("mustafas", "Mustafa's Gemüse Kebab", "Essen & Trinken", 0.28f, 0.45f, BerlinRed, 4.7, "450 m"),
            MapPinItem("markthalle_neun", "Markthalle Neun", "Essen & Trinken", 0.65f, 0.62f, BerlinYellow, 4.6, "850 m"),
            MapPinItem("kiez_friseur", "Kiez Friseur", "Unternehmen", 0.48f, 0.32f, BerlinBlue, 5.0, "300 m"),
            MapPinItem("kiez_fitness", "Kiez Fitness Studio", "Unternehmen", 0.72f, 0.38f, BerlinGreen, 4.6, "550 m"),
            MapPinItem("goerli", "Görlitzer Park", "Sehenswürdigkeiten", 0.58f, 0.75f, Color(0xFF10B981), 4.4, "600 m"),
            MapPinItem("landwehr", "Landwehrkanal Ufer", "Sehenswürdigkeiten", 0.35f, 0.70f, BerlinBlue, 4.8, "500 m")
        )
    }

    var selectedPin by remember { mutableStateOf(pins[1]) }

    val filteredPins = remember(selectedCategory) {
        if (selectedCategory == "Alle") pins else pins.filter { it.category == selectedCategory }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        // Filter Chips Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            val categories = listOf("Alle", "Essen & Trinken", "Unternehmen", "Sehenswürdigkeiten")
            categories.forEach { cat ->
                val isSelected = selectedCategory == cat
                FilterChip(
                    selected = isSelected,
                    onClick = { selectedCategory = cat },
                    label = {
                        Text(
                            text = when {
                                language == AppLanguage.TURKISH && cat == "Alle" -> "Tümü"
                                language == AppLanguage.TURKISH && cat == "Essen & Trinken" -> "Yeme-İçme"
                                language == AppLanguage.TURKISH && cat == "Unternehmen" -> "İşletmeler"
                                language == AppLanguage.TURKISH && cat == "Sehenswürdigkeiten" -> "Görülecek Yerler"
                                else -> cat
                            },
                            fontSize = 12.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                        )
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        selectedContainerColor = BerlinRed,
                        selectedLabelColor = Color.White
                    )
                )
            }
        }

        // Map Canvas Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color(0xFFE8ECEF))
        ) {
            // Draw simulated map background (River Spree/Canal, Parks, Street grid)
            Canvas(modifier = Modifier.fillMaxSize()) {
                val w = size.width
                val h = size.height

                // Parks / Green Zones
                val parkPath = Path().apply {
                    moveTo(w * 0.45f, h * 0.65f)
                    cubicTo(w * 0.52f, h * 0.60f, w * 0.68f, h * 0.62f, w * 0.75f, h * 0.72f)
                    cubicTo(w * 0.78f, h * 0.85f, w * 0.55f, h * 0.90f, w * 0.45f, h * 0.78f)
                    close()
                }
                drawPath(parkPath, Color(0xFFCBEAD1))

                // Landwehrkanal / River Spree (Blue water stream)
                val riverPath = Path().apply {
                    moveTo(0f, h * 0.68f)
                    cubicTo(w * 0.3f, h * 0.72f, w * 0.6f, h * 0.55f, w, h * 0.45f)
                }
                drawPath(riverPath, Color(0xFFA5C9EB), style = Stroke(width = 28f))

                // Street Grids (Light gray streets)
                // Horizontals
                drawLine(Color(0xFFFFFFFF), Offset(0f, h * 0.25f), Offset(w, h * 0.25f), strokeWidth = 14f)
                drawLine(Color(0xFFFFFFFF), Offset(0f, h * 0.48f), Offset(w, h * 0.48f), strokeWidth = 18f)
                drawLine(Color(0xFFFFFFFF), Offset(0f, h * 0.82f), Offset(w, h * 0.82f), strokeWidth = 12f)
                // Verticals / Diagonals
                drawLine(Color(0xFFFFFFFF), Offset(w * 0.22f, 0f), Offset(w * 0.22f, h), strokeWidth = 16f)
                drawLine(Color(0xFFFFFFFF), Offset(w * 0.52f, 0f), Offset(w * 0.52f, h), strokeWidth = 16f)
                drawLine(Color(0xFFFFFFFF), Offset(w * 0.80f, 0f), Offset(w * 0.80f, h), strokeWidth = 14f)
            }

            // Pins positioned on Map
            filteredPins.forEach { pin ->
                val isSelected = selectedPin.id == pin.id
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
                        val posX = maxWidth * pin.xRatio
                        val posY = maxHeight * pin.yRatio

                        Box(
                            modifier = Modifier
                                .offset(x = posX, y = posY)
                                .shadow(if (isSelected) 8.dp else 3.dp, CircleShape)
                                .clip(CircleShape)
                                .background(if (isSelected) BerlinRed else pin.color)
                                .size(if (isSelected) 36.dp else 28.dp)
                                .clickable { selectedPin = pin },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = when (pin.category) {
                                    "Essen & Trinken" -> Icons.Default.Restaurant
                                    "Unternehmen" -> Icons.Default.Storefront
                                    else -> Icons.Default.Park
                                },
                                contentDescription = pin.title,
                                tint = Color.White,
                                modifier = Modifier.size(if (isSelected) 20.dp else 16.dp)
                            )
                        }
                    }
                }
            }

            // Bottom Floating Card for Selected Spot
            Card(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(16.dp)
                    .shadow(8.dp, RoundedCornerShape(16.dp))
                    .clickable { onSpotClick(selectedPin.id) },
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = BerlinRedLight,
                            modifier = Modifier.size(48.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.Place,
                                    contentDescription = null,
                                    tint = BerlinRed,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }

                        Column {
                            Text(
                                text = selectedPin.title,
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text(
                                    text = selectedPin.category,
                                    fontSize = 12.sp,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Text("•", color = Color.Gray, fontSize = 10.sp)
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Icon(
                                        imageVector = Icons.Default.Star,
                                        contentDescription = "Rating",
                                        tint = BerlinYellow,
                                        modifier = Modifier.size(13.dp)
                                    )
                                    Text(
                                        text = "${selectedPin.rating}",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                Text("•", color = Color.Gray, fontSize = 10.sp)
                                Text(
                                    text = selectedPin.distance,
                                    fontSize = 12.sp,
                                    color = BerlinRed,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }

                    IconButton(
                        onClick = { onSpotClick(selectedPin.id) }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowForwardIos,
                            contentDescription = "Details",
                            tint = BerlinRed,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }
        }
    }
}
