package fi.example.parties.viewmodels

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import fi.example.parties.data.MembersRepository
import fi.example.parties.data.RatingsRepository
import fi.example.parties.room.DB
import fi.example.parties.room.entities.MemberRating
import fi.example.parties.room.entities.PartyMember
import kotlinx.coroutines.launch

class MemberInfoVM(application: Application,
                   memberPersNumber: Int
): AndroidViewModel(application) {
    private val personNumber: Int
    private val _getMemberByPersNumber: LiveData<PartyMember>
    private val _getRatingByPersNumber: LiveData<Int>
    private val _getRatingObjByPersNumber: LiveData<MemberRating>
    private val membersRepository: MembersRepository
    private val ratingsRepository: RatingsRepository
    
    val memberByPersNumber: LiveData<PartyMember>
        get() = _getMemberByPersNumber
    val memberRating: LiveData<Int>
        get() = _getRatingByPersNumber
    val memberRatingObj: LiveData<MemberRating>
        get() = _getRatingObjByPersNumber
    
    init {
        val partyMemberDao = DB.getInstance(application).partyMemberDao
        val memberRatingDao = DB.getInstance(application).memberRatingDao
        personNumber = memberPersNumber
        
        membersRepository = MembersRepository(partyMemberDao)
        ratingsRepository = RatingsRepository(memberRatingDao)
        _getMemberByPersNumber = membersRepository.getMemberByPersNumber(memberPersNumber)
        _getRatingByPersNumber = ratingsRepository.getRating(memberPersNumber)
        _getRatingObjByPersNumber = ratingsRepository.getRatingObj(memberPersNumber)
        
        Log.d("LOG", "RatingObj: ${_getRatingObjByPersNumber.value}")
        Log.d("LOG", "MemberInfoVM _getAllMembersByParty received")
    }
    
    fun onSetRating(rating: Int) {
            val ratingObj = MemberRating(personNumber, rating, "")
            viewModelScope.launch {
                ratingsRepository.setRating(ratingObj)
        }
    }
    
    fun onSetNote(note: String) {
        val ratingObj = memberRating.value?.let {
            MemberRating(
                personNumber = personNumber,
                rating = it,
                note = note)
        }
        viewModelScope.launch {
            if (ratingObj != null) {
                ratingsRepository.setRating(ratingObj)
            }
        }
    }
}