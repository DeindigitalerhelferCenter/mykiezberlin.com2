package com.example.ui.components

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import com.example.R
import com.example.data.model.AppLanguage
import com.example.data.model.KiezStory
import com.example.ui.theme.*
import com.example.util.WatermarkHelper

@Composable
fun KiezStoriesBar(
    stories: List<KiezStory>,
    language: AppLanguage,
    onStoryClick: (KiezStory) -> Unit,
    onAddStoryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Only display approved stories in the public bar
    val visibleStories = stories.filter { it.status == "approved" }

    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 2.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = when (language) {
                    AppLanguage.TURKISH -> "Kiez Hikâyeleri"
                    AppLanguage.ENGLISH -> "Kiez Stories"
                    else -> "Kiez-Stories"
                },
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = when (language) {
                    AppLanguage.TURKISH -> "+ Durum Paylaş"
                    AppLanguage.ENGLISH -> "+ Share Story"
                    else -> "+ Deine Story"
                },
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = BerlinRed,
                modifier = Modifier.clickable { onAddStoryClick() }
            )
        }

        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // First item: Add Your Story button (WhatsApp / Instagram style)
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable { onAddStoryClick() }
                ) {
                    Box(
                        modifier = Modifier
                            .size(62.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .border(2.dp, BerlinRed, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add Story",
                            tint = BerlinRed,
                            modifier = Modifier.size(28.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = if (language == AppLanguage.TURKISH) "Paylaş" else "Deine Story",
                        fontSize = 10.5.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            // Visible approved stories
            items(visibleStories) { story ->
                StoryAvatarItem(
                    story = story,
                    onClick = { onStoryClick(story) }
                )
            }
        }
    }
}

@Composable
fun StoryAvatarItem(
    story: KiezStory,
    onClick: () -> Unit
) {
    val borderColor = when (story.headlineColor.lowercase()) {
        "blau" -> BerlinBlue
        "rot" -> BerlinRed
        "gelb" -> BerlinYellow
        else -> BerlinGreen
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.clickable { onClick() }
    ) {
        Box(
            modifier = Modifier
                .size(62.dp)
                .clip(CircleShape)
                .border(2.5.dp, borderColor, CircleShape)
                .padding(3.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.surfaceVariant),
            contentAlignment = Alignment.Center
        ) {
            if (story.mediaUri != null) {
                AsyncImage(
                    model = story.mediaUri,
                    contentDescription = story.headline,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            } else if (story.mediaResId != null) {
                Image(
                    painter = painterResource(id = story.mediaResId),
                    contentDescription = story.headline,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            } else {
                // Official My Kiez Berlin logo badge instead of bare letters
                Image(
                    painter = painterResource(id = R.drawable.ic_mykiez_logo),
                    contentDescription = "Logo",
                    modifier = Modifier.fillMaxSize().padding(4.dp),
                    contentScale = ContentScale.Fit
                )
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = story.kiez,
            fontSize = 10.5.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1
        )
    }
}

@Composable
fun StoryViewerDialog(
    story: KiezStory,
    language: AppLanguage,
    onClose: () -> Unit
) {
    val context = LocalContext.current
    var progress by remember { mutableFloatStateOf(0f) }
    var showFullTextDialog by remember { mutableStateOf(false) }

    LaunchedEffect(story.id, showFullTextDialog) {
        if (!showFullTextDialog) {
            val totalMs = if (story.isVideo) 15000L else 8000L
            val intervalMs = 50L
            val step = intervalMs.toFloat() / totalMs.toFloat()
            while (progress < 1f && !showFullTextDialog) {
                kotlinx.coroutines.delay(intervalMs)
                progress += step
            }
            if (!showFullTextDialog && progress >= 1f) {
                onClose()
            }
        }
    }

    val bannerColor = when (story.headlineColor.lowercase()) {
        "blau" -> BerlinBlue
        "rot" -> BerlinRed
        "gelb" -> BerlinYellow
        else -> Color(0xFF334155)
    }

    Dialog(
        onDismissRequest = onClose,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
        ) {
            // AUFGABE 1: BILD (groß) - Sauberes Bild im oberen/mittleren Bereich, KEINE störende Text-Überlagerung!
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .systemBarsPadding()
            ) {
                // Top: Progress bar, Profile header, Close Button
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    LinearProgressIndicator(
                        progress = { progress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(3.dp)
                            .clip(RoundedCornerShape(2.dp)),
                        color = Color.White,
                        trackColor = Color.White.copy(alpha = 0.3f),
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Left: Author, Kiez, timeAgo and Video badge
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Column {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = "${story.kiez} · ${story.author}",
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 13.5.sp
                                    )
                                    if (story.isVideo) {
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Surface(
                                            shape = RoundedCornerShape(4.dp),
                                            color = BerlinRed
                                        ) {
                                            Text(
                                                text = "VIDEO (bis 1 Min.)",
                                                color = Color.White,
                                                fontSize = 9.sp,
                                                fontWeight = FontWeight.Bold,
                                                modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                                            )
                                        }
                                    }
                                }
                                Text(
                                    text = "${story.timeAgo} · ${story.durationHours}h",
                                    color = Color.White.copy(alpha = 0.75f),
                                    fontSize = 11.sp
                                )
                            }
                        }

                        // Right: Close Button
                        IconButton(
                            onClick = onClose,
                            modifier = Modifier.size(36.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Close",
                                tint = Color.White,
                                modifier = Modifier.size(22.dp)
                            )
                        }
                    }
                }

                // Bild-Container (groß, sauber, unüberlagert)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f, fill = false)
                        .heightIn(min = 260.dp, max = 460.dp)
                        .background(Color.Black),
                    contentAlignment = Alignment.Center
                ) {
                    if (story.mediaUri != null) {
                        AsyncImage(
                            model = story.mediaUri,
                            contentDescription = story.headline,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Fit
                        )
                    } else if (story.mediaResId != null) {
                        Image(
                            painter = painterResource(id = story.mediaResId),
                            contentDescription = story.headline,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Fit
                        )
                    } else {
                        // Text-Story Background (Ortada logo YOK, temiz ve odaklı metin)
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(Color(0xFF18191A)),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.padding(28.dp)
                            ) {
                                Text(
                                    text = story.headline,
                                    color = Color.White,
                                    fontWeight = FontWeight.Black,
                                    fontSize = 20.sp,
                                    textAlign = TextAlign.Center,
                                    lineHeight = 28.sp
                                )
                            }
                        }
                    }

                    // 5. GÖREV: SAĞ KÖŞEDE HER ZAMAN SABİT RESMİ LOGO DAMGASI (Hem resim hem metin üzerinde sabit)
                    Surface(
                        shape = RoundedCornerShape(bottomStart = 10.dp),
                        color = Color.Black.copy(alpha = 0.72f),
                        modifier = Modifier.align(Alignment.TopEnd)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 5.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.mykiez_logo),
                                contentDescription = "My Kiez Berlin Logo",
                                modifier = Modifier.size(22.dp)
                            )
                            Text(
                                text = "My Kiez Berlin",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp
                            )
                        }
                    }

                    // Watermark Preview Badge (Damga) auf Bild unten rechts
                    Surface(
                        shape = RoundedCornerShape(topStart = 8.dp),
                        color = Color.Black.copy(alpha = 0.75f),
                        modifier = Modifier.align(Alignment.BottomEnd)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.mykiez_logo),
                                contentDescription = null,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "mykiezberlin.com",
                                color = Color.White,
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // 6. GÖREV: YUKARI KAYDIRILMIŞ ERGONOMİK AKSİYON ALANI (Geniş boşluk, tam görünürlük)
                Surface(
                    color = Color(0xFF232528),
                    shape = RoundedCornerShape(18.dp),
                    tonalElevation = 6.dp,
                    shadowElevation = 8.dp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp)
                        .padding(bottom = 32.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 14.dp, vertical = 12.dp)
                    ) {
                        // Metin Başlığı ve Kategori Rozeti
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = bannerColor
                            ) {
                                Text(
                                    text = story.kind.uppercase(),
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 10.sp,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                                )
                            }
                            Text(
                                text = story.headline,
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp,
                                maxLines = 1,
                                modifier = Modifier.weight(1f)
                            )
                        }

                        if (story.subtext.isNotBlank()) {
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = story.subtext,
                                color = Color(0xFFE2E8F0),
                                fontSize = 12.sp,
                                maxLines = 2,
                                lineHeight = 16.sp
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // 6. & 7. GÖREV: YUKARI TAŞINMIŞ BÜYÜK & BELİRGİN AKSİYON BUTONLARI
                        val hasExternalSource = story.link.isNotBlank() &&
                                story.link != "https://mykiezberlin.com" &&
                                (story.link.startsWith("http://") || story.link.startsWith("https://"))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // [KAYNAĞA GİT / DEVAM OKU] Button
                            Button(
                                onClick = {
                                    if (hasExternalSource) {
                                        try {
                                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(story.link))
                                            context.startActivity(intent)
                                        } catch (_: Exception) {
                                            showFullTextDialog = true
                                        }
                                    } else {
                                        showFullTextDialog = true
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = BerlinRed),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier
                                    .weight(1.3f)
                                    .fillMaxHeight(),
                                contentPadding = PaddingValues(horizontal = 6.dp, vertical = 4.dp)
                            ) {
                                Icon(
                                    imageVector = if (hasExternalSource) Icons.AutoMirrored.Filled.ArrowForward else Icons.Default.MenuBook,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = if (hasExternalSource) {
                                        if (language == AppLanguage.TURKISH) "KAYNAĞA GİT" else "QUELLE ÖFFNEN"
                                    } else {
                                        if (language == AppLanguage.TURKISH) "DEVAM OKU" else "WEITERLESEN"
                                    },
                                    fontWeight = FontWeight.Black,
                                    fontSize = 11.5.sp,
                                    maxLines = 1
                                )
                            }

                            // [DAMGALI İNDİR] Button
                            Button(
                                onClick = {
                                    val bmp = if (story.mediaResId != null) {
                                        BitmapFactory.decodeResource(context.resources, story.mediaResId)
                                    } else if (story.mediaUri != null) {
                                        try {
                                            val uri = Uri.parse(story.mediaUri)
                                            val stream = context.contentResolver.openInputStream(uri)
                                            BitmapFactory.decodeStream(stream)
                                        } catch (_: Exception) { null }
                                    } else {
                                        null
                                    }

                                    if (bmp != null) {
                                        WatermarkHelper.saveWatermarkedImageToGallery(context, bmp, "Story_${story.kiez}")
                                    } else {
                                        val textBmp = WatermarkHelper.createTextStoryBitmap(
                                            context = context,
                                            headline = story.headline,
                                            subtext = story.subtext,
                                            kiez = story.kiez
                                        )
                                        WatermarkHelper.saveWatermarkedImageToGallery(context, textBmp, "Story_${story.kiez}")
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF475569)),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier
                                    .weight(1.1f)
                                    .fillMaxHeight(),
                                contentPadding = PaddingValues(horizontal = 6.dp, vertical = 4.dp)
                            ) {
                                Icon(imageVector = Icons.Default.Download, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(3.dp))
                                Text(
                                    text = if (language == AppLanguage.TURKISH) "İndir" else "Download",
                                    fontSize = 11.5.sp,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1
                                )
                            }

                            // [PAYLAŞ] Button
                            Button(
                                onClick = {
                                    val shareUrl = if (hasExternalSource) story.link else "https://mykiezberlin.com"
                                    val shareIntent = Intent(Intent.ACTION_SEND).apply {
                                        type = "text/plain"
                                        putExtra(
                                            Intent.EXTRA_TEXT,
                                            "My Kiez Berlin (${story.kiez}):\n${story.headline}\n${story.subtext}\n\n👉 $shareUrl"
                                        )
                                    }
                                    context.startActivity(Intent.createChooser(shareIntent, "Story teilen"))
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = BerlinGreen),
                                shape = RoundedCornerShape(12.dp),
                                modifier = Modifier
                                    .weight(1f)
                                    .fillMaxHeight(),
                                contentPadding = PaddingValues(horizontal = 6.dp, vertical = 4.dp)
                            ) {
                                Icon(imageVector = Icons.Default.Share, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(3.dp))
                                Text(
                                    text = if (language == AppLanguage.TURKISH) "Paylaş" else "Teilen",
                                    fontSize = 11.5.sp,
                                    fontWeight = FontWeight.Bold,
                                    maxLines = 1
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    // 7. GÖREV: POPUP - Kaynak yoksa veya tıklandığında direkt resim ve altındaki metin çıkar!
    if (showFullTextDialog) {
        Dialog(onDismissRequest = { showFullTextDialog = false }) {
            Card(
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(10.dp),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            Image(
                                painter = painterResource(id = R.drawable.mykiez_logo),
                                contentDescription = null,
                                modifier = Modifier.size(26.dp)
                            )
                            Text(
                                text = if (language == AppLanguage.TURKISH) "Haber & Hikâye Detayı" else "Story-Details",
                                fontWeight = FontWeight.Bold,
                                fontSize = 15.sp
                            )
                        }
                        IconButton(onClick = { showFullTextDialog = false }, modifier = Modifier.size(28.dp)) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = "Close", modifier = Modifier.size(18.dp))
                        }
                    }

                    // 7. GÖREV: Direkt Resim ve altındaki metin çıkmalı!
                    if (story.mediaUri != null) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.Black)
                        ) {
                            AsyncImage(
                                model = story.mediaUri,
                                contentDescription = story.headline,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    } else if (story.mediaResId != null) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(Color.Black)
                        ) {
                            Image(
                                painter = painterResource(id = story.mediaResId),
                                contentDescription = story.headline,
                                modifier = Modifier.fillMaxSize(),
                                contentScale = ContentScale.Crop
                            )
                        }
                    }

                    Text(
                        text = story.headline,
                        fontWeight = FontWeight.Black,
                        fontSize = 17.sp,
                        lineHeight = 22.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = "📍 ${story.kiez} · Verfasser: ${story.author} · ${story.timeAgo}",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )

                    HorizontalDivider(color = MaterialTheme.colorScheme.surfaceVariant)

                    // Vollständiger Text (Kein Kürzen)
                    Text(
                        text = story.subtext.ifBlank { "Bu hikaye için ek açıklama metni girilmemiştir." },
                        fontSize = 14.sp,
                        lineHeight = 21.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )

                    if (story.source.isNotBlank() || (story.link.isNotBlank() && story.link != "https://mykiezberlin.com")) {
                        Surface(
                            shape = RoundedCornerShape(10.dp),
                            color = BerlinRed.copy(alpha = 0.08f),
                            border = androidx.compose.foundation.BorderStroke(1.dp, BerlinRed.copy(alpha = 0.3f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier.padding(10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    if (story.source.isNotBlank()) {
                                        Text(
                                            text = "Kaynak: ${story.source}",
                                            fontSize = 11.5.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onSurface
                                        )
                                    }
                                    if (story.link.isNotBlank()) {
                                        Text(
                                            text = story.link,
                                            fontSize = 11.sp,
                                            color = BerlinRed,
                                            maxLines = 1
                                        )
                                    }
                                }
                                if (story.link.isNotBlank() && (story.link.startsWith("http://") || story.link.startsWith("https://"))) {
                                    IconButton(
                                        onClick = {
                                            try {
                                                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(story.link))
                                                context.startActivity(intent)
                                            } catch (_: Exception) {}
                                        },
                                        modifier = Modifier.size(32.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                            contentDescription = "Link",
                                            tint = BerlinRed,
                                            modifier = Modifier.size(18.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }

                    Button(
                        onClick = { showFullTextDialog = false },
                        colors = ButtonDefaults.buttonColors(containerColor = BerlinRed),
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(if (language == AppLanguage.TURKISH) "Kapat / Hikâyeye Dön" else "Schließen", color = Color.White)
                    }
                }
            }
        }
    }
}

@Composable
fun StoryCreateDialog(
    language: AppLanguage,
    onClose: () -> Unit,
    onSubmit: (
        headline: String,
        subtext: String,
        kiez: String,
        kind: String,
        color: String,
        author: String,
        mediaUri: String?,
        isVideo: Boolean,
        isCommercial: Boolean,
        durationHours: Int,
        pricePaid: Double
    ) -> Unit
) {
    var headline by remember { mutableStateOf("") }
    var subtext by remember { mutableStateOf("") }
    var selectedKiez by remember { mutableStateOf("Kreuzberg") }
    var selectedKind by remember { mutableStateOf("news") }
    var selectedColor by remember { mutableStateOf("blau") }
    var author by remember { mutableStateOf("") }
    var selectedDurationHours by remember { mutableIntStateOf(24) }
    var isCommercial by remember { mutableStateOf(false) }
    var rulesAccepted by remember { mutableStateOf(false) }

    var selectedMediaUri by remember { mutableStateOf<String?>(null) }
    var isVideoMedia by remember { mutableStateOf(false) }
    var durationMenuExpanded by remember { mutableStateOf(false) }

    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            selectedMediaUri = uri.toString()
            isVideoMedia = false
        }
    }

    val videoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            selectedMediaUri = uri.toString()
            isVideoMedia = true
        }
    }

    val priceToPay = when (selectedDurationHours) {
        168 -> 5.00
        720 -> 9.90
        else -> 0.00
    }

    Dialog(onDismissRequest = onClose) {
        Card(
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (language == AppLanguage.TURKISH) "Durum / Hikâye Paylaş" else "Story / Status teilen",
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp
                    )
                    IconButton(onClick = onClose) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
                    }
                }

                Text(
                    text = if (language == AppLanguage.TURKISH)
                        "Resim veya 1 dk video seç, başlık ve metin ekle. İndirildiğinde My Kiez Berlin logosu damgalanır."
                    else
                        "Foto oder Video wählen, Text verfassen. Beim Download wird das offizielle My Kiez Berlin Logo als Siegel eingefügt.",
                    fontSize = 12.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                // Media picker buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    OutlinedButton(
                        onClick = {
                            photoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                        },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Image, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(if (language == AppLanguage.TURKISH) "Fotoğraf Seç" else "Foto wählen", fontSize = 11.sp)
                    }

                    OutlinedButton(
                        onClick = {
                            videoPickerLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.VideoOnly))
                        },
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Videocam, contentDescription = null, modifier = Modifier.size(16.dp))
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(if (language == AppLanguage.TURKISH) "Video (1 Dk)" else "Video (1 Min)", fontSize = 11.sp)
                    }
                }

                if (selectedMediaUri != null) {
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = if (isVideoMedia) "Seçilen: Video (1 Dk)" else "Seçilen: Fotoğraf / Görsel",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = BerlinGreen
                            )
                            IconButton(onClick = { selectedMediaUri = null }, modifier = Modifier.size(24.dp)) {
                                Icon(imageVector = Icons.Default.Close, contentDescription = "Remove")
                            }
                        }
                    }
                }

                OutlinedTextField(
                    value = headline,
                    onValueChange = { headline = it },
                    label = { Text(if (language == AppLanguage.TURKISH) "Kısa Başlık (Maks 2 Satır)" else "Kurzer Titel (Max 2 Zeilen)") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = subtext,
                    onValueChange = { subtext = it },
                    label = { Text(if (language == AppLanguage.TURKISH) "Detaylı Metin (DEVAM OKU ile okunur)" else "Ausführlicher Text (für DEVAM OKU)") },
                    maxLines = 4,
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = author,
                    onValueChange = { author = it },
                    label = { Text(if (language == AppLanguage.TURKISH) "Adınız / İşletme" else "Name / Betrieb") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )

                // Commercial Toggle with 3 months free
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Checkbox(checked = isCommercial, onCheckedChange = { isCommercial = it })
                    Column {
                        Text(
                            text = if (language == AppLanguage.TURKISH) "Ticari Giriş (İşletme)" else "Gewerblicher Eintrag",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold
                        )
                        if (isCommercial) {
                            Text(
                                text = if (language == AppLanguage.TURKISH) "İlk 3 Ay Ücretsiz! Sonra paket ücreti." else "Erste 3 Monate kostenlos!",
                                fontSize = 10.5.sp,
                                color = BerlinGreen,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                // Duration Selector - Clean Hamburger / Dropdown & Compact Tidy Layout
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = if (language == AppLanguage.TURKISH) "Yayın Süresi:" else "Laufzeit:",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = when (selectedDurationHours) {
                            24 -> if (language == AppLanguage.TURKISH) "24 Saat (Ücretsiz)" else "24 Std. (Kostenlos)"
                            48 -> if (language == AppLanguage.TURKISH) "48 Saat (Ücretsiz)" else "48 Std. (Kostenlos)"
                            168 -> if (language == AppLanguage.TURKISH) "7 Gün (5,00€)" else "7 Tage (5,00€)"
                            else -> if (language == AppLanguage.TURKISH) "30 Gün (9,90€)" else "30 Tage (9,90€)"
                        },
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (selectedDurationHours <= 48) BerlinGreen else BerlinRed
                    )
                }

                // Compact Hamburger Menu Selector (Takes 1 clean row, perfectly structured)
                Box(modifier = Modifier.fillMaxWidth()) {
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
                        border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.3f)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { durationMenuExpanded = true }
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Menu,
                                    contentDescription = "Süre Menüsü",
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier.size(18.dp)
                                )
                                Text(
                                    text = when (selectedDurationHours) {
                                        24 -> if (language == AppLanguage.TURKISH) "24 Saat (0€ Ücretsiz)" else "24 Stunden (Kostenlos 0€)"
                                        48 -> if (language == AppLanguage.TURKISH) "48 Saat (0€ Ücretsiz)" else "48 Stunden (Kostenlos 0€)"
                                        168 -> if (language == AppLanguage.TURKISH) "7 Gün (5,00€ Sponsorlu)" else "7 Tage (5,00€ Gewerblich)"
                                        else -> if (language == AppLanguage.TURKISH) "30 Gün (9,90€ Premium)" else "30 Tage (9,90€ Monatspaket)"
                                    },
                                    fontSize = 12.sp,
                                    fontWeight = FontWeight.Medium
                                )
                            }
                            Icon(
                                imageVector = Icons.Default.ArrowDropDown,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }

                    DropdownMenu(
                        expanded = durationMenuExpanded,
                        onDismissRequest = { durationMenuExpanded = false }
                    ) {
                        listOf(
                            24 to (if (language == AppLanguage.TURKISH) "24 Saat (Ücretsiz · 0€)" else "24 Std. (Kostenlos · 0€)"),
                            48 to (if (language == AppLanguage.TURKISH) "48 Saat (Ücretsiz · 0€)" else "48 Std. (Kostenlos · 0€)"),
                            168 to (if (language == AppLanguage.TURKISH) "7 Gün (5,00€)" else "7 Tage (5,00€)"),
                            720 to (if (language == AppLanguage.TURKISH) "30 Gün (9,90€)" else "30 Tage (9,90€)")
                        ).forEach { (dur, label) ->
                            DropdownMenuItem(
                                text = {
                                    Row(
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Text(label, fontWeight = if (selectedDurationHours == dur) FontWeight.Bold else FontWeight.Normal)
                                        if (selectedDurationHours == dur) {
                                            Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = BerlinGreen, modifier = Modifier.size(16.dp))
                                        }
                                    }
                                },
                                onClick = {
                                    selectedDurationHours = dur
                                    durationMenuExpanded = false
                                }
                            )
                        }
                    }
                }

                // Compact alt alta 2x2 grid (Never wraps weirdly, neat & tidy)
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = if (selectedDurationHours == 24) BerlinGreen.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                            border = androidx.compose.foundation.BorderStroke(1.dp, if (selectedDurationHours == 24) BerlinGreen else Color.Transparent),
                            modifier = Modifier
                                .weight(1f)
                                .clickable { selectedDurationHours = 24 }
                        ) {
                            Text(
                                text = "24s · 0€",
                                fontSize = 11.sp,
                                fontWeight = if (selectedDurationHours == 24) FontWeight.Bold else FontWeight.Normal,
                                color = if (selectedDurationHours == 24) BerlinGreen else MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(vertical = 8.dp),
                                textAlign = TextAlign.Center
                            )
                        }

                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = if (selectedDurationHours == 48) BerlinGreen.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                            border = androidx.compose.foundation.BorderStroke(1.dp, if (selectedDurationHours == 48) BerlinGreen else Color.Transparent),
                            modifier = Modifier
                                .weight(1f)
                                .clickable { selectedDurationHours = 48 }
                        ) {
                            Text(
                                text = "48s · 0€",
                                fontSize = 11.sp,
                                fontWeight = if (selectedDurationHours == 48) FontWeight.Bold else FontWeight.Normal,
                                color = if (selectedDurationHours == 48) BerlinGreen else MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(vertical = 8.dp),
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = if (selectedDurationHours == 168) BerlinRed.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                            border = androidx.compose.foundation.BorderStroke(1.dp, if (selectedDurationHours == 168) BerlinRed else Color.Transparent),
                            modifier = Modifier
                                .weight(1f)
                                .clickable { selectedDurationHours = 168 }
                        ) {
                            Text(
                                text = "7 Gün · 5€",
                                fontSize = 11.sp,
                                fontWeight = if (selectedDurationHours == 168) FontWeight.Bold else FontWeight.Normal,
                                color = if (selectedDurationHours == 168) BerlinRed else MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(vertical = 8.dp),
                                textAlign = TextAlign.Center
                            )
                        }

                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = if (selectedDurationHours == 720) BerlinRed.copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                            border = androidx.compose.foundation.BorderStroke(1.dp, if (selectedDurationHours == 720) BerlinRed else Color.Transparent),
                            modifier = Modifier
                                .weight(1f)
                                .clickable { selectedDurationHours = 720 }
                        ) {
                            Text(
                                text = "30 Gün · 9,90€",
                                fontSize = 11.sp,
                                fontWeight = if (selectedDurationHours == 720) FontWeight.Bold else FontWeight.Normal,
                                color = if (selectedDurationHours == 720) BerlinRed else MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(vertical = 8.dp),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

                // Rules
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Checkbox(checked = rulesAccepted, onCheckedChange = { rulesAccepted = it })
                    Text(
                        text = if (language == AppLanguage.TURKISH)
                            "Politik, küfür ve 18+ içeriklerin yasak olduğunu kabul ediyorum."
                        else
                            "Ich bestätige: Keine Politik, keine Beleidigungen, keine 18+ Inhalte.",
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Button(
                    onClick = {
                        if (headline.isNotBlank() && rulesAccepted) {
                            onSubmit(
                                headline,
                                subtext,
                                selectedKiez,
                                selectedKind,
                                selectedColor,
                                if (author.isBlank()) "Kiez-User" else author,
                                selectedMediaUri,
                                isVideoMedia,
                                isCommercial,
                                selectedDurationHours,
                                priceToPay
                            )
                        }
                    },
                    enabled = headline.isNotBlank() && rulesAccepted,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BerlinRed)
                ) {
                    Text(
                        text = if (language == AppLanguage.TURKISH) "Admin Onayına Gönder" else "Zur Admin-Prüfung senden",
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}
