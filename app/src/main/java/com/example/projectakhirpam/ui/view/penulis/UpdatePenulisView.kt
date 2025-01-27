package com.example.projectakhirpam.ui.view.penulis

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
import com.example.projectakhirpam.ui.viewmodel.PenyediaViewModel
import com.example.projectakhirpam.ui.viewmodel.UpdateUiEvent
import com.example.projectakhirpam.ui.viewmodel.UpdateUiState
import com.example.projectakhirpam.ui.viewmodel.penulis.UpdatePenulisUiEvent
import com.example.projectakhirpam.ui.viewmodel.penulis.UpdatePenulisUiState
import com.example.projectakhirpam.ui.viewmodel.penulis.UpdatePenulisViewModel
import kotlinx.coroutines.launch

object UpdatePenulis : DestinasiNavigasi {
    override val route = "update_penulis"
    override val titleRes = "Update Penulis"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdatePenulisScreen(
    navigateBack: () -> Unit,
    id_penulis: String,
    modifier: Modifier = Modifier,
    viewModel: UpdatePenulisViewModel = viewModel(factory = PenyediaViewModel.Factory)
) {
    val uiState = viewModel.uiState
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    LaunchedEffect(id_penulis) {
        viewModel.getPenulisById(id_penulis)
    }

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomeTopAppBarr(
                UpdatePenulis.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        }
    ) { innerPadding ->
        UpdateBody(
            updateUiState = uiState,
            onPenulisValueChange = viewModel::updateUpdatePenulisState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.updatePenulis()
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
fun UpdateBody(
    updateUiState: UpdatePenulisUiState,
    onPenulisValueChange: (UpdatePenulisUiEvent) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.padding(12.dp)
    ) {
        FormInput(
            updatePenulisUiEvent = updateUiState.updateUiEvent,
            onValueChange = onPenulisValueChange,
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
fun FormInput(
    updatePenulisUiEvent: UpdatePenulisUiEvent,
    modifier: Modifier = Modifier,
    onValueChange: (UpdatePenulisUiEvent) -> Unit = {},
    enabled: Boolean = true
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = updatePenulisUiEvent.id_penulis,
            onValueChange = { onValueChange(updatePenulisUiEvent.copy(id_penulis = it)) },
            label = { Text("Id Penulis") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = updatePenulisUiEvent.nama_penulis,
            onValueChange = { onValueChange(updatePenulisUiEvent.copy(nama_penulis = it)) },
            label = { Text("Nama Penulis") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = updatePenulisUiEvent.biografi,
            onValueChange = { onValueChange(updatePenulisUiEvent.copy(biografi = it)) },
            label = { Text("biografi") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
        OutlinedTextField(
            value = updatePenulisUiEvent.kontak,
            onValueChange = { onValueChange(updatePenulisUiEvent.copy(kontak = it)) },
            label = { Text("kontak") },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )
    }
}