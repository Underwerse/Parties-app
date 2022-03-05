package fi.example.parties.data

import fi.example.parties.room.entities.Image
import fi.example.parties.room.entities.ImageDao

/**
 * 5.3.2022, Pavel Chernov, opnro. 2110598
 * Repository for Image-object
 */
class ImagesRepository(private val imageDao: ImageDao) {

    suspend fun setImage(image: Image) {
        imageDao.setImage(image)
    }
}