//package com.example.navigation
//
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Home
//import androidx.compose.material.icons.outlined.Article
//import androidx.compose.material.icons.outlined.CalendarMonth
//import androidx.compose.material.icons.outlined.Home
//import androidx.compose.material.icons.outlined.LocationOn
//import androidx.compose.ui.graphics.vector.ImageVector
//import com.example.home.ui.navigation.MAP_SCREEN_ROUTE
//
//
//data class BottomNavigationItem<T: Any>(
//    val icon: ImageVector = Icons.Filled.Home,
//    val route: T? = null,
//    val label: String? = "",
//) {
//
//    //function to get the list of bottomNavigationItems
//    fun bottomNavigationItems(): List<BottomNavigationItem<Any>> {
//        return listOf(
//            BottomNavigationItem(
//                route = HomeScreen,
//                icon = Icons.Outlined.Home,
//                label = Screens.Home.localizedName
//            ),
//            BottomNavigationItem(
//                icon = Icons.Outlined.LocationOn,
//                route = MapScreen,
//                label = Screens.Map.localizedName
//            ),
////            BottomNavigationItem(
////                icon = Icons.Outlined.CalendarMonth,
////                route = Screens.Calendar.route,
////                label = Screens.Calendar.localizedName
////            ),
////            BottomNavigationItem(
////                icon = Icons.Outlined.Article,
////                route = Screens.Feed.route,
////                label = Screens.Feed.localizedName
////            )
//        )
//    }
//}
