package com.example.testlibrarysong.datasourse.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    @SerializedName("id") val userId: Int = 0,
    @SerializedName("firstname") val userFirstName: String = "no",
    @SerializedName("lastname") val userLastName: String = "no",
    @SerializedName("email") val userEmail: String = ""
)

@Entity(tableName = "songs")
data class SongEntity(
    @PrimaryKey
    @SerializedName("id") val songId: Int = 0,
    @SerializedName("name") val songName: String = "",
    @SerializedName("singer_first_name") val singerFirstName: String = "",
    @SerializedName("singer_last_name") val singerLastName: String = "",
    @SerializedName("description") val songDescription: String = ""
)

@Entity(tableName = "play_lists")
data class PlayListEntity(
    @PrimaryKey
    @SerializedName("id") val playlistId: Int = 0,
    @SerializedName("name") val playlistName: String = "",
    @SerializedName("description") val playlistDescription: String = ""
)


@Entity(primaryKeys = ["userId", "playlistId"])
data class UserPlaylistCrossReference(
    @SerializedName("user_id") val userId: Int,
    @SerializedName("music_library_id") val playlistId: Int
)

@Entity(primaryKeys = ["playlistId", "songId"])
data class PlaylistSongCrossReference(
    @SerializedName("music_library_id") val playlistId: Int,
    @SerializedName("song_id") val songId: Int
)
