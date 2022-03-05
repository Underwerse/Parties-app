package fi.example.parties.data

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import fi.example.parties.room.entities.Image
import fi.example.parties.room.entities.ImageDao

class ImagesRepository(private val imageDao: ImageDao) {

    suspend fun setImage(image: Image) {
        imageDao.setImage(image)
    }
    
    fun getImage(personNumber: Int): LiveData<Bitmap?> {
        return imageDao.getImage(personNumber)
    }
}