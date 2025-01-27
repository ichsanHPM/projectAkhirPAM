package com.example.projectakhirpam.ui.view.penerbit

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
import com.example.projectakhirpam.ui.viewmodel.Penerbit.InsertPenerbitUiEvent
import com.example.projectakhirpam.ui.viewmodel.Penerbit.InsertPenerbitUiState
import com.example.projectakhirpam.ui.viewmodel.Penerbit.InsertPenerbitViewModel
import com.example.projectakhirpam.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch


object DestinasiEntryPenerbit : DestinasiNavigasi {
    override val route = "item_penerbit"
    override val titleRes = "Entry Penerbit"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryPenerbitScreen(
    navigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertPenerbitViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomeTopAppBarr(
                title = DestinasiEntryPenerbit.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        EntryPenerbitBody(
            insertPenerbitUiState = viewModel.uiState,
            onPenerbitValueChange = viewModel::updateInsertPenerbitState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.insertPenerbit()
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
fun EntryPenerbitBody(
    insertPenerbitUiState: InsertPenerbitUiState,
    onPenerbitValueChange: (InsertPenerbitUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInputPenerbit(
            insertPenerbitUiEvent = insertPenerbitUiState.insertUiEvent,
            onValueChange = onPenerbitValueChange,
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
fun FormInputPenerbit(
    insertPenerbitUiEvent: InsertPenerbitUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (InsertPenerbitUiEvent) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = insertPenerbitUiEvent.nama_penerbit,
            onValueChange = { onValueChange(insertPenerbitUiEvent.copy(nama_penerbit = it)) },
            label = { Text("Nama Penerbit") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertPenerbitUiEvent.alamat_penerbit,
            onValueChange = { onValueChange(insertPenerbitUiEvent.copy(alamat_penerbit = it)) },
            label = { Text("Alamat Penerbit") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = insertPenerbitUiEvent.telepon_penerbit,
            onValueChange = { onValueChange(insertPenerbitUiEvent.copy(telepon_penerbit = it)) },
            label = { Text("Kontak Penerbit") },
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