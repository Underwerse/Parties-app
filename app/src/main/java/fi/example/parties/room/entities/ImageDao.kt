package fi.example.parties.room.entities

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setImage(Image: Image)

    @Query("select image from party_members_images_table where personNumber = :key")
    suspend fun getImage(key: Int): Bitmap
    
    @Query("SELECT EXISTS (SELECT * FROM party_members_images_table WHERE personNumber = :key)")
    suspend fun isExists(key: Int): Boolean
}