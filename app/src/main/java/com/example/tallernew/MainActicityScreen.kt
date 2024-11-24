package com.example.tallernew

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.tallernew.Almacenamiento.Album

@Composable
fun MainActivityScreen(viewModel: AlbumViewModel = viewModel()) {
    var selectedAlbum by remember { mutableStateOf<Album?>(null) }
    var isAddingAlbum by remember { mutableStateOf(false) }
    val albums by viewModel.albums.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        Button(
            onClick = { isAddingAlbum = true },
            modifier = Modifier.padding(16.dp)
        ) {
            Text("Añadir Álbum")
        }

        Row(modifier = Modifier.fillMaxSize()) {
            AlbumList(albums = albums, onAlbumSelected = { selectedAlbum = it }, modifier = Modifier.weight(1f))
            AlbumDetails(album = selectedAlbum, modifier = Modifier.weight(1f))
        }
    }

    if (isAddingAlbum) {
        AddAlbumDialog(onDismiss = { isAddingAlbum = false }) { album ->
            viewModel.addAlbum(album)
            isAddingAlbum = false
        }
    }
}

@Composable
fun AlbumList(albums: List<Album>, onAlbumSelected: (Album) -> Unit, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier.padding(8.dp)) {
        items(albums) { album ->
            ListItem(
                headlineContent = { Text(album.title) },
                supportingContent = { Text(album.artist) },
                modifier = Modifier.clickable { onAlbumSelected(album) }
            )
        }
    }
}

@Composable
fun AlbumDetails(album: Album?, modifier: Modifier = Modifier) {
    Surface(modifier = modifier.padding(16.dp)) {
        album?.let {
            Column {
                Text(text = "Título: ${it.title}", style = MaterialTheme.typography.titleLarge)
                Text(text = "Artista: ${it.artist}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Fecha de Lanzamiento: ${it.releaseDate}", style = MaterialTheme.typography.bodySmall)
            }
        } ?: Text("Selecciona un álbum para ver detalles", style = MaterialTheme.typography.bodySmall)
    }
}

@Composable
fun AddAlbumDialog(onDismiss: () -> Unit, onAddAlbum: (Album) -> Unit) {
    var title by remember { mutableStateOf("") }
    var artist by remember { mutableStateOf("") }
    var releaseDate by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Añadir Nuevo Álbum") },
        text = {
            Column {
                OutlinedTextField(value = title, onValueChange = { title = it }, label = { Text("Título") })
                OutlinedTextField(value = artist, onValueChange = { artist = it }, label = { Text("Artista") })
                OutlinedTextField(value = releaseDate, onValueChange = { releaseDate = it }, label = { Text("Fecha de Lanzamiento") })
            }
        },
        confirmButton = {
            Button(onClick = {
                if (title.isNotBlank() && artist.isNotBlank() && releaseDate.isNotBlank()) {
                    onAddAlbum(Album(title = title, artist = artist, releaseDate = releaseDate))
                }
            }) {
                Text("Añadir")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) { Text("Cancelar") }
        }
    )
}