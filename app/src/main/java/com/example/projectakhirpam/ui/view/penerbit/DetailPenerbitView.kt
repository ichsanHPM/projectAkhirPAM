package com.example.projectakhirpam.ui.view.penerbit

import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.projectakhirpam.ui.customwidget.CustomeTopAppBarr
import com.example.projectakhirpam.ui.navigasi.DestinasiNavigasi
import com.example.projectakhirpam.ui.viewmodel.DetailUiState
import com.example.projectakhirpam.ui.viewmodel.DetailViewModel
import com.example.projectakhirpam.ui.viewmodel.Penerbit.DetailPenerbitUiState
import com.example.projectakhirpam.ui.viewmodel.Penerbit.DetailPenerbitViewModel
import com.example.projectakhirpam.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch


object DestinasiDetailPenerbit : DestinasiNavigasi {
    override val route = "detail_penerbit"
    override val titleRes = "Detail Penerbit"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailPenerbitScreen(
    navigateBack: () -> Unit,
    id_penerbit: String,
    modifier: Modifier = Modifier,
    viewModel: DetailPenerbitViewModel = viewModel(factory = PenyediaViewModel.Factory),
    navController: NavHostController
) {
    val uiState by viewModel.detailUiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomeTopAppBarr(
                title = DestinasiDetailPenerbit.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("update_penerbit/$id_penerbit")
                },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Update Penerbit")
            }
        }
    ) { innerPadding ->
        DetailBodyPenerbit(
            detailPenerbitUiState = uiState,
            onDeleteClick = {
                coroutineScope.launch {
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
fun DetailBodyPenerbit(
    detailPenerbitUiState: DetailPenerbitUiState,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (detailPenerbitUiState) {
        is DetailPenerbitUiState.Loading -> {
            CircularProgressIndicator(modifier = modifier.fillMaxSize())
        }
        is DetailPenerbitUiState.Error -> {
            Text(
                text = detailPenerbitUiState.message,
                color = Color.Red,
                modifier = modifier.fillMaxSize().wrapContentSize(Alignment.Center)
            )
        }
        is DetailPenerbitUiState.Success -> {
            val penerbit = detailPenerbitUiState.penerbit
            Column(
                verticalArrangement = Arrangement.spacedBy(18.dp),
                modifier = Modifier.padding(12.dp)
            ) {
                ComponentDetailPenerbit(judul = "Id Penerbit", isinya = penerbit.id_penerbit)
                ComponentDetailPenerbit(judul = "Nama Penerbit", isinya = penerbit.nama_penerbit)
                ComponentDetailPenerbit(judul = "Alamat Penerbit", isinya = penerbit.alamat_penerbit)
                ComponentDetailPenerbit(judul = "Kontak Penerbit", isinya = penerbit.telepon_penerbit)
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = onDeleteClick,
                    shape = MaterialTheme.shapes.small,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Hapus")
                }
            }
        }
    }
}

@Composable
fun ComponentDetailPenerbit(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "$judul : ",
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Gray
        )

        Text(
            text = isinya,
            fontSize = 19.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
