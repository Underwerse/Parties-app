package fi.example.parties.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import fi.example.parties.data.ImagesRepository
import fi.example.parties.data.MembersRepository
import fi.example.parties.data.RatingsRepository
import fi.example.parties.room.DB
import fi.example.parties.room.entities.MemberRating
import fi.example.parties.room.entities.PartyMember
import kotlinx.coroutines.launch

/**
 * 5.3.2022, Pavel Chernov, opnro. 2110598
 * ViewModel for MemberInfo View
 */
class MemberInfoVM(application: Application,
                   memberPersNumber: Int
): AndroidViewModel(application) {
    private val personNumber: Int
    private val _imageUrl: String
    private val _getMember: LiveData<PartyMember>
    private val _getRating: LiveData<Int>
    private val _getRatingObj: LiveData<MemberRating>
    private val membersRepository: MembersRepository
    private val ratingsRepository: RatingsRepository
    private val imagesRepository: ImagesRepository
    
    val memberByPersNumber: LiveData<PartyMember>
        get() = _getMember
    val memberRating: LiveData<Int>
        get() = _getRating
    val memberRatingObj: LiveData<MemberRating>
        get() = _getRatingObj
    
    init {
        val partyMemberDao = DB.getInstance(application).partyMemberDao
        val memberRatingDao = DB.getInstance(application).memberRatingDao
        val imageDao = DB.getInstance(application).imageDao
        membersRepository = MembersRepository(partyMemberDao)
        ratingsRepository = RatingsRepository(memberRatingDao)
        imagesRepository = ImagesRepository(imageDao)
        
        personNumber = memberPersNumber // variable for using in separate methods below
        _getMember = membersRepository.getMemberByPersNumber(memberPersNumber)
        _imageUrl = _getMember.value?.picture ?: ""
        _getRating = ratingsRepository.getRating(memberPersNumber)
        _getRatingObj = ratingsRepository.getRatingObj(memberPersNumber)
    }
    
    /**
     * Sets (updates) rating in the DB
     */
    fun processRating(ratingObject: MemberRating) {
        viewModelScope.launch {
            ratingsRepository.setRating(ratingObject)
        }
    }
    
    /**
     * Updates rating in DB, saving current note
     */
    fun onSetRating(rating: Int) {
        val currentNote = (memberRatingObj.value)?.note ?: "No notes"
        val ratingObj = MemberRating(
            personNumber,
            rating,
            currentNote)
        processRating(ratingObj)
    }
    
    /**
     * Puts note into DB (by btn click in View), saving current rating
     */
    fun onSetNote(note: String) {
        val currentRating = memberRatingObj.value?.rating ?: 0
        val ratingObj = MemberRating(
                personNumber,
                currentRating,
                note)
        processRating(ratingObj)
    }
}