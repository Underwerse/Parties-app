package fi.example.parties.data

import android.util.Log
import androidx.lifecycle.LiveData
import fi.example.parties.room.DB
import fi.example.parties.room.entities.MemberRating
import fi.example.parties.room.entities.MemberRatingDao
import fi.example.parties.room.entities.PartyMember
import fi.example.parties.room.entities.PartyMemberDao

class RatingsRepository(private val memberRatingDao: MemberRatingDao) {

    val allRatingsFromDb = memberRatingDao.getAllRatings()

    suspend fun setRating(rating: MemberRating) {
        memberRatingDao.setRating(rating)
    }

    /*suspend fun deleteAll() {
        DB.getInstance()
            .partyMemberDao
            .deleteAll()
    }*/
    
    fun getRating(personNumber: Int): LiveData<Int> {
        Log.d("LOG", "Repository: getMemberByPersNumber call")
        return memberRatingDao.getRating(personNumber)
    }
}