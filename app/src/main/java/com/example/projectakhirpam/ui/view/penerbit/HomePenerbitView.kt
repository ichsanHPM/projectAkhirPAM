package com.example.projectakhirpam.ui.view.penerbit

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectakhirpam.model.Penerbit
import com.example.projectakhirpam.ui.customwidget.CustomeTopAppBarr
import com.example.projectakhirpam.ui.navigasi.DestinasiNavigasi
import com.example.projectakhirpam.ui.view.buku.OnLoading
import com.example.projectakhirpam.ui.viewmodel.Penerbit.HomePenerbitUiState
import com.example.projectakhirpam.ui.viewmodel.Penerbit.HomePenerbitViewModel
import com.example.projectakhirpam.ui.viewmodel.PenyediaViewModel


object DestinasiHomePenerbit : DestinasiNavigasi {
    override val route = "home_penerbit"
    override val titleRes = "Home Penerbit"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomePenerbitScreen(
    navigateToItemEntry: () -> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (Penerbit) -> Unit = {},
    viewModel: HomePenerbitViewModel = viewModel (factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    LaunchedEffect(Unit) {
        viewModel.getPenerbit()
    }

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomeTopAppBarr(
                title = DestinasiHomePenerbit.titleRes,
                canNavigateBack = false,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getPenerbit()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Penerbit")
            }
        }
    ) { innerPadding ->
        HomePenerbitStatus(
            homePenerbitUiState = viewModel.penerbitUiState,
            retryAction = { viewModel.getPenerbit() },
            modifier = Modifier.padding(innerPadding),
            onDetailClick = onDetailClick,
            onDeleteClick = {
                viewModel.deletePenerbit(it.id_penerbit)
                viewModel.getPenerbit()
            }
        )
    }
}

@Composable
fun HomePenerbitStatus(
    homePenerbitUiState: HomePenerbitUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Penerbit) -> Unit = {},
    onDetailClick: (Penerbit) -> Unit
) {
    when (homePenerbitUiState) {
        is HomePenerbitUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())
        is HomePenerbitUiState.Success -> {
            if (homePenerbitUiState.penerbit.isEmpty()) {
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Tidak ada data penerbit")
                }
            } else {
                PenerbitLayout(
                    penerbit = homePenerbitUiState.penerbit,
                    modifier = modifier,
                    onDetailClick = onDetailClick,
                    onDeleteClick = onDeleteClick
                )
            }
        }
        is HomePenerbitUiState.Error -> Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Terjadi kesalahan. Coba lagi.")
        }
    }
}

@Composable
fun PenerbitLayout(
    penerbit: List<Penerbit>,
    modifier: Modifier = Modifier,
    onDetailClick: (Penerbit) -> Unit,
    onDeleteClick: (Penerbit) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(penerbit) { pnrbt ->
            PenerbitCard(
                penerbit = pnrbt,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(pnrbt) },
                onDeleteClick = {
                    onDeleteClick(pnrbt)
                }
            )
        }
    }
}

@Composable
fun PenerbitCard(
    penerbit: Penerbit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Penerbit) -> Unit
) {
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = penerbit.nama_penerbit,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = { onDeleteClick(penerbit) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Penerbit"
                    )
                }
            }
            Text(
                text = "Alamat: ${penerbit.alamat_penerbit}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "Telepon: ${penerbit.telepon_penerbit}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}