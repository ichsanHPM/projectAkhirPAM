package com.example.projectakhirpam.ui.view.penerbit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.projectakhirpam.ui.customwidget.CustomeTopAppBarr
import com.example.projectakhirpam.ui.navigasi.DestinasiNavigasi
import com.example.projectakhirpam.ui.viewmodel.Penerbit.UpdatePenerbitUiEvent
import com.example.projectakhirpam.ui.viewmodel.Penerbit.UpdatePenerbitUiState
import com.example.projectakhirpam.ui.viewmodel.Penerbit.UpdatePenerbitViewModel
import com.example.projectakhirpam.ui.viewmodel.PenyediaViewModel
import com.example.projectakhirpam.ui.viewmodel.UpdateUiEvent
import com.example.projectakhirpam.ui.viewmodel.UpdateUiState
import com.example.projectakhirpam.ui.viewmodel.kategori.UpdateKategoriUiEvent
import kotlinx.coroutines.launch


object UpdatePenerbit : DestinasiNavigasi {
    override val route = "update_penerbit"
    override val titleRes = "Update Penerbit"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdatePenerbitScreen(
    navigateBack: () -> Unit,
    id_penerbit: String,
    modifier: Modifier = Modifier,
    viewModel: UpdatePenerbitViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.uiState
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    LaunchedEffect(id_penerbit) {
        viewModel.getPenerbitById(id_penerbit)
    }

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomeTopAppBarr(
                UpdatePenerbit.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        UpdatePenerbitBody(
            updatePenerbitUiState = uiState,
            onPenerbitValueChange = viewModel::updateUpdatePenerbitState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updatePenerbit()
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
fun UpdatePenerbitBody(
    updatePenerbitUiState: UpdatePenerbitUiState,
    onPenerbitValueChange: (UpdatePenerbitUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInputPenerbit(
            updatePenerbitUiEvent = updatePenerbitUiState.updateUiEvent,
            onValueChange = onPenerbitValueChange,
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
fun FormInputPenerbit(
    updatePenerbitUiEvent: UpdatePenerbitUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (UpdatePenerbitUiEvent) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = updatePenerbitUiEvent.nama_penerbit,
            onValueChange = { onValueChange(updatePenerbitUiEvent.copy(nama_penerbit = it)) },
            label = { Text("Nama Penerbit") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = updatePenerbitUiEvent.alamat_penerbit,
            onValueChange = { onValueChange(updatePenerbitUiEvent.copy(alamat_penerbit = it)) },
            label = { Text("Alamat Penerbit") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = updatePenerbitUiEvent.telepon_penerbit,
            onValueChange = { onValueChange(updatePenerbitUiEvent.copy(telepon_penerbit = it)) },
            label = { Text("Kontak Penerbit") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = updatePenerbitUiEvent.id_penerbit,
            onValueChange = { onValueChange(updatePenerbitUiEvent.copy(id_penerbit = it)) },
            label = { Text("ID Penerbit") },
            modifier = Modifier.fillMaxWidth(),
            enabled = false,
            singleLine = true
        )
    }
}