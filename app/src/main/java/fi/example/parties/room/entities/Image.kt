package fi.example.parties.room.entities

import android.graphics.Bitmap
import androidx.room.*

/**
 * 5.3.2022, Pavel Chernov, opnro. 2110598
 * Entity of Image-object for Room-DB
 */
@Entity(tableName = "party_members_images_table")
data class Image (
    @PrimaryKey
    val personNumber: Int,
    val image: Bitmap
)
