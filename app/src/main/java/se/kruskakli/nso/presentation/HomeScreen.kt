package se.kruskakli.nso.presentation

import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import se.kruskakli.nso.R
import se.kruskakli.nso.domain.MainIntent
import se.kruskakli.nso.domain.MainViewModel
import se.kruskakli.presentation.RememberDevices
import se.kruskakli.presentation.RememberPackages
import se.kruskakli.presentation.RememberAlarms
import androidx.compose.ui.res.vectorResource


@Composable
fun ModalDrawer(drawerState: Any, drawerContent: () -> Unit, content: () -> Unit, function: () -> Unit, function1: (String?, MainViewModel) -> Unit, function2: () -> Unit, function3: ((TabPage) -> Unit) -> Unit) {

}

/*
    * The HomeScreen is the main screen of the application. It is a composable function that
    * takes a MainViewModel as a parameter. The content of the body is determined by the current
    * tab, which is a state variable.
    *
    * The HomeScreen function defines several state variables that are collected from the ViewModel,
    * including apiError, loading, nsoPackages, nsoDevices, nsoAlarms, and refresh.
    * These state variables are observed and any changes to them will cause a recompose to occur
    * of the appropriate parts of the UI.
    *
    * For the PackagesScreen, DevicesScreen, and AlarmsScreen, before they are displayed,
    * an intent is sent to the ViewModel to show the packages, devices, or alarms, respectively.
    * This will trigger the ViewModel to enable fetching data and/or update the state, changes
    * which then is observed by the HomeScreen.
*/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: MainViewModel) {
    var page by remember { mutableStateOf(TabPage.Home) }

    val apiError by viewModel.apiError.collectAsState()
    val loading by viewModel.loading.collectAsState()
    val nsoPackages by viewModel.nsoPackages.collectAsState()
    val nsoDevices by viewModel.nsoDevices.collectAsState()
    val nsoAlarms by viewModel.nsoAlarms.collectAsState()
    val nsoInet by viewModel.nsoInet.collectAsState()
    val nsoEts by viewModel.nsoEts.collectAsState()
    val nsoAllocators by viewModel.nsoAllocators.collectAsState()
    val nsoProcesses by viewModel.nsoProcesses.collectAsState()
    val nsoDbgEnabled by viewModel.nsoDbgEnabled.collectAsState()

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }
    val menuItems = MenuItems()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet{
                Spacer(modifier = Modifier.height(16.dp))
                menuItems.forEachIndexed { index, item ->
                    if (item.hasSubItems) {
                        if (nsoDbgEnabled) {
                            CustomNestedMenu(
                                item,
                                fun(newPage: TabPage) {
                                    page = newPage
                                    scope.launch {
                                        drawerState.close()
                                    }
                                }
                            )
                        }
                    } else {
                        NavigationDrawerItem(
                            label = {
                                Text(
                                    text = item.title,
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = if (index == selectedItemIndex) {
                                        FontWeight.Bold
                                    } else {
                                        FontWeight.Normal
                                    }
                                )
                            },
                            selected = index == selectedItemIndex,
                            onClick = {
                                selectedItemIndex = index
                                page = item.page
                                scope.launch{
                                    drawerState.close()
                                }
                            },
                            icon = {
                                Icon(
                                    modifier = Modifier.size(20.dp),
                                    imageVector = if (index == selectedItemIndex) {
                                        item.selectedIcon
                                    } else {
                                        item.unSelectedIcon
                                    },
                                    contentDescription = item.title
                                )
                            },
                            modifier = Modifier
                                .padding(NavigationDrawerItemDefaults.ItemPadding)
                        )
                    }
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text("NSO Mobile")
                            IconButton(onClick = {
                                viewModel.handleIntent(MainIntent.RefreshPage(page))
                            }) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_flower),
                                    contentDescription = "Refresh")
                            }
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }) {
                            Icon(
                                Icons.Filled.Menu,
                                contentDescription = "Menu"
                            )
                        }
                    }
                )
            }
        ) { padding ->
            Box(
                modifier = Modifier
                    .padding(
                        top = padding.calculateTopPadding(),
                        start = padding.calculateLeftPadding(LayoutDirection.Ltr),
                        end = padding.calculateEndPadding(LayoutDirection.Ltr)
                    )
            ) {
                Divider()
                if (apiError != null) {
                    page = TabPage.Error
                }
                when (page) {
                    TabPage.Home -> {
                        WelcomePage()
                    }

                    TabPage.Settings -> {
                        SettingsScreen(viewModel)
                    }

                    TabPage.Packages -> {
                        viewModel.handleIntent(MainIntent.ShowPackages)
                        if (loading) {
                            LoadingState()
                        } else {
                            PackagesScreen(nsoPackages)
                        }
                    }

                    TabPage.Devices -> {
                        viewModel.handleIntent(MainIntent.ShowDevices)
                        if (loading) {
                            LoadingState()
                        } else {
                            DevicesScreen(nsoDevices)
                        }
                    }

                    TabPage.Alarms -> {
                        viewModel.handleIntent(MainIntent.ShowAlarms)
                        if (loading) {
                            LoadingState()
                        } else {
                            AlarmsScreen(nsoAlarms)
                        }
                    }

                    TabPage.About -> {
                        AboutPage()
                    }

                    TabPage.Debug -> {
                        AboutPage()
                    }

                    TabPage.Listeners -> {
                        viewModel.handleIntent(MainIntent.ShowInet)
                        if (loading) {
                            LoadingState()
                        } else {
                            InetScreen(nsoInet)
                        }
                    }

                    TabPage.EtsTables -> {
                        viewModel.handleIntent(MainIntent.ShowEts)
                        if (loading) {
                            LoadingState()
                        } else {
                            EtsScreen(nsoEts, viewModel)
                        }
                    }

                    TabPage.Allocators -> {
                        viewModel.handleIntent(MainIntent.ShowAllocators)
                        if (loading) {
                            LoadingState()
                        } else {
                            AllocatorScreen(nsoAllocators)
                        }
                    }

                    TabPage.Processes -> {
                        viewModel.handleIntent(MainIntent.ShowProcesses)
                        if (loading) {
                            LoadingState()
                        } else {
                            ProcessScreen(nsoProcesses, viewModel)
                        }
                    }

                    TabPage.Error -> {
                        ErrorPage(apiError, viewModel)
                    }
                }
            }
        }
    }
}

