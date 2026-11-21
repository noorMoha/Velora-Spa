package com.veloraspa.app.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.veloraspa.app.ui.screens.EditAddServiceScreen
import com.veloraspa.app.ui.screens.OurSpecialistsScreen
import com.veloraspa.app.ui.screens.ServiceDetailsScreen
import com.veloraspa.app.ui.screens.SplashScreen
import com.veloraspa.app.ui.screens.StaffAndReviewsScreen
import com.veloraspa.app.model.Service
import com.veloraspa.app.ui.screens.AllAppointmentsScreen
import com.veloraspa.app.ui.screens.FavoritesScreen
import com.veloraspa.app.ui.screens.ManageBookingsScreen
import com.veloraspa.app.ui.screens.AdminDashboardScreen
import com.veloraspa.app.ui.screens.HomeScreen
import com.veloraspa.app.ui.screens.BrowseServicesScreen
import com.veloraspa.app.ui.screens.ManageServicesScreen

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

        composable(Screen.ServiceDetails.route) {
            ServiceDetailsScreen(
                service = Service(
                    id = "1",
                    name = "Signature Massage",
                    description = "Experience ultimate relaxation with our signature treatment, combining Swedish and deep tissue techniques.",
                    price = 90.0,
                    duration = "60",
                    category = "Massage Therapy"
                ),
                onBackClick = {
                    navController.popBackStack()
                },
                onBookClick = {
                    navController.navigate(Screen.BookAppointment.route)
                },
                onFavoriteClick = {
                    // TODO: Toggle favorite state
                }
            )
        }

        composable(Screen.EditAddService.route) {
            EditAddServiceScreen(
                service = null,
                onBackClick = {
                    navController.popBackStack()
                },
                onSaveClick = {
                    navController.popBackStack()
                },
                onPreviewClick = {
                    navController.navigate(Screen.ServiceDetails.route)
                },
                onChangeImageClick = {
                    // TODO: Handle image selection
                }
            )
        }
        composable(Screen.ManageBookings.route) {
            ManageBookingsScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onRescheduleClick = { booking ->
                    navController.navigate(Screen.BookAppointment.route)
                }
            )
        }

        composable(Screen.Favorites.route) {
            FavoritesScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onBookServiceClick = { service ->
                    navController.navigate(Screen.BookAppointment.route)
                },
                onSelectSpecialistClick = { specialist ->
                    navController.navigate(Screen.BookAppointment.route)
                }
            )
        }

        composable(Screen.AllAppointments.route) {
            AllAppointmentsScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(
                onNavigateToServices = { _ ->
                    navController.navigate(Screen.BrowseServices.route)
                },
                onNavigateToServiceDetails = { _ ->
                    navController.navigate(Screen.ServiceDetails.route)
                },
                onNavigateToBookAppointment = {
                    navController.navigate(Screen.BookAppointment.route)
                },
                onNavigateToBookings = {
                    navController.navigate(Screen.ManageBookings.route)
                },
                onNavigateToFavorites = {
                    navController.navigate(Screen.Favorites.route)
                },
                onNavigateToProfile = {
                    navController.navigate(Screen.ProfileCustomer.route)
                }
            )
        }

        composable(Screen.AdminDashboard.route) {
            AdminDashboardScreen(
                onNavigateToManageServices = {
                    navController.navigate(Screen.ManageServices.route)
                },
                onNavigateToAllAppointments = {
                    navController.navigate(Screen.AllAppointments.route)
                },
                onNavigateToSpecialists = {
                    navController.navigate(Screen.StaffAndReviews.route)
                },
                onNavigateToConsumer = {
                    navController.navigate(Screen.Home.route)
                },
                onNavigateToStaffProfile = {
                    navController.navigate(Screen.StaffProfile.route)
                }
            )
        }
        composable(Screen.BrowseServices.route) {
            BrowseServicesScreen(
                onBackClick = {
                    navController.popBackStack()
                },
                onBookService = { service ->
                    navController.navigate(Screen.ServiceDetails.route)
                }
            )
        }

        composable(Screen.ManageServices.route) {
            ManageServicesScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}