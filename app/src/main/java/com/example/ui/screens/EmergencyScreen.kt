package com.example.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.model.AppLanguage
import com.example.data.repository.SampleData
import com.example.ui.theme.BerlinGreen
import com.example.ui.theme.BerlinRed

@Composable
fun EmergencyScreen(
    language: AppLanguage
) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // Top Banner with Full Official My Kiez Berlin Logo (replacing bare 'K' or plain text)
        Surface(
            color = BerlinRed.copy(alpha = 0.08f),
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
            border = androidx.compose.foundation.BorderStroke(1.dp, BerlinRed.copy(alpha = 0.3f))
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                // Official Full My Kiez Berlin Logo (Fernsehturm & Bär)
                Surface(
                    shape = CircleShape,
                    color = Color.White,
                    modifier = Modifier.size(54.dp),
                    shadowElevation = 3.dp
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.mykiez_logo),
                        contentDescription = "My Kiez Berlin Logo",
                        modifier = Modifier.padding(4.dp),
                        contentScale = ContentScale.Fit
                    )
                }

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = if (language == AppLanguage.TURKISH)
                            "Berlin Acil Durum & Polis Rehberi"
                        else
                            "Berlin Notruf & Polizei-Meldungen",
                        fontWeight = FontWeight.Black,
                        fontSize = 15.sp,
                        color = BerlinRed
                    )
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(
                        text = if (language == AppLanguage.TURKISH)
                            "Dokunarak doğrudan arayabilirsiniz. 110 ve 112 ücretsizdir. Orijinal kaynak: mykiezberlin.com"
                        else
                            "Direkt per Fingertipp anrufen. Polizei (110) & Feuerwehr (112) sind kostenlos. Offizieller Service von mykiezberlin.com",
                        fontSize = 11.5.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        LazyColumn(
            contentPadding = PaddingValues(start = 16.dp, end = 16.dp, top = 4.dp, bottom = 90.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(SampleData.emergencyContacts) { contact ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${contact.number}"))
                            context.startActivity(intent)
                        },
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Text(
                                    text = when (language) {
                                        AppLanguage.TURKISH -> contact.nameTr
                                        AppLanguage.ENGLISH -> contact.nameEn
                                        else -> contact.nameDe
                                    },
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp,
                                    color = MaterialTheme.colorScheme.onSurface
                                )
                                if (contact.isFree) {
                                    Surface(
                                        shape = RoundedCornerShape(4.dp),
                                        color = BerlinGreen.copy(alpha = 0.15f)
                                    ) {
                                        Text(
                                            text = if (language == AppLanguage.TURKISH) "Ücretsiz" else "Gebührenfrei",
                                            color = BerlinGreen,
                                            fontSize = 9.sp,
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                        )
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = when (language) {
                                    AppLanguage.TURKISH -> contact.descTr
                                    AppLanguage.ENGLISH -> contact.descEn
                                    else -> contact.descDe
                                },
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                lineHeight = 16.sp
                            )
                        }

                        // Call action button with phone number
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = BerlinRed,
                            modifier = Modifier.padding(start = 10.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Call,
                                    contentDescription = "Anrufen",
                                    tint = Color.White,
                                    modifier = Modifier.size(16.dp)
                                )
                                Text(
                                    text = contact.number,
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 13.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
