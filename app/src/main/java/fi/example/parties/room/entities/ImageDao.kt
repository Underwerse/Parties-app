package fi.example.parties.room.entities

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ImageDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setImage(Image: Image)
    
    @Query("update party_members_images_table set image = :image where personNumber = :persNumber ")
    suspend fun updateImage(persNumber: Int, image: Bitmap)

    @Query("select image from party_members_images_table where personNumber = :key")
    fun getImage(key: Int): LiveData<Bitmap?>
    
    @Query("select * from party_members_images_table where personNumber = :key")
    fun getImageObj(key: Int): LiveData<Image>
}