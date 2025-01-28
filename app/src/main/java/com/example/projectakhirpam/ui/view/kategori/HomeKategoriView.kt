package com.example.projectakhirpam.ui.view.kategori

import com.example.projectakhirpam.R
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectakhirpam.model.Kategori
import com.example.projectakhirpam.ui.customwidget.CustomeTopAppBarr
import com.example.projectakhirpam.ui.navigasi.DestinasiNavigasi
import com.example.projectakhirpam.ui.viewmodel.PenyediaViewModel
import com.example.projectakhirpam.ui.viewmodel.kategori.HomeKategoriUiState
import com.example.projectakhirpam.ui.viewmodel.kategori.HomeKategoriViewModel

object DestinasiHomeKategori: DestinasiNavigasi {
    override val route = "home_kategori"
    override val titleRes = "Home Kategori"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeKategoriScreen(
    navigateToItemEntry: () -> Unit,
    navigateToUpdate:(Kategori)-> Unit,
    modifier: Modifier = Modifier,
    onDetailClick: (String) -> Unit,
    onDeleteCLick: (String) -> Unit = {},
    viewModel: HomeKategoriViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    LaunchedEffect(Unit) {
        viewModel.getKategori()
    }

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomeTopAppBarr(
                title = DestinasiHomeKategori.titleRes,
                canNavigateBack = false,
                scrollBehavior = scrollBehavior,
                onRefresh = {
                    viewModel.getKategori()
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Kstegori") //belum diubah
            }
        }
    ) { innerpadding->
        HomeKategoriStatus(
            homeKategoriUiState = viewModel.kategoriUIState,
            retryAction = {viewModel.getKategori()},
            modifier = Modifier.padding(innerpadding),
            onDetailClick = onDetailClick,
            onDeleteClick = {
                viewModel.deleteKategori(it.id_kategori)
                viewModel.getKategori()
            },
            onUpdate = navigateToUpdate
        )
    }
}

@Composable
fun HomeKategoriStatus(
    homeKategoriUiState: HomeKategoriUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
    onDeleteClick: (Kategori) -> Unit = {},
    onDetailClick: (String) -> Unit,
    onUpdate: (Kategori) -> Unit,
) {
    when (homeKategoriUiState) {
        is HomeKategoriUiState.Loading -> OnLoading(modifier = modifier.fillMaxSize())
        is HomeKategoriUiState.Success ->
            if (homeKategoriUiState.kategori.isEmpty()) {
                Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Tidak ada data kategori")
                }
            } else {
                KategoriLayout(
                    kategori = homeKategoriUiState.kategori,
                    modifier = modifier,
                    onDetailClick = {onDetailClick(it.id_kategori)},
                    onDeleteClick = onDeleteClick,
                    onUpdate = onUpdate
                )
            }
        is HomeKategoriUiState.Error -> OnError(retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun KategoriLayout(
    kategori: List<Kategori>,
    modifier: Modifier = Modifier,
    onDetailClick: (Kategori) -> Unit,
    onDeleteClick: (Kategori) -> Unit = {},
    onUpdate: (Kategori) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(kategori) { kategori ->
            KategoriCard(
                kategori = kategori,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onDetailClick(kategori) },
                onDeleteClick = {
                    onDeleteClick(kategori)
                }
            )
        }
    }
}


@Composable
fun OnLoading(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier.size(200.dp),
        painter = painterResource(R.drawable.loading),
        contentDescription = stringResource(R.string.loading)
    )
}

@Composable
fun OnError(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_connection_error),
            contentDescription = ""
        )
        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
        Button(onClick = retryAction) {
            Text(stringResource(R.string.retry))
        }
    }
}

@Composable
fun KategoriCard(
    kategori: Kategori,
    modifier: Modifier = Modifier,
    onDeleteClick: (Kategori) -> Unit = {},
    onUpdate: (Kategori) -> Unit = {},
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
                    text = kategori.nama_kategori,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(Modifier.weight(1f))
                IconButton(onClick = { onUpdate(kategori) }) {   //tombol edit
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit kategori"
                    )
                }
                IconButton(onClick = { onDeleteClick(kategori) }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null
                    )
                }
                Text(
                    text = kategori.deskripsi_kategori,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }
    }
}
