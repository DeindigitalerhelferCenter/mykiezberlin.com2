package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AppLanguage
import com.example.data.repository.SampleData
import com.example.ui.components.KiezPhotoHelper
import com.example.ui.components.RealKiezImage
import com.example.ui.theme.*

@Composable
fun KiezQuizScreen(
    currentIndex: Int,
    selectedOption: Int?,
    isAnswerChecked: Boolean,
    score: Int,
    isFinished: Boolean,
    language: AppLanguage,
    onSelectOption: (Int) -> Unit,
    onNextQuestion: () -> Unit,
    onRestart: () -> Unit
) {
    val questions = SampleData.quizQuestions
    val currentQuestion = if (currentIndex < questions.size) questions[currentIndex] else questions.first()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(bottom = 80.dp)
    ) {
        // Quiz Header & Progress
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Surface(shape = CircleShape, color = BerlinYellowLight, modifier = Modifier.size(32.dp)) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(imageVector = Icons.Default.EmojiEvents, contentDescription = null, tint = BerlinYellow, modifier = Modifier.size(18.dp))
                    }
                }
                Text(
                    text = if (language == AppLanguage.TURKISH) "Kiez Bilgi Yarışması" else "Kiez Quiz",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }

            Surface(
                shape = RoundedCornerShape(12.dp),
                color = MaterialTheme.colorScheme.surfaceVariant
            ) {
                Text(
                    text = "${currentIndex + 1} / ${questions.size}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp,
                    color = BerlinRed,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                )
            }
        }

        LinearProgressIndicator(
            progress = { (currentIndex + 1).toFloat() / questions.size.toFloat() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            color = BerlinRed,
            trackColor = BerlinRedLight
        )

        Spacer(modifier = Modifier.height(14.dp))

        if (isFinished) {
            // Finished Screen
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.EmojiEvents,
                        contentDescription = null,
                        tint = BerlinYellow,
                        modifier = Modifier.size(64.dp)
                    )
                    Text(
                        text = if (language == AppLanguage.TURKISH) "Tebrikler!" else "Glückwunsch!",
                        fontWeight = FontWeight.Black,
                        fontSize = 24.sp
                    )
                    Text(
                        text = if (language == AppLanguage.TURKISH)
                            "Berlin bilginiz test edildi. Toplam skorunuz: $score / ${questions.size}"
                        else
                            "Du bist ein echter Berlin-Kenner! Dein Ergebnis: $score / ${questions.size}",
                        fontSize = 15.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Button(
                        onClick = onRestart,
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = BerlinRed),
                        modifier = Modifier.fillMaxWidth().height(48.dp)
                    ) {
                        Icon(imageVector = Icons.Default.Refresh, contentDescription = null, tint = Color.White)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (language == AppLanguage.TURKISH) "Yeniden Başlat" else "Erneut spielen",
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        } else {
            // Real Question Landmark Photo Banner
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(175.dp)
                    .padding(horizontal = 16.dp)
                    .clip(RoundedCornerShape(18.dp))
            ) {
                RealKiezImage(
                    drawableId = KiezPhotoHelper.getQuizDrawable(currentQuestion.id),
                    contentDescription = currentQuestion.question,
                    showBottomGradient = true,
                    modifier = Modifier.fillMaxSize()
                )
                Surface(
                    color = BerlinRed,
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(12.dp)
                ) {
                    Text(
                        text = "BERLIN QUIZ · FRAGE ${currentIndex + 1}/${questions.size}",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 11.sp,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Question Text
            Text(
                text = currentQuestion.question,
                fontWeight = FontWeight.Bold,
                fontSize = 17.sp,
                lineHeight = 23.sp,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            Spacer(modifier = Modifier.height(14.dp))

            // Options List
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                currentQuestion.options.forEachIndexed { index, optionText ->
                    val isSelected = selectedOption == index
                    val isCorrect = index == currentQuestion.correctIndex

                    val (containerColor, borderColor, textColor) = when {
                        !isAnswerChecked && isSelected -> Triple(BerlinRedLight, BerlinRed, BerlinRedDark)
                        isAnswerChecked && isCorrect -> Triple(BerlinGreenLight, BerlinGreen, BerlinGreen)
                        isAnswerChecked && isSelected && !isCorrect -> Triple(Color(0xFFFEE2E2), Color(0xFFDC2626), Color(0xFFDC2626))
                        else -> Triple(MaterialTheme.colorScheme.surface, MaterialTheme.colorScheme.outline, MaterialTheme.colorScheme.onSurface)
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable(enabled = !isAnswerChecked) { onSelectOption(index) },
                        shape = RoundedCornerShape(14.dp),
                        colors = CardDefaults.cardColors(containerColor = containerColor),
                        border = androidx.compose.foundation.BorderStroke(1.5.dp, borderColor)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 14.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = optionText,
                                fontWeight = if (isSelected || (isAnswerChecked && isCorrect)) FontWeight.Bold else FontWeight.Medium,
                                fontSize = 15.sp,
                                color = textColor
                            )

                            if (isAnswerChecked) {
                                if (isCorrect) {
                                    Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "Richtig", tint = BerlinGreen)
                                } else if (isSelected) {
                                    Icon(imageVector = Icons.Default.Close, contentDescription = "Falsch", tint = Color(0xFFDC2626))
                                }
                            }
                        }
                    }
                }
            }

            // Explanation & Next Button
            if (isAnswerChecked) {
                Spacer(modifier = Modifier.height(14.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = BerlinGreenLight)
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Text(
                            text = currentQuestion.explanation,
                            color = Color(0xFF14532D),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium,
                            lineHeight = 18.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))
                Button(
                    onClick = onNextQuestion,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .height(50.dp),
                    shape = RoundedCornerShape(14.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = BerlinRed)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                        Text(
                            text = if (currentIndex + 1 < questions.size)
                                (if (language == AppLanguage.TURKISH) "Sonraki Soru" else "Nächste Frage")
                            else
                                (if (language == AppLanguage.TURKISH) "Sonuçları Gör" else "Ergebnis ansehen"),
                            fontWeight = FontWeight.Bold,
                            fontSize = 15.sp,
                            color = Color.White
                        )
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, tint = Color.White)
                    }
                }
            }
        }
    }
}
