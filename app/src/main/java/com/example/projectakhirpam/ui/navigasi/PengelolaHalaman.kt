package com.example.projectakhirpam.ui.navigasi

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.projectakhirpam.ui.view.Home.DestinasiHomeScreen
import com.example.projectakhirpam.ui.view.Home.Home
import com.example.projectakhirpam.ui.view.buku.DestinasiDetail
import com.example.projectakhirpam.ui.view.buku.DestinasiEntry
import com.example.projectakhirpam.ui.view.buku.DestinasiHome
import com.example.projectakhirpam.ui.view.buku.DetailBukuScreen
import com.example.projectakhirpam.ui.view.buku.EntryBkScreen
import com.example.projectakhirpam.ui.view.buku.HomeScreen
import com.example.projectakhirpam.ui.view.buku.UpdateBuku
import com.example.projectakhirpam.ui.view.buku.UpdateBukuScreen
import com.example.projectakhirpam.ui.view.kategori.DestinasiHomeKategori
import com.example.projectakhirpam.ui.view.kategori.DetailKategoriScreen
import com.example.projectakhirpam.ui.view.kategori.EntryKategoriScreen
import com.example.projectakhirpam.ui.view.kategori.HomeKategoriScreen
import com.example.projectakhirpam.ui.view.kategori.KategoriDetail
import com.example.projectakhirpam.ui.view.kategori.KategoriEntry
import com.example.projectakhirpam.ui.view.kategori.KategoriUpdate
import com.example.projectakhirpam.ui.view.kategori.UpdateKategoriScreen
import com.example.projectakhirpam.ui.view.penerbit.DestinasiDetailPenerbit
import com.example.projectakhirpam.ui.view.penerbit.DestinasiEntryPenerbit
import com.example.projectakhirpam.ui.view.penerbit.DestinasiHomePenerbit
import com.example.projectakhirpam.ui.view.penerbit.DetailPenerbitScreen
import com.example.projectakhirpam.ui.view.penerbit.EntryPenerbitScreen
import com.example.projectakhirpam.ui.view.penerbit.HomePenerbitScreen
import com.example.projectakhirpam.ui.view.penerbit.UpdatePenerbit
import com.example.projectakhirpam.ui.view.penerbit.UpdatePenerbitScreen

@Composable
fun PengelolaHalaman(navController: NavHostController = rememberNavController()){
    NavHost(
        navController = navController,
        startDestination = DestinasiHomeScreen.route,
        modifier = Modifier
    ){

        composable(
            route = DestinasiHomeScreen.route) {
            Home(
                onNavigateToBuku = { navController.navigate(DestinasiHome.route) },
                onNavigateToPenerbit = { navController.navigate(DestinasiHomePenerbit.route) },
                modifier = Modifier
            )
        }

        composable(DestinasiHome.route){
            HomeScreen(
                navigateBack = { navController.navigate(DestinasiHomeScreen.route) },
                navigateToItemEntry = { navController.navigate(DestinasiEntry.route) },
                onDetailClick = { id_buku ->
                    navController.navigate("${DestinasiDetail.route}/$id_buku")
                },
                navigateToUpdate = {buku->
                    navController.navigate("${UpdateBuku.route}/${buku.id_buku}")
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
            route = "${UpdateBuku.route}/{id_buku}",
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
        composable(DestinasiHomePenerbit.route){
            HomePenerbitScreen(
                onDetailClick = {id_penerbit ->
                    navController.navigate("${DestinasiDetailPenerbit.route}/$id_penerbit")
                },
                navigateToItemEntry = {navController.navigate(DestinasiEntryPenerbit.route)},
                navigateBack = { navController.navigate(DestinasiHomeScreen.route) },

            )
        }
        composable(DestinasiEntryPenerbit.route){
            EntryPenerbitScreen(navigateBack = {
                navController.navigate(DestinasiHomePenerbit.route){
                    popUpTo(DestinasiHomePenerbit.route){
                        inclusive = true
                    }
                }
            })
        }
        composable(
            route = "${UpdatePenerbit.route}/{id_penerbit}",
            arguments = listOf(navArgument("id_penerbit") {type = NavType.StringType })
        ) { backStackEntry ->
            val id_penerbit = backStackEntry.arguments?.getString("id_penerbit") ?: return@composable
            UpdatePenerbitScreen(
                id_penerbit = id_penerbit,
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
        composable(
                route = "${DestinasiDetailPenerbit.route}/{id_penerbit}",
        arguments = listOf(navArgument("id_penerbit") { type = NavType.StringType })
        ) { backStackEntry ->
        val id_penerbit = backStackEntry.arguments?.getString("id_penerbit") ?: return@composable
        DetailPenerbitScreen(
            id_penerbit = id_penerbit,
            navigateBack = {
                navController.navigateUp()
                },
            navController = navController
            )
        }

        composable(DestinasiHomeKategori.route){
            HomeKategoriScreen(
                onDetailClick = {id_kategori ->
                    navController.navigate("${KategoriDetail.route}/$id_kategori")
                },
                navigateToItemEntry = {navController.navigate(KategoriEntry.route)},
                navigateToUpdate = {navController.navigate(KategoriEntry.route)}

            )
        }
        composable(
            route = "${UpdatePenerbit.route}/{id_penerbit}",
            arguments = listOf(navArgument("id_penerbit") {type = NavType.StringType })
        ) { backStackEntry ->
            val id_penerbit = backStackEntry.arguments?.getString("id_penerbit") ?: return@composable
            UpdatePenerbitScreen(
                id_penerbit = id_penerbit,
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
        composable(KategoriEntry.route){
            EntryKategoriScreen(navigateBack = {
                navController.navigate(DestinasiHomeKategori.route){
                    popUpTo(DestinasiHomeKategori.route){
                        inclusive = true
                    }
                }
            })
        }
        composable(
            route = "${KategoriDetail.route}/{id_kategori}",
            arguments = listOf(navArgument("id_kategori") { type = NavType.StringType })
        ) { backStackEntry ->
            val id_kategori = backStackEntry.arguments?.getString("id_kategori") ?: return@composable
            DetailKategoriScreen(
                id_kategori = id_kategori,
                navigateBack = {
                    navController.navigateUp()
                },
                navController = navController
            )
        }
        composable(
            route = "updateKategori/{id_kategori}",
            arguments = listOf(navArgument("id_kategori") { type = NavType.StringType })
        ) { backStackEntry ->
            val id_kategori = backStackEntry.arguments?.getString("id_kategori")
            UpdateKategoriScreen(
                id_kategori = id_kategori ?: return@composable,
                navigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}
