package fi.example.parties.room.entities

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setImage(Image: Image)

    @Query("select image from party_members_images_table where personNumber = :key")
    fun getImage(key: Int): LiveData<Bitmap?>
}