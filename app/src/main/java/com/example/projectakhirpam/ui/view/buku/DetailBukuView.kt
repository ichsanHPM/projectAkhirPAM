package com.example.projectakhirpam.ui.view.buku

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
import com.example.projectakhirpam.ui.viewmodel.PenyediaViewModel
import kotlinx.coroutines.launch

object DestinasiDetail : DestinasiNavigasi {
    override val route = "detail_bk"
    override val titleRes = "Detail Buku"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailBukuScreen(
    navigateBack: () -> Unit,
    id_buku: String,
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel = viewModel(factory = PenyediaViewModel.Factory),
    navController: NavHostController
) {
    val uiState by viewModel.detailUiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CustomeTopAppBarr(
                title = DestinasiDetail.titleRes,
                canNavigateBack = true,
                scrollBehavior = scrollBehavior,
                navigateUp = navigateBack
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("update_bk/$id_buku")
                },
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(18.dp)
            ) {
                Icon(imageVector = Icons.Default.Edit, contentDescription = "Update Buku")
            }
        }
    ) { innerPadding ->
        DetailBody(
            detailUiState = uiState,
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
fun DetailBody(
    detailUiState: DetailUiState,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (detailUiState) {
        is DetailUiState.Loading -> {
            CircularProgressIndicator(modifier = modifier.fillMaxSize())
        }
        is DetailUiState.Error -> {
            Text(
                text = detailUiState.message,
                color = Color.Red,
                modifier = modifier.fillMaxSize().wrapContentSize(Alignment.Center)
            )
        }
        is DetailUiState.Success -> {
            val buku = detailUiState.buku
            Column(
                verticalArrangement = Arrangement.spacedBy(18.dp),
                modifier = Modifier.padding(12.dp)
            ) {
                ComponentDetailBk(judul = "Nama_buku", isinya = buku.nama_buku)
                ComponentDetailBk(judul = "Nama_buku", isinya = buku.nama_buku)
                ComponentDetailBk(judul = "Id_buku", isinya = buku.id_buku)
                ComponentDetailBk(judul = "Deskripsi_buku", isinya = buku.deskripsi_buku)
                ComponentDetailBk(judul = "Status_buku", isinya = buku.status_buku)
                ComponentDetailBk(judul = "Tanggal_terbit", isinya = buku.tanggal_terbit)
                ComponentDetailBk(judul = "Id_kategori", isinya = buku.id_kategori)
                ComponentDetailBk(judul = "Id_penulis", isinya = buku.id_penulis)
                ComponentDetailBk(judul = "Id_penerbit", isinya = buku.id_penerbit)
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
fun ComponentDetailBk(
    modifier: Modifier = Modifier,
    judul: String,
    isinya: String,
){
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
