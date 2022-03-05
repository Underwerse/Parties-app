package fi.example.parties.data

import androidx.lifecycle.LiveData
import fi.example.parties.room.entities.MemberRating
import fi.example.parties.room.entities.MemberRatingDao

/**
 * 5.3.2022, Pavel Chernov, opnro. 2110598
 * Repository for Rating-object
 */
class RatingsRepository(private val memberRatingDao: MemberRatingDao) {

    suspend fun setRating(rating: MemberRating) {
        memberRatingDao.setRating(rating)
    }
    
    fun getRating(personNumber: Int): LiveData<Int> {
        return memberRatingDao.getRating(personNumber)
    }
    
    fun getRatingObj(personNumber: Int): LiveData<MemberRating> {
        return memberRatingDao.getRatingObj(personNumber)
    }
}