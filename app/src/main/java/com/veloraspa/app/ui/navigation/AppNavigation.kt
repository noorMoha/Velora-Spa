package com.veloraspa.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.veloraspa.app.ui.screens.OurSpecialistsScreen
import com.veloraspa.app.ui.screens.SplashScreen
import com.veloraspa.app.ui.screens.StaffAndReviewsScreen

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object SignUp : Screen("sign_up")
    object AdminStaffLogin : Screen("admin_staff_login")
    object Home : Screen("home")
    object BrowseServices : Screen("browse_services")
    object ServiceDetails : Screen("service_details")
    object OurSpecialists : Screen("our_specialists")
    object BookAppointment : Screen("book_appointment")
    object AppointmentConfirmed : Screen("appointment_confirmed")
    object ManageBookings : Screen("manage_bookings")
    object Favorites : Screen("favorites")
    object ProfileCustomer : Screen("profile_customer")
    object AdminDashboard : Screen("admin_dashboard")
    object AllAppointments : Screen("all_appointments")
    object ManageServices : Screen("manage_services")
    object EditAddService : Screen("edit_add_service")
    object StaffAndReviews : Screen("admin_specialists")
    object StaffProfile : Screen("staff_profile")
}

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {

        composable(Screen.Splash.route) {
            SplashScreen(
                onGetStartedClick = {
                    navController.navigate(Screen.SignUp.route)
                },
                onLoginClick = {
                    navController.navigate(Screen.Login.route)
                }
            )
        }

        composable(Screen.OurSpecialists.route) {
            OurSpecialistsScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onSearchClick = {
                    // TODO: Handle search
                },
                onSelectClick = { specialist ->
                    navController.navigate(Screen.BookAppointment.route)
                }
            )
        }

        composable(Screen.StaffAndReviews.route) {
            StaffAndReviewsScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        //...
    }
}