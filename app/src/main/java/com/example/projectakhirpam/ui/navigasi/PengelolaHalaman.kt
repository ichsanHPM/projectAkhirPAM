package com.example.projectakhirpam.ui.navigasi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.projectakhirpam.ui.view.buku.DestinasiDetail
import com.example.projectakhirpam.ui.view.buku.DestinasiEntry
import com.example.projectakhirpam.ui.view.buku.DestinasiHome
import com.example.projectakhirpam.ui.view.buku.DetailBukuScreen
import com.example.projectakhirpam.ui.view.buku.EntryBkScreen
import com.example.projectakhirpam.ui.view.buku.HomeScreen
import com.example.projectakhirpam.ui.view.buku.UpdateBukuScreen

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()){
    NavHost(
        navController = navController,
        startDestination = DestinasiHome.route,
        modifier = Modifier
    ){
        composable(DestinasiHome.route){
            HomeScreen(
                navigateToItemEntry = { navController.navigate(DestinasiEntry.route) },
                onDetailClick = { id_buku ->
                    navController.navigate("${DestinasiDetail.route}/$id_buku")
                }
            )
        }
        composable(DestinasiEntry.route){
            EntryBkScreen(navigateBack = {
                navController.navigate(DestinasiHome.route){
                    popUpTo(DestinasiHome.route){
                        inclusive = true
                    }
                }
            })
        }
        composable(
            route = "${DestinasiDetail.route}/{id_buku}",
            arguments = listOf(navArgument("id_buku") { type = NavType.StringType })
        ) { backStackEntry ->
            val id_buku = backStackEntry.arguments?.getString("id_buku") ?: return@composable
            DetailBukuScreen(
                id_buku = id_buku,
                navigateBack = {
                    navController.navigateUp()
                },
                navController = navController
            )
        }
        composable(
            route = "update_bk/{id_buku}",
            arguments = listOf(navArgument("id_buku") {type = NavType.StringType })
        ) { backStackEntry ->
            val id_buku = backStackEntry.arguments?.getString("id_buku") ?: return@composable
            UpdateBukuScreen(
                id_buku = id_buku,
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}
