package com.example.projectakhirpam.ui.view.kategori

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectakhirpam.ui.customwidget.CustomeTopAppBarr
import com.example.projectakhirpam.ui.navigasi.DestinasiNavigasi
import com.example.projectakhirpam.ui.viewmodel.InsertUiEvent
import com.example.projectakhirpam.ui.viewmodel.InsertUiState
import com.example.projectakhirpam.ui.viewmodel.PenyediaViewModel
import com.example.projectakhirpam.ui.viewmodel.kategori.InsertKategoriUiEvent
import com.example.projectakhirpam.ui.viewmodel.kategori.InsertKategoriUiState
import com.example.projectakhirpam.ui.viewmodel.kategori.InsertKategoriViewModel
import kotlinx.coroutines.launch

object KategoriEntry : DestinasiNavigasi {
    override val route = "item_kategori"
    override val titleRes = "Entry Kategori"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryKategoriScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertKategoriViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomeTopAppBarr(
                title = KategoriEntry.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        EntryKategoriBody(
            insertKategoriUiState = viewModel.uiState,
            onKategoriValueChange = viewModel::updateInsertKategoriState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertKategori()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxWidth()
        )
    }
}

@Composable
fun EntryKategoriBody(
    insertKategoriUiState: InsertKategoriUiState,
    onKategoriValueChange: (InsertKategoriUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInputKategori(
            insertKategoriUiEvent = insertKategoriUiState.insertUiEvent,
            onValueChange = onKategoriValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Simpan")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInputKategori(
    insertKategoriUiEvent: InsertKategoriUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (InsertKategoriUiEvent) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = insertKategoriUiEvent.id_kategori,
            onValueChange = { onValueChange(insertKategoriUiEvent.copy(id_kategori = it)) },
            label = { Text("Id Kategori") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertKategoriUiEvent.nama_kategori,
            onValueChange = { onValueChange(insertKategoriUiEvent.copy(nama_kategori = it)) },
            label = { Text("Nama Kategori") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertKategoriUiEvent.deskripsi_kategori,
            onValueChange = { onValueChange(insertKategoriUiEvent.copy(deskripsi_kategori = it)) },
            label = { Text("Deskripsi Kategori") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        if (enabled) {
            Text(
                text = "Isi Semua Data!",
                modifier = Modifier.padding(12.dp)
            )
        }
        Divider(
            thickness = 8.dp,
            modifier = Modifier.padding(12.dp)
        )
    }
}