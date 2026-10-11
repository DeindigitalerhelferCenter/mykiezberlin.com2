package com.example.ui.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AppLanguage
import com.example.ui.theme.BerlinRed
import com.example.ui.theme.BerlinRedLight

data class TransitLineInfo(
    val code: String,
    val name: String,
    val type: String, // "U-Bahn", "S-Bahn", "Tram"
    val color: Color,
    val terminus: String,
    val majorStations: List<String>
)

@Composable
fun BerlinTransitMapComponent(
    language: AppLanguage,
    initialSelectedLine: String? = null,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    val transitLines = remember {
        listOf(
            TransitLineInfo("Ringbahn S41/S42", "S-Bahn Ring", "S-Bahn", Color(0xFF782522), "Ringbahn (Vollkreis)", listOf("Ostkreuz", "Südkreuz", "Westkreuz", "Gesundbrunnen", "Treptower Park")),
            TransitLineInfo("U1", "U-Bahn U1", "U-Bahn", Color(0xFF65AC38), "Uhlandstraße ↔ Warschauer Straße", listOf("Kottbusser Tor", "Hallesches Tor", "Möckernbrücke", "Wittenbergplatz")),
            TransitLineInfo("U2", "U-Bahn U2", "U-Bahn", Color(0xFFE31B23), "Pankow ↔ Ruhleben", listOf("Pankow", "Alexanderplatz", "Stadtmitte", "Potsdamer Platz", "Zoologischer Garten")),
            TransitLineInfo("U3", "U-Bahn U3", "U-Bahn", Color(0xFF009C77), "Warschauer Straße ↔ Krumme Lanke", listOf("Warschauer Str.", "Kottbusser Tor", "Wittenbergplatz", "Fehrbelliner Platz", "Krumme Lanke")),
            TransitLineInfo("U5", "U-Bahn U5", "U-Bahn", Color(0xFF7E5330), "Hauptbahnhof ↔ Hönow", listOf("Hauptbahnhof", "Brandenburger Tor", "Unter den Linden", "Alexanderplatz", "Frankfurter Allee")),
            TransitLineInfo("U6", "U-Bahn U6", "U-Bahn", Color(0xFF6B4B9A), "Alt-Tegel ↔ Alt-Mariendorf", listOf("Wedding", "Friedrichstraße", "Stadtmitte", "Mehringdamm", "Tempelhof")),
            TransitLineInfo("U7", "U-Bahn U7", "U-Bahn", Color(0xFF009ED7), "Rathaus Spandau ↔ Rudow", listOf("Rathaus Spandau", "Bismarckstraße", "Fehrbelliner Platz", "Yorckstraße", "Hermannplatz", "Neukölln")),
            TransitLineInfo("U8", "U-Bahn U8", "U-Bahn", Color(0xFF135A9C), "Wittenau ↔ Hermannstraße", listOf("Osloer Straße", "Gesundbrunnen", "Rosenthaler Platz", "Alexanderplatz", "Kottbusser Tor", "Hermannplatz")),
            TransitLineInfo("U9", "U-Bahn U9", "U-Bahn", Color(0xFFF37920), "Osloer Straße ↔ Rathaus Steglitz", listOf("Osloer Straße", "Leopoldplatz", "Turmstraße", "Zoologischer Garten", "Kurfürstendamm", "Rathaus Steglitz")),
            TransitLineInfo("Stadtbahn", "S-Bahn Stadtbahn (S3, S5, S7, S9)", "S-Bahn", Color(0xFF007E33), "Spandau / Potsdam ↔ Erkner / Flughafen BER", listOf("Charlottenburg", "Zoologischer Garten", "Hauptbahnhof", "Friedrichstraße", "Alexanderplatz", "Ostbahnhof", "Ostkreuz")),
            TransitLineInfo("Nord-Süd", "S-Bahn Nord-Süd (S1, S2, S25, S26)", "S-Bahn", Color(0xFF006F45), "Bernau / Hennigsdorf ↔ Wannsee / Teltow", listOf("Gesundbrunnen", "Friedrichstraße", "Potsdamer Platz", "Yorckstraße", "Schöneberg"))
        )
    }

    var selectedLine by remember {
        mutableStateOf(transitLines.find { it.code == initialSelectedLine } ?: transitLines[0])
    }

    var selectedTypeFilter by remember { mutableStateOf("Alle") }

    val filteredLines = remember(selectedTypeFilter) {
        if (selectedTypeFilter == "Alle") transitLines else transitLines.filter { it.type == selectedTypeFilter }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Quick Action Bar: BVG & Google Maps External links
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = MaterialTheme.colorScheme.surfaceVariant
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.DirectionsSubway,
                        contentDescription = null,
                        tint = BerlinRed,
                        modifier = Modifier.size(18.dp)
                    )
                    Text(
                        text = if (language == AppLanguage.TURKISH) "Berlin U- & S-Bahn Ağı" else "Berlin Schnellbahnnetz",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp
                    )
                }

                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    // Google Maps Transit Button
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Color(0xFF34A853),
                        modifier = Modifier.clickable {
                            val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/@52.5170,13.3888,12z/data=!5m1!1e2"))
                            context.startActivity(mapIntent)
                        }
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(imageVector = Icons.Default.Map, contentDescription = null, tint = Color.White, modifier = Modifier.size(12.dp))
                            Text(text = "Google Maps", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.White)
                        }
                    }

                    // BVG Fahrinfo Button
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = Color(0xFFF3C400),
                        modifier = Modifier.clickable {
                            val bvgIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.bvg.de/de/verbindungen/fahrplanauskunft"))
                            context.startActivity(bvgIntent)
                        }
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(imageVector = Icons.Default.DirectionsTransit, contentDescription = null, tint = Color.Black, modifier = Modifier.size(12.dp))
                            Text(text = "BVG Fahrinfo", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.Black)
                        }
                    }
                }
            }
        }

        // Filter Pills: [Tümü | U-Bahn | S-Bahn]
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            listOf("Alle", "U-Bahn", "S-Bahn").forEach { filterType ->
                val isSelected = selectedTypeFilter == filterType
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = if (isSelected) BerlinRed else MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier.clickable { selectedTypeFilter = filterType }
                ) {
                    Text(
                        text = when {
                            language == AppLanguage.TURKISH && filterType == "Alle" -> "Tüm Hatlar"
                            else -> filterType
                        },
                        fontSize = 11.sp,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                    )
                }
            }
        }

        // Horizontal Line Selector Badges
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState())
                .padding(horizontal = 14.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            filteredLines.forEach { line ->
                val isSelected = selectedLine.code == line.code
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = if (isSelected) line.color else line.color.copy(alpha = 0.15f),
                    modifier = Modifier.clickable { selectedLine = line }
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Surface(
                            shape = CircleShape,
                            color = if (isSelected) Color.White else line.color,
                            modifier = Modifier.size(8.dp)
                        ) {}
                        Text(
                            text = line.code,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isSelected) Color.White else MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            }
        }

        // Interactive Berlin Liniennetz Canvas
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(210.dp)
                .padding(horizontal = 14.dp, vertical = 6.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFF1E242B))
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val w = size.width
                val h = size.height

                // Spree river diagram
                val spree = Path().apply {
                    moveTo(0f, h * 0.55f)
                    cubicTo(w * 0.25f, h * 0.45f, w * 0.55f, h * 0.65f, w, h * 0.50f)
                }
                drawPath(spree, Color(0xFF2B4764), style = Stroke(width = 16f))

                // S-Bahn Ringbahn (Outer loop)
                val ring = Path().apply {
                    addOval(androidx.compose.ui.geometry.Rect(w * 0.16f, h * 0.15f, w * 0.84f, h * 0.85f))
                }
                drawPath(ring, Color(0xFF782522), style = Stroke(width = 12f))

                // Stadtbahn (East-West diagonal)
                drawLine(
                    color = Color(0xFF007E33),
                    start = Offset(0f, h * 0.35f),
                    end = Offset(w, h * 0.65f),
                    strokeWidth = 10f
                )

                // Nord-Süd-Bahn (North-South vertical)
                drawLine(
                    color = Color(0xFF006F45),
                    start = Offset(w * 0.50f, 0f),
                    end = Offset(w * 0.50f, h),
                    strokeWidth = 10f
                )

                // U2 (Red line)
                drawLine(
                    color = Color(0xFFE31B23),
                    start = Offset(w * 0.70f, 0f),
                    end = Offset(w * 0.25f, h),
                    strokeWidth = 8f
                )

                // U7 (Light blue line)
                drawLine(
                    color = Color(0xFF009ED7),
                    start = Offset(0f, h * 0.20f),
                    end = Offset(w, h * 0.80f),
                    strokeWidth = 8f
                )

                // U8 (Blue line)
                drawLine(
                    color = Color(0xFF135A9C),
                    start = Offset(w * 0.60f, 0f),
                    end = Offset(w * 0.60f, h),
                    strokeWidth = 8f
                )

                // U9 (Orange line)
                drawLine(
                    color = Color(0xFFF37920),
                    start = Offset(w * 0.35f, 0f),
                    end = Offset(w * 0.35f, h),
                    strokeWidth = 8f
                )

                // Hub nodes (Interchanges)
                val hubs = listOf(
                    Offset(w * 0.50f, h * 0.50f), // Friedrichstraße
                    Offset(w * 0.62f, h * 0.54f), // Alexanderplatz
                    Offset(w * 0.38f, h * 0.47f), // Hauptbahnhof
                    Offset(w * 0.28f, h * 0.44f), // Zoologischer Garten
                    Offset(w * 0.84f, h * 0.50f), // Ostkreuz
                    Offset(w * 0.16f, h * 0.50f), // Westkreuz
                    Offset(w * 0.50f, h * 0.15f), // Gesundbrunnen
                    Offset(w * 0.50f, h * 0.85f)  // Südkreuz
                )

                hubs.forEach { hub ->
                    drawCircle(Color.White, radius = 7f, center = hub)
                    drawCircle(Color.Black, radius = 4f, center = hub)
                }
            }

            // Overlay label
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = Color.Black.copy(alpha = 0.7f),
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Surface(shape = CircleShape, color = selectedLine.color, modifier = Modifier.size(10.dp)) {}
                    Text(
                        text = "${selectedLine.code} · ${selectedLine.terminus}",
                        color = Color.White,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // Selected Line Detail Card
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 6.dp),
            shape = RoundedCornerShape(14.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = selectedLine.color,
                            modifier = Modifier.padding(bottom = 2.dp)
                        ) {
                            Text(
                                text = selectedLine.code,
                                color = Color.White,
                                fontWeight = FontWeight.Black,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                            )
                        }
                        Text(
                            text = selectedLine.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp
                        )
                    }

                    Text(
                        text = selectedLine.type,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Güzergâh: ${selectedLine.terminus}",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = if (language == AppLanguage.TURKISH) "Önemli Aktarma İstasyonları:" else "Wichtige Umsteigebahnhöfe:",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .horizontalScroll(rememberScrollState()),
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    selectedLine.majorStations.forEach { station ->
                        Surface(
                            shape = RoundedCornerShape(6.dp),
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            modifier = Modifier.clickable {
                                val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=S+U+$station+Berlin"))
                                context.startActivity(Intent.createChooser(mapIntent, "Station öffnen"))
                            }
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(imageVector = Icons.Default.Place, contentDescription = null, tint = selectedLine.color, modifier = Modifier.size(11.dp))
                                Text(text = station, fontSize = 11.sp, fontWeight = FontWeight.Medium)
                            }
                        }
                    }
                }
            }
        }
    }
}
