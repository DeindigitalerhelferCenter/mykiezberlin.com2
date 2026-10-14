package com.example.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.MainActivity
import com.example.R
import com.example.data.model.AppLanguage
import com.example.data.repository.KiezStorageManager

data class BerlinNotificationItem(
    val id: Int,
    val titleDe: String,
    val titleTr: String,
    val bodyDe: String,
    val bodyTr: String,
    val targetScreen: String, // "BUSINESS_PORTAL", "LIVE_BULLETIN", "NOTRUF"
    val timestamp: String = "Gerade eben"
) {
    fun localizedTitle(lang: AppLanguage): String = if (lang == AppLanguage.TURKISH) titleTr else titleDe
    fun localizedBody(lang: AppLanguage): String = if (lang == AppLanguage.TURKISH) bodyTr else bodyDe
}

/**
 * Manages regular periodic notifications (every 2-3 hours) for Berlin live updates
 * and business visibility prompts ("İşletmeniz tüm Berlin'de görünür olsun mu?").
 * Users can disable or enable notifications manually at any time.
 */
object BerlinNotificationManager {

    const val CHANNEL_ID = "berlin_kiez_alerts_channel"
    const val EXTRA_TARGET_SCREEN = "extra_target_screen"

    val ROTATING_NOTIFICATIONS = listOf(
        BerlinNotificationItem(
            id = 101,
            titleDe = "Möchten Sie, dass Ihr Unternehmen in ganz Berlin sichtbar ist? 🚀",
            titleTr = "İşletmeniz tüm Berlin'de görünür olsun mu? 🚀",
            bodyDe = "Tragen Sie Ihr Geschäft jetzt im Berliner Kiez-Guide ein & erreichen Sie tausende neue Kunden in Ihrer Nachbarschaft!",
            bodyTr = "Kiez Rehberinde yerinizi hemen ayırtın! Mahallenizde ve tüm Berlin'de müşterilerinize tek tıkla ulaşın.",
            targetScreen = "BUSINESS_PORTAL"
        ),
        BerlinNotificationItem(
            id = 102,
            titleDe = "Berliner Feuerwehr: Erfolgreicher Rettungseinsatz 🚒",
            titleTr = "Berlin İtfaiyesi: Başarılı Teknik Kurtarma 🚒",
            bodyDe = "Mitte & Charlottenburg: Feuerwehr meldet erfolgreichen technischen Hilfeleistungseinsatz. Lage unter Kontrolle.",
            bodyTr = "Charlottenburg'da teknik kurtarma müdahalesi başarıyla tamamlandı. Detaylar canlı bültende.",
            targetScreen = "LIVE_BULLETIN"
        ),
        BerlinNotificationItem(
            id = 103,
            titleDe = "BVG & S-Bahn: Live-Verkehrsaktualisierung 🚇",
            titleTr = "BVG & S-Bahn: Canlı Sefer Güncellemesi 🚇",
            bodyDe = "U-Bahn, Tram und Ringbahn: Aktuelle Störungsmeldungen und Baustellentakte für das Stadtgebiet online.",
            bodyTr = "Metro, Tramvay ve Ringbahn hatlarında güncel sefer ve bakım duyuruları yayımlandı.",
            targetScreen = "LIVE_BULLETIN"
        ),
        BerlinNotificationItem(
            id = 104,
            titleDe = "Polizei Berlin: Aktuelle Sicherheitsmitteilung 🚔",
            titleTr = "Polizei Berlin: Kiez Asayiş Raporu 🚔",
            bodyDe = "Schwerpunktkontrollen im Kiez und Bürgerhinweise über die offizielle Internetwache verfügbar.",
            bodyTr = "Kreuzberg ve Mitte bölgelerinde güncel denetimler ve Internetwache online ihbar servisi aktif.",
            targetScreen = "LIVE_BULLETIN"
        ),
        BerlinNotificationItem(
            id = 105,
            titleDe = "Bürgeramt Berlin: Neue Online-Terminkontingente 🏛️",
            titleTr = "Bürgeramt Berlin: Yeni Randevu Kontenjanları 🏛️",
            bodyDe = "Kurzfristige Termine für Reisepass, Personalausweis und Wohnsitzanmeldung freigeschaltet.",
            bodyTr = "Kimlik, pasaport ve ikametgâh (Anmeldung) işlemleri için günlük randevular açıldı.",
            targetScreen = "LIVE_BULLETIN"
        ),
        BerlinNotificationItem(
            id = 106,
            titleDe = "Berlin Volksfeste & Kultur: Neues Wochenendprogramm 🎭",
            titleTr = "Berlin Festivaller & Kültür: Yeni Hafta Sonu Takvimi 🎭",
            bodyDe = "Entdecken Sie bezirkliche Volksfeste, Museumssonntag und Open-Air-Events auf Berlin.de.",
            bodyTr = "Berlin.de resmi kültür takviminde yer alan mahalle panayırları ve müze günlerini keşfedin.",
            targetScreen = "LIVE_BULLETIN"
        )
    )

    private var currentIndex = 0

    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Berlin Kiez Live & B2B Alerts"
            val descriptionText = "Benachrichtigungen zu Live-Ticker, Feuerwehr, BVG und Kiez-Unternehmenssichtbarkeit"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    /**
     * Posts an authentic system notification if enabled by user and permissions granted.
     */
    fun postNotification(context: Context, item: BerlinNotificationItem, lang: AppLanguage): Boolean {
        if (!KiezStorageManager.isNotificationsEnabled(context)) {
            return false
        }

        // Check Android 13+ POST_NOTIFICATIONS permission
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    context,
                    android.Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return false
            }
        }

        createNotificationChannel(context)

        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP
            putExtra(EXTRA_TARGET_SCREEN, item.targetScreen)
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            item.id,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(item.localizedTitle(lang))
            .setContentText(item.localizedBody(lang))
            .setStyle(NotificationCompat.BigTextStyle().bigText(item.localizedBody(lang)))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        try {
            NotificationManagerCompat.from(context).notify(item.id, builder.build())
            return true
        } catch (e: SecurityException) {
            return false
        } catch (e: Exception) {
            return false
        }
    }

    /**
     * Cycles to next notification in rotation and triggers it.
     */
    fun triggerNextNotification(context: Context, lang: AppLanguage): BerlinNotificationItem {
        val item = ROTATING_NOTIFICATIONS[currentIndex % ROTATING_NOTIFICATIONS.size]
        currentIndex++
        postNotification(context, item, lang)
        return item
    }
}
