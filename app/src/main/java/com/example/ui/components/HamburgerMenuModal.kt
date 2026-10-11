package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.data.model.AppLanguage
import com.example.data.repository.SampleData
import com.example.ui.theme.*
import com.example.ui.viewmodel.AppScreen

@Composable
fun HamburgerMenuModal(
    language: AppLanguage,
    itemCount: Int,
    isMapView: Boolean,
    onSelectViewMode: (Boolean) -> Unit,
    activeKiezFilter: String,
    onKiezFilterChange: (String) -> Unit,
    activeCategory: String,
    onCategoryChange: (String) -> Unit,
    onAddBusinessClick: () -> Unit,
    onNavigate: (AppScreen) -> Unit,
    onDismiss: () -> Unit
) {
    val kiezeFilterList = listOf("Alle") + SampleData.allKieze.map { it.name }
    val categoriesFilterList = listOf("Alle") + SampleData.categoriesMap.values.map {
        if (language == AppLanguage.TURKISH) it.second else it.first
    }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.90f)
                .padding(vertical = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(18.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                // 1. HEADER
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = BerlinRed.copy(alpha = 0.12f),
                            modifier = Modifier.size(34.dp)
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = null,
                                    tint = BerlinRed,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                        Column {
                            Text(
                                text = if (language == AppLanguage.TURKISH) "Menü & Filtreler" else "Menü & Filter",
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                            Text(
                                text = if (language == AppLanguage.TURKISH) "$itemCount işletme hazır" else "$itemCount Einträge verfügbar",
                                fontSize = 11.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    IconButton(
                        onClick = onDismiss,
                        modifier = Modifier.size(32.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close",
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                HorizontalDivider(color = MaterialTheme.colorScheme.surfaceVariant)

                // 2. AKSIYONLAR & GÖRÜNÜM MODLARI (Liste / Harita / İşletme Ekle)
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = if (language == AppLanguage.TURKISH) "Aksiyonlar & Görünüm Modu" else "Aktionen & Ansicht",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = BerlinRed
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        // Liste Modu Butonu
                        Button(
                            onClick = {
                                onSelectViewMode(false)
                                onDismiss()
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (!isMapView) BerlinRed else MaterialTheme.colorScheme.surfaceVariant,
                                contentColor = if (!isMapView) Color.White else MaterialTheme.colorScheme.onSurface
                            ),
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(10.dp),
                            contentPadding = PaddingValues(vertical = 10.dp)
                        ) {
                            Icon(imageVector = Icons.Default.List, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = if (language == AppLanguage.TURKISH) "Liste Modu" else "Listen-Modus",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        // Harita Modu Butonu
                        Button(
                            onClick = {
                                onSelectViewMode(true)
                                onDismiss()
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isMapView) BerlinRed else MaterialTheme.colorScheme.surfaceVariant,
                                contentColor = if (isMapView) Color.White else MaterialTheme.colorScheme.onSurface
                            ),
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(10.dp),
                            contentPadding = PaddingValues(vertical = 10.dp)
                        ) {
                            Icon(imageVector = Icons.Default.Map, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = if (language == AppLanguage.TURKISH) "Harita Modu" else "Karten-Modus",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    // İşletme Ekle Butonu
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = BerlinGreen,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onDismiss()
                                onAddBusinessClick()
                            }
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Icon(imageVector = Icons.Default.Add, contentDescription = null, tint = Color.White, modifier = Modifier.size(18.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = if (language == AppLanguage.TURKISH) "Yeni İşletme / Yer Ekle" else "Betrieb kostenlos eintragen",
                                fontWeight = FontWeight.Bold,
                                fontSize = 13.sp,
                                color = Color.White
                            )
                        }
                    }

                    // Aktif Filtre Temizleme Butonu
                    if (activeKiezFilter != "Alle" || activeCategory != "Alle") {
                        OutlinedButton(
                            onClick = {
                                onKiezFilterChange("Alle")
                                onCategoryChange("Alle")
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Icon(imageVector = Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(15.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = if (language == AppLanguage.TURKISH) "Filtreleri Sıfırla (Tümü)" else "Filter zurücksetzen",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }

                HorizontalDivider(color = MaterialTheme.colorScheme.surfaceVariant)

                // 3. UYGULAMA BÖLÜMLERİ / KLASÖRLER (Direkt yönlendirme)
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = if (language == AppLanguage.TURKISH) "Uygulama Bölümleri & Klasörler" else "Kategorien & Bereiche",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = BerlinBlue
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        FolderNavButton(
                            title = if (language == AppLanguage.TURKISH) "Yeme-İçme" else "Gastronomie",
                            icon = Icons.Default.Restaurant,
                            tint = BerlinRed,
                            modifier = Modifier.weight(1f),
                            onClick = {
                                onDismiss()
                                onNavigate(AppScreen.FOOD)
                            }
                        )
                        FolderNavButton(
                            title = if (language == AppLanguage.TURKISH) "İşletmeler" else "Betriebe",
                            icon = Icons.Default.Storefront,
                            tint = BerlinBlue,
                            modifier = Modifier.weight(1f),
                            onClick = {
                                onDismiss()
                                onNavigate(AppScreen.BUSINESSES)
                            }
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        FolderNavButton(
                            title = if (language == AppLanguage.TURKISH) "Kiez Haritası" else "Stadtplan",
                            icon = Icons.Default.Map,
                            tint = BerlinGreen,
                            modifier = Modifier.weight(1f),
                            onClick = {
                                onDismiss()
                                onNavigate(AppScreen.KIEZ_MAP)
                            }
                        )
                        FolderNavButton(
                            title = if (language == AppLanguage.TURKISH) "Etkinlikler" else "Events",
                            icon = Icons.Default.Event,
                            tint = BerlinYellow,
                            modifier = Modifier.weight(1f),
                            onClick = {
                                onDismiss()
                                onNavigate(AppScreen.EVENTS)
                            }
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        FolderNavButton(
                            title = if (language == AppLanguage.TURKISH) "İndirimler" else "Deals",
                            icon = Icons.Default.LocalOffer,
                            tint = BerlinRed,
                            modifier = Modifier.weight(1f),
                            onClick = {
                                onDismiss()
                                onNavigate(AppScreen.DEALS)
                            }
                        )
                        FolderNavButton(
                            title = if (language == AppLanguage.TURKISH) "İş İlanları" else "Jobs",
                            icon = Icons.Default.Work,
                            tint = BerlinBlue,
                            modifier = Modifier.weight(1f),
                            onClick = {
                                onDismiss()
                                onNavigate(AppScreen.JOBS)
                            }
                        )
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        FolderNavButton(
                            title = if (language == AppLanguage.TURKISH) "Acil Yardım" else "Notruf",
                            icon = Icons.Default.Phone,
                            tint = BerlinRed,
                            modifier = Modifier.weight(1f),
                            onClick = {
                                onDismiss()
                                onNavigate(AppScreen.NOTRUF)
                            }
                        )
                        FolderNavButton(
                            title = if (language == AppLanguage.TURKISH) "Kiez-Haber" else "Reporter",
                            icon = Icons.Default.Campaign,
                            tint = Color(0xFF8B5CF6),
                            modifier = Modifier.weight(1f),
                            onClick = {
                                onDismiss()
                                onNavigate(AppScreen.REPORTER)
                            }
                        )
                    }
                }

                HorizontalDivider(color = MaterialTheme.colorScheme.surfaceVariant)

                // 4. BERLİN MAHALLELERİ (Kiez Seçimi)
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(
                        text = if (language == AppLanguage.TURKISH) "Berlin Mahalleleri (Kiez Seçimi)" else "Berliner Kieze",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = BerlinRed
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        kiezeFilterList.forEach { kiez ->
                            val isSelected = (activeKiezFilter == "Alle" && kiez == "Alle") || activeKiezFilter == kiez
                            FilterChip(
                                selected = isSelected,
                                onClick = {
                                    onKiezFilterChange(kiez)
                                    // Yönlendirme ve hızlı uygulama
                                    onNavigate(AppScreen.BUSINESSES)
                                },
                                label = {
                                    Text(
                                        text = if (language == AppLanguage.TURKISH && kiez == "Alle") "Tüm Berlin" else if (kiez == "Alle") "Ganz Berlin" else kiez,
                                        fontSize = 11.5.sp,
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
                }

                HorizontalDivider(color = MaterialTheme.colorScheme.surfaceVariant)

                // 5. KATEGORİLER (Gastronomi & İşletmeler)
                Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(
                        text = if (language == AppLanguage.TURKISH) "Kategoriler (Gastronomi & İşletmeler)" else "Kategorien",
                        fontWeight = FontWeight.Bold,
                        fontSize = 13.sp,
                        color = BerlinBlue
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        categoriesFilterList.forEach { cat ->
                            val isSelected = (activeCategory == "Alle" && cat == "Alle") || activeCategory == cat
                            FilterChip(
                                selected = isSelected,
                                onClick = {
                                    onCategoryChange(cat)
                                    // Yönlendirme ve hızlı uygulama
                                    onNavigate(AppScreen.BUSINESSES)
                                },
                                label = {
                                    Text(
                                        text = if (language == AppLanguage.TURKISH && cat == "Alle") "Hepsi" else if (cat == "Alle") "Alles" else cat,
                                        fontSize = 11.5.sp,
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                                    )
                                },
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = BerlinBlue,
                                    selectedLabelColor = Color.White
                                )
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(4.dp))

                // 6. KAPAT & UYGULA BUTONU
                Button(
                    onClick = onDismiss,
                    colors = ButtonDefaults.buttonColors(containerColor = BerlinRed),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(vertical = 12.dp)
                ) {
                    Text(
                        text = if (language == AppLanguage.TURKISH) "Uygula & Menüyü Kapat" else "Schließen & Anwenden",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Composable
private fun FolderNavButton(
    title: String,
    icon: ImageVector,
    tint: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier.clickable { onClick() }
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 9.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(imageVector = icon, contentDescription = title, tint = tint, modifier = Modifier.size(16.dp))
            Text(
                text = title,
                fontSize = 11.5.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1
            )
        }
    }
}
