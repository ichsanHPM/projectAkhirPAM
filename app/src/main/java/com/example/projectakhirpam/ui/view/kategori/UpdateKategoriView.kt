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
import com.example.projectakhirpam.ui.viewmodel.PenyediaViewModel
import com.example.projectakhirpam.ui.viewmodel.UpdateUiEvent
import com.example.projectakhirpam.ui.viewmodel.UpdateUiState
import com.example.projectakhirpam.ui.viewmodel.kategori.UpdateKategoriUiEvent
import com.example.projectakhirpam.ui.viewmodel.kategori.UpdateKategoriUiState
import com.example.projectakhirpam.ui.viewmodel.kategori.UpdateKategoriViewModel
import kotlinx.coroutines.launch

object KategoriUpdate : DestinasiNavigasi {
    override val route = "item_kategori_update"
    override val titleRes = "Update Kategori"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateKategoriScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UpdateKategoriViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.uiState
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomeTopAppBarr(
                title = KategoriUpdate.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        UpdateKategoriBody(
            updateKategoriUiState = uiState,
            onKategoriValueChange = viewModel::updateUpdateKategoriState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updateKategori()
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
fun UpdateKategoriBody(
    updateKategoriUiState: UpdateKategoriUiState,
    onKategoriValueChange: (UpdateKategoriUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInputUpdateKategori(
            updateKategoriUiEvent = updateKategoriUiState.updateUiEvent,
            onValueChange = onKategoriValueChange,
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = onSaveClick,
            shape = MaterialTheme.shapes.small,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Update")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormInputUpdateKategori(
    updateKategoriUiEvent: UpdateKategoriUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (UpdateKategoriUiEvent) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = updateKategoriUiEvent.nama_kategori,
            onValueChange = { onValueChange(updateKategoriUiEvent.copy(nama_kategori = it)) },
            label = { Text("Nama Kategori") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = updateKategoriUiEvent.deskripsi_kategori,
            onValueChange = { onValueChange(updateKategoriUiEvent.copy(deskripsi_kategori = it)) },
            label = { Text("Deskripsi Kategori") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        if (enabled) {
            Text(
                text = "Update Semua Data!",
                modifier = Modifier.padding(12.dp)
            )
        }
        Divider(
            thickness = 8.dp,
            modifier = Modifier.padding(12.dp)
        )
    }
}
