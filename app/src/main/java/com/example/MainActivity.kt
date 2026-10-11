package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.data.model.AppLanguage
import com.example.ui.components.HamburgerHubModal
import com.example.ui.components.KiezStoriesBar
import com.example.ui.components.MyKiezBottomNav
import com.example.ui.components.MyKiezTopBar
import com.example.ui.components.StoryCreateDialog
import com.example.ui.components.StoryViewerDialog
import com.example.ui.screens.*
import com.example.ui.theme.BerlinBlue
import com.example.ui.theme.BerlinGreen
import com.example.ui.theme.BerlinRed
import com.example.ui.theme.MyApplicationTheme
import com.example.ui.viewmodel.AppScreen
import com.example.ui.viewmodel.MyKiezViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: MyKiezViewModel = viewModel()
            val uiState by viewModel.uiState.collectAsStateWithLifecycle()

            MyApplicationTheme(darkTheme = uiState.isDarkMode) {
                MyKiezMainApp(viewModel)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyKiezMainApp(viewModel: MyKiezViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    var showMoreBottomSheet by remember { mutableStateOf(false) }
    var showHamburgerMenu by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .testTag("my_kiez_scaffold"),
        topBar = {
            if (uiState.currentScreen != AppScreen.KIEZ_DETAIL) {
                MyKiezTopBar(
                    currentLanguage = uiState.language,
                    onLanguageChange = { viewModel.setLanguage(it) },
                    onNavigate = { viewModel.navigateTo(it) },
                    onOpenHamburgerMenu = { showHamburgerMenu = true },
                    showSearchBox = uiState.currentScreen == AppScreen.HOME ||
                            uiState.currentScreen == AppScreen.BUSINESSES ||
                            uiState.currentScreen == AppScreen.FOOD ||
                            uiState.currentScreen == AppScreen.DEALS,
                    searchQuery = uiState.searchQuery,
                    onSearchChange = { viewModel.setSearchQuery(it) }
                )
            }
        },
        bottomBar = {
            MyKiezBottomNav(
                currentScreen = uiState.currentScreen,
                currentLanguage = uiState.language,
                onNavigate = { viewModel.navigateTo(it) },
                onOpenMoreMenu = { showMoreBottomSheet = true }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (uiState.currentScreen) {
                AppScreen.HOME, AppScreen.BUSINESSES -> BusinessesScreen(
                    language = uiState.language,
                    activeCategory = uiState.activeBusinessCategory,
                    onCategoryChange = { viewModel.setBusinessCategory(it) },
                    activeKiezFilter = uiState.activeKiezFilter,
                    onKiezFilterChange = { viewModel.setKiezFilter(it) },
                    selectedBusiness = uiState.selectedBusiness,
                    onSelectBusiness = { viewModel.openBusinessDetail(it) },
                    onCloseDetail = { viewModel.closeBusinessDetail() },
                    favoriteIds = uiState.favoriteIds,
                    onToggleFavorite = { viewModel.toggleFavorite(it) },
                    searchQuery = uiState.searchQuery,
                    customBusinesses = uiState.customBusinesses,
                    onAddBusiness = { viewModel.addCustomBusiness(it) },
                    stories = uiState.customStories + com.example.data.repository.SampleData.stories,
                    onStoryClick = { viewModel.openStory(it) },
                    onAddStoryClick = { viewModel.openAddStoryDialog() },
                    viewMode = uiState.businessViewMode,
                    onViewModeChange = { viewModel.setBusinessViewMode(it) },
                    onResetFilters = { viewModel.resetFilters() },
                    showAddBusinessDialog = uiState.showAddBusinessDialog,
                    onOpenAddBusiness = { viewModel.openAddBusinessDialog() },
                    onCloseAddBusiness = { viewModel.closeAddBusinessDialog() }
                )

                AppScreen.JOBS -> JobsScreen(
                    language = uiState.language,
                    customJobs = uiState.customJobs,
                    onAddJob = { t, c, k, tp, s, w, d -> viewModel.addJob(t, c, k, tp, s, w, d) }
                )

                AppScreen.NOTRUF -> EmergencyScreen(
                    language = uiState.language
                )

                AppScreen.REPORTER -> KiezReporterScreen(
                    language = uiState.language,
                    customPosts = uiState.customReports,
                    onSubmitPost = { t, k, c, d, a -> viewModel.submitReporterPost(t, k, c, d, a) }
                )

                AppScreen.KIEZE -> KiezListScreen(
                    language = uiState.language,
                    onKiezClick = { viewModel.openKiezDetail(it) }
                )

                AppScreen.KIEZ_DETAIL -> KiezDetailScreen(
                    kiezId = uiState.selectedKiezId,
                    language = uiState.language,
                    onBack = { viewModel.navigateTo(AppScreen.KIEZE) },
                    onStartQuiz = { viewModel.navigateTo(AppScreen.QUIZ) },
                    onNavigate = { viewModel.navigateTo(it) },
                    isFavorite = uiState.favoriteIds.contains(uiState.selectedKiezId),
                    onToggleFavorite = { viewModel.toggleFavorite(uiState.selectedKiezId) }
                )

                AppScreen.KIEZ_MAP -> KiezListScreen(
                    language = uiState.language,
                    onKiezClick = { viewModel.openKiezDetail(it) }
                )

                AppScreen.QUIZ -> KiezQuizScreen(
                    currentIndex = uiState.quizCurrentIndex,
                    selectedOption = uiState.quizSelectedOption,
                    isAnswerChecked = uiState.quizIsAnswerChecked,
                    score = uiState.quizScore,
                    isFinished = uiState.quizFinished,
                    language = uiState.language,
                    onSelectOption = { viewModel.answerQuizOption(it) },
                    onNextQuestion = { viewModel.nextQuizQuestion() },
                    onRestart = { viewModel.restartQuiz() }
                )

                AppScreen.FOOD -> FoodScreen(
                    language = uiState.language,
                    activeCuisine = uiState.activeFoodCuisine,
                    onSelectCuisine = { viewModel.setFoodCuisine(it) },
                    favoriteIds = uiState.favoriteIds,
                    onToggleFavorite = { viewModel.toggleFavorite(it) }
                )

                AppScreen.EVENTS -> EventsScreen(
                    language = uiState.language,
                    activeFilter = uiState.activeEventFilter,
                    onFilterChange = { viewModel.setEventFilter(it) },
                    favoriteIds = uiState.favoriteIds,
                    onToggleFavorite = { viewModel.toggleFavorite(it) },
                    customEvents = uiState.customEvents,
                    showSubmitDialog = uiState.showEventSubmitDialog,
                    onOpenSubmit = { viewModel.openEventSubmitDialog() },
                    onCloseSubmit = { viewModel.closeEventSubmitDialog() },
                    onSubmitNewEvent = { t, k, d, tm, p, c, desc -> viewModel.submitNewEvent(t, k, d, tm, p, c, desc) },
                    submitSuccessMessage = uiState.eventSubmitSuccessMessage
                )

                AppScreen.DEALS -> DealsScreen(
                    language = uiState.language,
                    activeRedeemDeal = uiState.activeRedeemDeal,
                    onOpenRedeem = { viewModel.openRedeemDeal(it) },
                    onCloseRedeem = { viewModel.closeRedeemDeal() }
                )

                AppScreen.KNOWLEDGE -> KnowledgeScreen(
                    language = uiState.language,
                    activeCategory = uiState.activeKnowledgeCategory,
                    onCategoryChange = { viewModel.setKnowledgeCategory(it) }
                )

                AppScreen.FAVORITES -> FavoritesScreen(
                    language = uiState.language,
                    activeTypeFilter = uiState.activeFavoritesTypeFilter,
                    onFilterChange = { viewModel.setFavoritesTypeFilter(it) },
                    favoriteIds = uiState.favoriteIds,
                    onToggleFavorite = { viewModel.toggleFavorite(it) },
                    onKiezClick = { viewModel.openKiezDetail(it) }
                )

                AppScreen.PROFILE -> ProfileScreen(
                    language = uiState.language,
                    onLanguageChange = { viewModel.setLanguage(it) },
                    isDarkMode = uiState.isDarkMode,
                    onToggleDarkMode = { viewModel.toggleDarkMode() },
                    locationGranted = uiState.locationPermissionGranted,
                    onToggleLocation = { viewModel.toggleLocationPermission() },
                    onNavigate = { viewModel.navigateTo(it) }
                )

                AppScreen.BUSINESS_PORTAL -> BusinessPortalScreen(
                    language = uiState.language,
                    isYearly = uiState.isBillingYearly,
                    onToggleYearly = { viewModel.toggleBillingPeriod(it) },
                    selectedPackage = uiState.selectedPricingPackage,
                    onSelectPackage = { viewModel.selectPricingPackage(it) },
                    onCloseBooking = { viewModel.closeBookingDialog() },
                    onSubmitBooking = { c, e, k -> viewModel.submitBooking(c, e, k) },
                    bookingSuccessMessage = uiState.bookingSuccessMessage
                )

                AppScreen.ADMIN -> AdminScreen(
                    language = uiState.language,
                    isAuthenticated = uiState.isAdminAuthenticated,
                    currentTab = uiState.adminStoriesTab,
                    stories = uiState.customStories + com.example.data.repository.SampleData.stories,
                    onLogin = { token ->
                        val success = token == "admin123" || token == "kiez2026" || token == "admin"
                        viewModel.setAdminAuthenticated(success)
                        success
                    },
                    onTabChange = { viewModel.setAdminStoriesTab(it) },
                    onApproveStory = { id, h -> viewModel.approveStory(id, h) },
                    onRejectStory = { id, r -> viewModel.rejectStory(id, r) },
                    onExtendStory = { id, h -> viewModel.extendStory(id, h) },
                    onDeleteStory = { id -> viewModel.deleteStory(id) },
                    onUpdateStory = { id, h, s -> viewModel.updateStoryHeadline(id, h, s) },
                    onAddAdminStory = { h, s, k, kd, c, a, uri, isVid, dur ->
                        viewModel.addAdminStory(h, s, k, kd, c, a, uri, isVid, dur)
                    },
                    onBack = { viewModel.navigateTo(AppScreen.HOME) }
                )
            }
        }
    }

    // Global Hamburger Hub Modal (Header Sağ Köşe - Menü, Filtreler & Klasörler)
    if (showHamburgerMenu) {
        HamburgerHubModal(
            language = uiState.language,
            currentViewMode = uiState.businessViewMode,
            onSelectViewMode = { viewModel.setBusinessViewMode(it) },
            activeKiez = uiState.activeKiezFilter,
            onSelectKiez = { viewModel.setKiezFilter(it) },
            activeCategory = uiState.activeBusinessCategory,
            onSelectCategory = { viewModel.setBusinessCategory(it) },
            onNavigateToScreen = { viewModel.navigateTo(it) },
            onOpenAddBusiness = { viewModel.openAddBusinessDialog() },
            onResetFilters = { viewModel.resetFilters() },
            onDismiss = { showHamburgerMenu = false }
        )
    }

    // Story Viewer Modal
    uiState.activeStory?.let { story ->
        StoryViewerDialog(
            story = story,
            language = uiState.language,
            onClose = { viewModel.closeStory() }
        )
    }

    // Story Create Modal
    if (uiState.showAddStoryDialog) {
        StoryCreateDialog(
            language = uiState.language,
            onClose = { viewModel.closeAddStoryDialog() },
            onSubmit = { h, s, k, kd, c, a, uri, isVid, isComm, dur, pr ->
                viewModel.submitStory(h, s, k, kd, c, a, uri, isVid, isComm, dur, pr)
            }
        )
    }

    // Story Submission Feedback Toast / Alert
    uiState.storySubmissionFeedback?.let { feedback ->
        AlertDialog(
            onDismissRequest = { viewModel.clearStoryFeedback() },
            title = {
                Text(
                    text = if (feedback == "REJECTED")
                        (if (uiState.language == AppLanguage.TURKISH) "İçerik Reddedildi" else "Beitrag abgelehnt")
                    else
                        (if (uiState.language == AppLanguage.TURKISH) "Admin Onayına İletildi" else "Zur Admin-Prüfung eingereicht"),
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Text(
                    text = if (feedback == "REJECTED") {
                        if (uiState.language == AppLanguage.TURKISH)
                            "Gönderiniz politik, küfür veya 18+ içerik kurallarımıza aykırı olduğu için reddedilmiştir."
                        else
                            "Dein Beitrag verstößt gegen unsere Richtlinien (keine Politik, keine Beleidigungen, keine 18+ Inhalte) und wurde abgelehnt."
                    } else {
                        if (uiState.language == AppLanguage.TURKISH)
                            "Hikâyeniz başarıyla gönderildi! Güvenlik kuralları gereği admin tarafından incelendikten hemen sonra mykiezberlin.com üzerinde yayına alınacaktır."
                        else
                            "Deine Story wurde erfolgreich eingereicht! Sie wird nach kurzer Admin-Prüfung auf mykiezberlin.com freigeschaltet."
                    }
                )
            },
            confirmButton = {
                Button(
                    onClick = { viewModel.clearStoryFeedback() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (feedback == "REJECTED") BerlinRed else BerlinGreen
                    )
                ) {
                    Text(if (uiState.language == AppLanguage.TURKISH) "Tamam" else "Verstanden", color = Color.White)
                }
            }
        )
    }

    // More Bottom Sheet Menu
    if (showMoreBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showMoreBottomSheet = false },
            containerColor = MaterialTheme.colorScheme.surface,
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 8.dp)
                    .navigationBarsPadding(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Text(
                    text = if (uiState.language == AppLanguage.TURKISH) "Tüm Özellikler & Menü" else "Alle Bereiche & Entdecken",
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )

                // Row 1: Notruf (SOS) & Kiez-Reporter
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    SheetGridItem(
                        icon = Icons.Default.Warning,
                        label = if (uiState.language == AppLanguage.TURKISH) "Acil Numaralar (SOS)" else "Notruf & SOS (Berlin)",
                        color = BerlinRed,
                        modifier = Modifier.weight(1f)
                    ) {
                        viewModel.navigateTo(AppScreen.NOTRUF)
                        showMoreBottomSheet = false
                    }
                    SheetGridItem(
                        icon = Icons.Default.Campaign,
                        label = if (uiState.language == AppLanguage.TURKISH) "Kiez Muhabiri" else "Kiez-Reporter",
                        color = Color(0xFF0284C7),
                        modifier = Modifier.weight(1f)
                    ) {
                        viewModel.navigateTo(AppScreen.REPORTER)
                        showMoreBottomSheet = false
                    }
                }

                // Row 2: 12 Kieze & Gastronomie
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    SheetGridItem(
                        icon = Icons.Default.LocationOn,
                        label = if (uiState.language == AppLanguage.TURKISH) "12 Kiez Rehberi" else "Die 12 Kieze",
                        color = BerlinRed,
                        modifier = Modifier.weight(1f)
                    ) {
                        viewModel.navigateTo(AppScreen.KIEZE)
                        showMoreBottomSheet = false
                    }
                    SheetGridItem(
                        icon = Icons.Default.Restaurant,
                        label = if (uiState.language == AppLanguage.TURKISH) "Yeme & İçme" else "Gastronomie",
                        color = Color(0xFFF97316),
                        modifier = Modifier.weight(1f)
                    ) {
                        viewModel.navigateTo(AppScreen.FOOD)
                        showMoreBottomSheet = false
                    }
                }

                // Row 3: Events & Kiez-Wissen
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    SheetGridItem(
                        icon = Icons.Default.CalendarToday,
                        label = if (uiState.language == AppLanguage.TURKISH) "Etkinlikler" else "Events & Kultur",
                        color = BerlinGreen,
                        modifier = Modifier.weight(1f)
                    ) {
                        viewModel.navigateTo(AppScreen.EVENTS)
                        showMoreBottomSheet = false
                    }
                    SheetGridItem(
                        icon = Icons.Default.Lightbulb,
                        label = if (uiState.language == AppLanguage.TURKISH) "Kiez Rehberi" else "Kiez-Wissen",
                        color = Color(0xFF06B6D4),
                        modifier = Modifier.weight(1f)
                    ) {
                        viewModel.navigateTo(AppScreen.KNOWLEDGE)
                        showMoreBottomSheet = false
                    }
                }

                // Row 4: Merkliste & Einstellungen
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    SheetGridItem(
                        icon = Icons.Default.Favorite,
                        label = if (uiState.language == AppLanguage.TURKISH) "Kaydedilenler" else "Merkliste",
                        color = Color(0xFFEC4899),
                        modifier = Modifier.weight(1f)
                    ) {
                        viewModel.navigateTo(AppScreen.FAVORITES)
                        showMoreBottomSheet = false
                    }
                    SheetGridItem(
                        icon = Icons.Default.Settings,
                        label = if (uiState.language == AppLanguage.TURKISH) "Ayarlar & Dil" else "Einstellungen",
                        color = BerlinBlue,
                        modifier = Modifier.weight(1f)
                    ) {
                        viewModel.navigateTo(AppScreen.PROFILE)
                        showMoreBottomSheet = false
                    }
                }

                // Row 5: Admin Panel Direktzugriff
                Row(modifier = Modifier.fillMaxWidth()) {
                    SheetGridItem(
                        icon = Icons.Default.AdminPanelSettings,
                        label = if (uiState.language == AppLanguage.TURKISH) "Yönetici Paneli (Admin)" else "Admin-Bereich (Stories & Prüfung)",
                        color = Color(0xFF1F9D55),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        viewModel.navigateTo(AppScreen.ADMIN)
                        showMoreBottomSheet = false
                    }
                }

                Spacer(modifier = Modifier.height(2.dp))

                // B2B Special Button in Sheet
                Surface(
                    shape = RoundedCornerShape(12.dp),
                    color = BerlinRed,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            viewModel.navigateTo(AppScreen.BUSINESS_PORTAL)
                            showMoreBottomSheet = false
                        }
                ) {
                    Row(
                        modifier = Modifier.padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(imageVector = Icons.Default.Business, contentDescription = null, tint = Color.White)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (uiState.language == AppLanguage.TURKISH) "İşletmeler İçin Reklam (B2B Paketleri)" else "Für Unternehmen: Werbepakete ab 19 €/Monat",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 13.5.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SheetGridItem(
    icon: ImageVector,
    label: String,
    color: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier.clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surfaceVariant
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Surface(shape = RoundedCornerShape(8.dp), color = color.copy(alpha = 0.15f), modifier = Modifier.size(36.dp)) {
                Box(contentAlignment = Alignment.Center) {
                    Icon(imageVector = icon, contentDescription = null, tint = color, modifier = Modifier.size(20.dp))
                }
            }
            Text(text = label, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
        }
    }
}
