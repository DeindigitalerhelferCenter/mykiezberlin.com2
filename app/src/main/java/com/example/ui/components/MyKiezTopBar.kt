package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AppLanguage
import com.example.ui.theme.BerlinRed
import com.example.ui.theme.BerlinRedLight
import com.example.ui.viewmodel.AppScreen

@Composable
fun MyKiezTopBar(
    currentLanguage: AppLanguage,
    onLanguageChange: (AppLanguage) -> Unit,
    onNavigate: (AppScreen) -> Unit,
    onOpenHamburgerMenu: () -> Unit = {},
    showSearchBox: Boolean = true,
    searchQuery: String = "",
    onSearchChange: (String) -> Unit = {}
) {
    var showLangMenu by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Main Logo
            MyKiezLogoView(
                height = 38,
                onClick = { onNavigate(AppScreen.HOME) }
            )

            // Right Actions: B2B Business Link, Language Selector, Notifications
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // Sponsoring / B2B Link
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = BerlinRedLight,
                    modifier = Modifier.clickable { onNavigate(AppScreen.BUSINESS_PORTAL) }
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Business,
                            contentDescription = "B2B Unternehmen",
                            tint = BerlinRed,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = if (currentLanguage == AppLanguage.TURKISH) "İşletmeler" else if (currentLanguage == AppLanguage.ENGLISH) "B2B" else "B2B Werben",
                            color = BerlinRed,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp
                        )
                    }
                }

                // Language Flag Selector
                Box {
                    Surface(
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        modifier = Modifier
                            .size(34.dp)
                            .clickable { showLangMenu = true }
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Text(text = currentLanguage.flag, fontSize = 16.sp)
                        }
                    }
                    DropdownMenu(
                        expanded = showLangMenu,
                        onDismissRequest = { showLangMenu = false }
                    ) {
                        AppLanguage.values().forEach { lang ->
                            DropdownMenuItem(
                                text = { Text("${lang.flag} ${lang.label}") },
                                onClick = {
                                    onLanguageChange(lang)
                                    showLangMenu = false
                                }
                            )
                        }
                    }
                }

                // Notification Bell Icon (Zil Bildirim Yeri)
                var showNotificationsDialog by remember { mutableStateOf(false) }
                Box {
                    Surface(
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        modifier = Modifier
                            .size(36.dp)
                            .clickable { showNotificationsDialog = true }
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Default.Notifications,
                                contentDescription = "Benachrichtigungen",
                                tint = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
                    Surface(
                        shape = CircleShape,
                        color = BerlinRed,
                        modifier = Modifier
                            .size(11.dp)
                            .align(Alignment.TopEnd)
                    ) {}
                }

                // Notification Dialog
                if (showNotificationsDialog) {
                    AlertDialog(
                        onDismissRequest = { showNotificationsDialog = false },
                        icon = {
                            Icon(imageVector = Icons.Default.Notifications, contentDescription = null, tint = BerlinRed)
                        },
                        title = {
                            Text(
                                text = if (currentLanguage == AppLanguage.TURKISH) "Kiez Bildirimleri" else "Kiez-Mitteilungen",
                                fontWeight = FontWeight.Bold
                            )
                        },
                        text = {
                            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = BerlinRedLight,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Column(modifier = Modifier.padding(10.dp)) {
                                        Text(
                                            text = if (currentLanguage == AppLanguage.TURKISH) "🎉 Yeni Kiez İndirimi!" else "🎉 Neuer Kiez-Deal!",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 12.sp,
                                            color = BerlinRed
                                        )
                                        Text(
                                            text = if (currentLanguage == AppLanguage.TURKISH)
                                                "Café Anna Blume'de %20 kahvaltı indirimi başladı."
                                            else
                                                "20% Rabatt auf Frühstückssets im Café Anna Blume.",
                                            fontSize = 11.5.sp
                                        )
                                    }
                                }

                                Surface(
                                    shape = RoundedCornerShape(8.dp),
                                    color = MaterialTheme.colorScheme.surfaceVariant,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Column(modifier = Modifier.padding(10.dp)) {
                                        Text(
                                            text = if (currentLanguage == AppLanguage.TURKISH) "📍 Yeni İşletme Eklendi" else "📍 Neuer Betrieb eingetragen",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 12.sp
                                        )
                                        Text(
                                            text = if (currentLanguage == AppLanguage.TURKISH)
                                                "Neukölln Sonnenallee'de yeni Bäckerei & Café açıldı."
                                            else
                                                "Bäckerei Brotzeit hat in Neukölln eröffnet.",
                                            fontSize = 11.5.sp
                                        )
                                    }
                                }
                            }
                        },
                        confirmButton = {
                            TextButton(onClick = { showNotificationsDialog = false }) {
                                Text(if (currentLanguage == AppLanguage.TURKISH) "Tamam" else "Schließen")
                            }
                        }
                    )
                }

                // Hamburger Menu Button (Header Sağ Köşe - Menü & Filtreler)
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = BerlinRed,
                    modifier = Modifier
                        .size(36.dp)
                        .clickable { onOpenHamburgerMenu() }
                        .testTag("topbar_hamburger_button")
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Hamburger Menü & Filtreler",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }

        if (showSearchBox) {
            Spacer(modifier = Modifier.height(6.dp))
            OutlinedTextField(
                value = searchQuery,
                onValueChange = onSearchChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 52.dp),
                placeholder = {
                    Text(
                        text = when (currentLanguage) {
                            AppLanguage.TURKISH -> "Kiez, mekân veya konu ara..."
                            AppLanguage.ENGLISH -> "Search kiez, place or topic..."
                            else -> "Kiez, Ort oder Thema suchen..."
                        },
                        fontSize = 13.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Suche",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(20.dp)
                    )
                },
                shape = RoundedCornerShape(24.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedBorderColor = Color.Transparent,
                    focusedBorderColor = BerlinRed
                ),
                singleLine = true
            )
        }
    }
}
