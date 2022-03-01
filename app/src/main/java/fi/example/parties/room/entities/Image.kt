package fi.example.parties.room.entities

import android.graphics.Bitmap
import androidx.room.*

@Entity(tableName = "party_members_images_table")
data class Image (
    @PrimaryKey
    val personNumber: Int,
    val image: Bitmap
)
