package com.example.data.model

enum class AppLanguage(val code: String, val label: String, val flag: String) {
    GERMAN("de", "Deutsch", "🇩🇪"),
    TURKISH("tr", "Türkçe", "🇹🇷"),
    ENGLISH("en", "English", "🇬🇧")
}

data class Kiez(
    val id: String,
    val name: String,
    val subtitle: String,
    val postalCodes: String,
    val population: String,
    val specialty: String,
    val description: String,
    val tags: List<String>,
    val lat: Double,
    val lng: Double,
    val highlights: List<String>,
    val imageUrl: String,
    val impressionImages: List<String> = emptyList(),
    val rentIndex: String = "15,50 €/m²",
    val vibe: String = "Urban & Lebendig",
    val transitLines: String = "",
    val parks: List<String> = emptyList(),
    val popularSpots: List<String> = emptyList(),
    val buergeramtName: String = "",
    val buergeramtAddress: String = "",
    val policeStation: String = "",
    val policeAddress: String = "",
    val policePhone: String = "+49 30 4664 4664"
)

data class KiezDistrict(
    val id: String,
    val name: String,
    val lat: Double,
    val lng: Double
)

data class FoodSpot(
    val id: String,
    val name: String,
    val cuisine: String,
    val rating: Double,
    val reviewsCount: Int,
    val kiezName: String,
    val distance: String,
    val address: String,
    val priceRange: String,
    val imageUrl: String,
    val isSponsored: Boolean = false,
    val phone: String = "+49 30 69532940",
    val website: String = "https://mykiezberlin.com/gastronomie",
    val openingHours: String = "Mo-So: 11:00 - 23:00 Uhr",
    val popularDishes: List<String> = emptyList(),
    val whatsapp: String = "+4915126509586"
)

data class KiezEvent(
    val id: String,
    val title: String,
    val dayLabel: String,
    val dateNum: String,
    val monthLabel: String,
    val time: String,
    val kiezName: String,
    val price: String,
    val isFree: Boolean,
    val category: String,
    val description: String,
    val imageUrl: String,
    val locationAddress: String = "Berlin",
    val organizer: String = "Kiez Community"
)

data class LocalBusiness(
    val id: String,
    val name: String,
    val category: String,
    val rating: Double,
    val reviewsCount: Int,
    val kiezName: String,
    val distance: String,
    val address: String,
    val imageUrl: String,
    val isSponsored: Boolean = false,
    val phone: String = "+49 30 61284455",
    val whatsapp: String = "+4915126509586",
    val website: String = "https://mykiezberlin.com/unternehmen",
    val instagram: String = "",
    val openingHours: String = "Mo-Sa: 09:00 - 19:00 Uhr",
    val galleryImages: List<String> = emptyList(),
    val description: String = "Ihr lokaler Kiez-Partner für erstklassige Qualität und persönlichen Service direkt vor Ort.",
    val hasCoupon: Boolean = false,
    val couponText: String = "",
    val lat: Double = 52.4986,
    val lng: Double = 13.4030
)

data class Deal(
    val id: String,
    val businessName: String,
    val discountBadge: String,
    val title: String,
    val validUntil: String,
    val category: String,
    val code: String,
    val imageUrl: String,
    val isRedeemed: Boolean = false,
    val whatsapp: String = "+4915126509586",
    val address: String = "Berlin"
)

data class KiezStory(
    val id: String,
    val headline: String,
    val subtext: String,
    val kind: String, // "news", "traffic", "event", "warn", "info", "politics"
    val headlineColor: String, // "blau", "weiss", "rot", "gelb"
    val kiez: String,
    val author: String,
    val timeAgo: String,
    val source: String = "",
    val link: String = "https://mykiezberlin.com",
    val whatsapp: String = "+4915126509586",
    val mediaResId: Int? = null,
    val mediaUri: String? = null,
    val isVideo: Boolean = false,
    val isCommercial: Boolean = false,
    val durationHours: Int = 24, // 24, 48, 168 (7 Tage), 720 (30 Tage)
    val pricePaid: Double = 0.0,
    val status: String = "approved", // "pending", "approved", "rejected", "expired"
    val modNote: String = "OK",
    val isSponsored: Boolean = false,
    val views: Int = 1,
    val createdAtMillis: Long = System.currentTimeMillis()
)

data class KiezJob(
    val id: String,
    val title: String,
    val company: String,
    val kiez: String,
    val jobType: String, // "Vollzeit", "Teilzeit", "Minijob", "Aushilfe"
    val salary: String,
    val description: String,
    val contactPhone: String = "",
    val contactWa: String = "",
    val contactEmail: String = "",
    val isUrgent: Boolean = false
)

data class KiezCoupon(
    val id: String,
    val title: String,
    val businessName: String,
    val kiez: String,
    val discount: String,
    val code: String,
    val description: String,
    val validUntil: String,
    val whatsapp: String = "+4915126509586",
    val address: String = "Berlin"
)

data class KiezReporterPost(
    val id: String,
    val title: String,
    val description: String,
    val kiez: String,
    val category: String, // "Verkehr", "Baustelle", "Neueröffnung", "Kultur", "Fundbüro"
    val author: String,
    val timeAgo: String,
    val upvotes: Int = 1
)

data class EmergencyContact(
    val id: String,
    val nameDe: String,
    val nameTr: String,
    val nameEn: String,
    val descDe: String,
    val descTr: String,
    val descEn: String,
    val number: String,
    val category: String, // "notruf", "krise", "gesundheit", "soziales", "tiere"
    val isFree: Boolean = true
)

data class SponsorBanner(
    val id: String,
    val name: String,
    val drawableRes: Int,
    val headlineDe: String,
    val headlineTr: String,
    val headlineEn: String,
    val ctaDe: String,
    val ctaTr: String,
    val ctaEn: String,
    val linkWeb: String,
    val linkWa: String,
    val linkTel: String,
    val category: String,
    val priority: Int = 5
)

data class QuizQuestion(
    val id: Int,
    val question: String,
    val imageUrl: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String
)

data class KiezArticle(
    val id: String,
    val title: String,
    val subtitle: String,
    val category: String,
    val readTimeMinutes: Int,
    val fullContent: String,
    val imageUrl: String
)

data class PricingPackage(
    val id: String,
    val name: String,
    val priceMonthly: String,
    val priceYearlyMonthly: String,
    val isPopular: Boolean = false,
    val isMaxVisibility: Boolean = false,
    val features: List<String>
)

data class AddOnOption(
    val id: String,
    val title: String,
    val pricePerWeek: String,
    val priceNum: Double,
    val description: String
)
