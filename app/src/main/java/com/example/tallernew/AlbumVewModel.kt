package com.example.tallernew

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.tallernew.Almacenamiento.Album
import com.example.tallernew.Almacenamiento.AlbumDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AlbumViewModel(application: Application) : AndroidViewModel(application) {
    private val albumDatabase = AlbumDatabase(application)

    private val _albums = MutableStateFlow<List<Album>>(emptyList())
    val albums: StateFlow<List<Album>> get() = _albums

    init {
        loadAlbums()
    }

    private fun loadAlbums() {
        viewModelScope.launch {
            _albums.value = albumDatabase.getAllAlbums()
        }
    }

    fun addAlbum(album: Album) {
        viewModelScope.launch {
            albumDatabase.insertAlbum(album)
            loadAlbums()
        }
    }

    fun updateAlbum(id: Int, newDescription: String) {
        viewModelScope.launch {
            albumDatabase.updateAlbum(id, newDescription)
            loadAlbums()
        }
    }

    fun deleteAlbum(title: String) {
        viewModelScope.launch {
            albumDatabase.deleteAlbum(title)
            loadAlbums()
        }
    }
}