package fi.example.parties.data

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.LiveData
import fi.example.parties.room.DB
import fi.example.parties.room.entities.*

class ImagesRepository(private val imageDao: ImageDao) {

    suspend fun setImage(image: Image) {
        imageDao.setImage(image)
    }
    
    fun getImage(personNumber: Int): LiveData<Bitmap?> {
        return imageDao.getImage(personNumber)
    }
    
    fun getImageObj(personNumber: Int): LiveData<Image> {
        return imageDao.getImageObj(personNumber)
    }
}