/*
 * Since the ModalDrawerSheet composable didn't support nested menus,
 * I had to create a custom composable for the nested menu items.
 */
@Composable
fun CustomNestedMenu(
    item: NavigationItem,
    onClick: (TabPage) -> Unit
) {
    Column {
        Row(
            modifier = Modifier
                .padding(start = 30.dp, bottom = 5.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = item.selectedIcon,
                contentDescription = item.title,
                modifier = Modifier.size(20.dp),
                tint = Color.Black
            )
            Text(
                text = item.title,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier
                    .padding(start = 10.dp)
            )
        }
        item.subItems.forEach { subItem ->
            Row(
                modifier = Modifier
                    .padding(start = 55.dp, bottom = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.PlayArrow,
                    contentDescription = "SubItem",
                    modifier = Modifier.size(10.dp),
                    tint = Color.Black
                )
                val text = AnnotatedString.Builder().apply {
                    append(subItem.title)
                }.toAnnotatedString()
                ClickableText(
                    text = text,
                    onClick = { onClick(subItem.page) },
                    style = MaterialTheme.typography.labelSmall,
                    modifier = Modifier
                        .padding(start = 10.dp)
                )
            }
        }
    }
}

data class NavigationItem(
    val title: String,
    val page: TabPage = TabPage.Home,
    val selectedIcon: ImageVector,
    val unSelectedIcon: ImageVector,
    val subItems: List<NavigationItem> = emptyList()
) {
    val hasSubItems: Boolean
        get() = subItems.isNotEmpty()
}

@Composable
private fun MenuItems(): List<NavigationItem> {
    return listOf(
        NavigationItem(
            title = "Home",
            page = TabPage.Home,
            selectedIcon = Icons.Filled.Home,
            unSelectedIcon = Icons.Outlined.Home
        ),
        NavigationItem(
            title = "Settings",
            page = TabPage.Settings,
            selectedIcon = Icons.Filled.Settings,
            unSelectedIcon = Icons.Outlined.Settings
        ),
        NavigationItem(
            title = "Packages",
            page = TabPage.Packages,
            selectedIcon = RememberPackages(),
            unSelectedIcon = RememberPackages()
        ),
        NavigationItem(
            title = "Devices",
            page = TabPage.Devices,
            selectedIcon = RememberDevices(),
            unSelectedIcon = RememberDevices()
        ),
        NavigationItem(
            title = "Alarms",
            page = TabPage.Alarms,
            selectedIcon = RememberAlarms(),
            unSelectedIcon = RememberAlarms()
        ),
        NavigationItem(
            title = "About",
            page = TabPage.About,
            selectedIcon = RememberQuestionMark(),
            unSelectedIcon = RememberQuestionMark()
        ),
        NavigationItem(
            title = "Debug",
            selectedIcon = ImageVector.vectorResource(id = R.drawable.ic_bug),
            unSelectedIcon = Icons.Outlined.Build,
            subItems = listOf(
                NavigationItem(
                    title = "Processes",
                    page = TabPage.Processes,
                    selectedIcon = RememberQuestionMark(),
                    unSelectedIcon = RememberQuestionMark()
                ),
                NavigationItem(
                    title = "Network Listeners",
                    page = TabPage.Listeners,
                    selectedIcon = RememberQuestionMark(),
                    unSelectedIcon = RememberQuestionMark()
                ),
                NavigationItem(
                    title = "ETS tables",
                    page = TabPage.EtsTables,
                    selectedIcon = RememberQuestionMark(),
                    unSelectedIcon = RememberQuestionMark()
                ),
                NavigationItem(
                    title = "ERTS Allocators",
                    page = TabPage.Allocators,
                    selectedIcon = RememberQuestionMark(),
                    unSelectedIcon = RememberQuestionMark()
                )
            )
        )
    )
}

@Composable
fun AboutPage() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 30.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Card(
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 4.dp
            )
        ) {
            val text = """
            NSO Mobile is an experimental application that allows
            you to view the alarms, devices, packages and debug
            info from your NSO system.
             
            It is built with Kotlin and Jetpack Compose, mainly
            as an exercise in learning these technologies.
        """.trimIndent()
            Text(
                text = text,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun ErrorPage(apiError: String?, viewModel: MainViewModel) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (apiError != null) {
            Box(
                modifier = Modifier
                    .padding(start = 8.dp, top = 20.dp, end = 8.dp)
                    .border(2.dp, Color.Red)
                    .clickable {
                        Log.d("MainActivity", "Error clicked (clearing apiError)")
                        viewModel.clearApiError()
                    }
            ) {
                Text(
                    text = "Error (click to clear): $apiError",
                    modifier = Modifier.padding(8.dp),
                    color = Color.Red,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun WelcomePage()
{
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Welcome to NSO Mobile!",
            modifier = Modifier
                .padding(top = 100.dp),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.titleLarge.copy(
                shadow = Shadow(
                    color = Color.LightGray,
                    offset = Offset(4f, 4f),
                    blurRadius = 8f
                )
            )
        )
    }
}

/*
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen("Blueberry", "10.0.0.1", "8888", { _, _, _ -> })
}
*/