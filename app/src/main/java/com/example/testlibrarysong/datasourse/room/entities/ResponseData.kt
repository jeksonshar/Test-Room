package com.example.testlibrarysong.datasourse.room.entities

import com.google.gson.annotations.SerializedName

data class ResponseData(
    @SerializedName("users") val users: List<UserEntity>,
    @SerializedName("music_libraries") val playlists: List<PlayListEntity>,
    @SerializedName("songs") val songs: List<SongEntity>,
    @SerializedName("music_libraries_to_users") val usersToPlaylists: List<UserPlaylistCrossReference>,
    @SerializedName("songs_to_music_libraries") val playlistsToSongs: List<PlaylistSongCrossReference>
)
