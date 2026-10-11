package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.data.model.AppLanguage
import com.example.data.model.KiezEvent
import com.example.data.repository.SampleData
import com.example.ui.components.KiezPhotoHelper
import com.example.ui.components.RealKiezImage
import com.example.ui.theme.BerlinGreen
import com.example.ui.theme.BerlinGreenLight
import com.example.ui.theme.BerlinRed
import com.example.ui.theme.BerlinRedLight

@Composable
fun EventsScreen(
    language: AppLanguage,
    activeFilter: String,
    onFilterChange: (String) -> Unit,
    favoriteIds: Set<String>,
    onToggleFavorite: (String) -> Unit,
    customEvents: List<KiezEvent>,
    showSubmitDialog: Boolean,
    onOpenSubmit: () -> Unit,
    onCloseSubmit: () -> Unit,
    onSubmitNewEvent: (String, String, String, String, String, String, String) -> Unit,
    submitSuccessMessage: String?
) {
    val context = LocalContext.current
    val filters = listOf("Alle", "Heute", "Dieses Wochenende", "Diesen Monat", "Kostenlos")

    val allEvents: List<KiezEvent> = remember(customEvents, activeFilter) {
        val merged: List<KiezEvent> = customEvents + SampleData.events
        when (activeFilter) {
            "Heute" -> merged.filter { it.dayLabel.equals("HEUTE", ignoreCase = true) }
            "Dieses Wochenende" -> merged.filter { it.dayLabel.contains("SA", ignoreCase = true) || it.dayLabel.contains("SO", ignoreCase = true) }
            "Kostenlos" -> merged.filter { it.isFree }
            else -> merged
        }
    }

    var selectedEvent by remember { mutableStateOf<KiezEvent?>(null) }

    var titleInput by remember { mutableStateOf("") }
    var kiezInput by remember { mutableStateOf("Kreuzberg") }
    var dateInput by remember { mutableStateOf("15.10.2026") }
    var timeInput by remember { mutableStateOf("19:00 Uhr") }
    var priceInput by remember { mutableStateOf("Kostenlos") }
    var categoryInput by remember { mutableStateOf("Kultur & Musik") }
    var descInput by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Date filters & "Event eintragen" Button
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                filters.forEach { filter ->
                    val isSelected = activeFilter == filter
                    FilterChip(
                        selected = isSelected,
                        onClick = { onFilterChange(filter) },
                        label = {
                            Text(
                                text = when {
                                    language == AppLanguage.TURKISH && filter == "Alle" -> "Tümü"
                                    language == AppLanguage.TURKISH && filter == "Heute" -> "Bugün"
                                    language == AppLanguage.TURKISH && filter == "Dieses Wochenende" -> "Bu Hafta Sonu"
                                    language == AppLanguage.TURKISH && filter == "Diesen Monat" -> "Bu Ay"
                                    language == AppLanguage.TURKISH && filter == "Kostenlos" -> "Ücretsiz"
                                    else -> filter
                                },
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                fontSize = 12.sp
                            )
                        },
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = BerlinRed,
                            selectedLabelColor = Color.White
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Action: Event eintragen
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = BerlinRed,
                modifier = Modifier.clickable { onOpenSubmit() }
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Event hinzufügen", tint = Color.White, modifier = Modifier.size(16.dp))
                    Text(
                        text = if (language == AppLanguage.TURKISH) "Ekle" else "+ Event",
                        color = Color.White,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        // Events list
        LazyColumn(
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 80.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(allEvents) { event ->
                val isFav = favoriteIds.contains(event.id)
                EventCard(
                    event = event,
                    isFavorite = isFav,
                    onToggleFavorite = { onToggleFavorite(event.id) },
                    onClick = { selectedEvent = event }
                )
            }
        }
    }

    // Event Detail Dialog
    selectedEvent?.let { event ->
        val isFav = favoriteIds.contains(event.id)
        Dialog(onDismissRequest = { selectedEvent = null }) {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(8.dp),
                modifier = Modifier.fillMaxWidth().wrapContentHeight()
            ) {
                Column {
                    Box(modifier = Modifier.fillMaxWidth().height(160.dp)) {
                        RealKiezImage(
                            drawableId = KiezPhotoHelper.getEventDrawable(event.id),
                            contentDescription = event.title,
                            showBottomGradient = true,
                            modifier = Modifier.fillMaxSize()
                        )
                        IconButton(
                            onClick = { selectedEvent = null },
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(8.dp)
                                .background(Color.Black.copy(alpha = 0.5f), CircleShape)
                                .size(32.dp)
                        ) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = "Close", tint = Color.White, modifier = Modifier.size(18.dp))
                        }
                    }

                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = event.title, fontWeight = FontWeight.Black, fontSize = 18.sp, modifier = Modifier.weight(1f))
                            IconButton(onClick = { onToggleFavorite(event.id) }) {
                                Icon(
                                    imageVector = if (isFav) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                                    contentDescription = "Favorit",
                                    tint = if (isFav) BerlinRed else Color.Gray
                                )
                            }
                        }

                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            Surface(color = BerlinRedLight, shape = RoundedCornerShape(6.dp)) {
                                Text(
                                    text = "${event.dateNum} ${event.monthLabel} · ${event.time}",
                                    color = BerlinRed,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                )
                            }
                            Surface(color = if (event.isFree) BerlinGreenLight else MaterialTheme.colorScheme.surfaceVariant, shape = RoundedCornerShape(6.dp)) {
                                Text(
                                    text = event.price,
                                    color = if (event.isFree) BerlinGreen else MaterialTheme.colorScheme.onSurface,
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                            Icon(imageVector = Icons.Default.Place, contentDescription = null, tint = BerlinRed, modifier = Modifier.size(15.dp))
                            Text(text = "${event.kiezName}, Berlin", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                        }

                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = event.description, fontSize = 13.sp, lineHeight = 19.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Button(
                                onClick = {
                                    Toast.makeText(context, "Event '${event.title}' in Kalender vorgemerkt! 📅", Toast.LENGTH_SHORT).show()
                                },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(10.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = BerlinRed)
                            ) {
                                Icon(imageVector = Icons.Default.Event, contentDescription = null, modifier = Modifier.size(15.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Merken", fontSize = 12.sp)
                            }

                            OutlinedButton(
                                onClick = {
                                    val mapIntent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=${event.kiezName}+Berlin"))
                                    context.startActivity(Intent.createChooser(mapIntent, "Karte öffnen"))
                                },
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(10.dp)
                            ) {
                                Icon(imageVector = Icons.Default.Directions, contentDescription = null, modifier = Modifier.size(15.dp))
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Route", fontSize = 12.sp)
                            }
                        }
                    }
                }
            }
        }
    }

    // Submit Event Dialog
    if (showSubmitDialog) {
        Dialog(onDismissRequest = onCloseSubmit) {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(8.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (language == AppLanguage.TURKISH) "Etkinlik Ekle" else "Kiez-Event eintragen",
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                        IconButton(onClick = onCloseSubmit) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
                        }
                    }

                    if (submitSuccessMessage != null) {
                        Surface(
                            shape = RoundedCornerShape(12.dp),
                            color = BerlinGreenLight,
                            modifier = Modifier.padding(vertical = 12.dp)
                        ) {
                            Text(
                                text = submitSuccessMessage,
                                color = BerlinGreen,
                                fontWeight = FontWeight.Medium,
                                fontSize = 13.sp,
                                modifier = Modifier.padding(12.dp)
                            )
                        }
                    } else {
                        OutlinedTextField(
                            value = titleInput,
                            onValueChange = { titleInput = it },
                            label = { Text("Event Titel") },
                            modifier = Modifier.fillMaxWidth(),
                            singleLine = true
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            OutlinedTextField(
                                value = kiezInput,
                                onValueChange = { kiezInput = it },
                                label = { Text("Kiez") },
                                modifier = Modifier.weight(1f),
                                singleLine = true
                            )
                            OutlinedTextField(
                                value = priceInput,
                                onValueChange = { priceInput = it },
                                label = { Text("Preis") },
                                modifier = Modifier.weight(1f),
                                singleLine = true
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            OutlinedTextField(
                                value = dateInput,
                                onValueChange = { dateInput = it },
                                label = { Text("Datum") },
                                modifier = Modifier.weight(1f),
                                singleLine = true
                            )
                            OutlinedTextField(
                                value = timeInput,
                                onValueChange = { timeInput = it },
                                label = { Text("Uhrzeit") },
                                modifier = Modifier.weight(1f),
                                singleLine = true
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = descInput,
                            onValueChange = { descInput = it },
                            label = { Text("Beschreibung") },
                            modifier = Modifier.fillMaxWidth(),
                            maxLines = 3
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Button(
                            onClick = {
                                if (titleInput.isNotBlank()) {
                                    onSubmitNewEvent(titleInput, kiezInput, dateInput, timeInput, priceInput, categoryInput, descInput)
                                }
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(48.dp),
                            shape = RoundedCornerShape(12.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = BerlinRed),
                            enabled = titleInput.isNotBlank()
                        ) {
                            Text("Event einreichen", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun EventCard(
    event: KiezEvent,
    isFavorite: Boolean,
    onToggleFavorite: () -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column {
            // Real photo header banner
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(115.dp)
            ) {
                RealKiezImage(
                    drawableId = KiezPhotoHelper.getEventDrawable(event.id),
                    contentDescription = event.title,
                    showBottomGradient = true,
                    modifier = Modifier.fillMaxSize()
                )
                Surface(
                    color = BerlinRed,
                    shape = RoundedCornerShape(bottomEnd = 10.dp),
                    modifier = Modifier.align(Alignment.TopStart)
                ) {
                    Text(
                        text = "${event.dateNum} ${event.monthLabel} · ${event.dayLabel}",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }

                Surface(
                    color = if (event.isFree) BerlinGreen else Color.Black.copy(alpha = 0.7f),
                    shape = RoundedCornerShape(6.dp),
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(6.dp)
                ) {
                    Text(
                        text = event.price,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = event.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 15.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Spacer(modifier = Modifier.height(3.dp))

                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        Icon(imageVector = Icons.Default.AccessTime, contentDescription = null, tint = MaterialTheme.colorScheme.onSurfaceVariant, modifier = Modifier.size(12.dp))
                        Text(text = event.time, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Text("•", color = Color.Gray, fontSize = 10.sp)
                        Icon(imageVector = Icons.Default.Place, contentDescription = null, tint = BerlinRed, modifier = Modifier.size(12.dp))
                        Text(text = event.kiezName, fontSize = 11.sp, color = MaterialTheme.colorScheme.onSurfaceVariant)
                    }
                }

                IconButton(
                    onClick = onToggleFavorite,
                    modifier = Modifier.size(36.dp)
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorit",
                        tint = if (isFavorite) BerlinRed else MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}
