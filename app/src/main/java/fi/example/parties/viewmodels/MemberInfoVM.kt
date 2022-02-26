package fi.example.parties.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import fi.example.parties.room.DB
import fi.example.parties.room.entities.PartyMember
import fi.example.parties.data.MembersRepository
import fi.example.parties.data.RatingsRepository
import kotlinx.coroutines.launch

class MemberInfoVM(application: Application,
                   memberPersNumber: Int
): AndroidViewModel(application) {
    private val _getMemberByPersNumber: LiveData<PartyMember>
    private val _getRatingByPersNumber: LiveData<Int>
    private val membersRepository: MembersRepository
    private val ratingsRepository: RatingsRepository
    val memberByPersNumber: LiveData<PartyMember>
        get() = _getMemberByPersNumber
    val getRatingByPersNumber: LiveData<Int>
        get() = _getRatingByPersNumber
    
    init {
        val partyMemberDao = DB.getInstance(application).partyMemberDao
        val memberRatingDao = DB.getInstance(application).memberRatingDao
        membersRepository = MembersRepository(partyMemberDao)
        ratingsRepository = RatingsRepository(memberRatingDao)
        _getMemberByPersNumber = membersRepository.getMemberByPersNumber(memberPersNumber)
        _getRatingByPersNumber = ratingsRepository.getRating(memberPersNumber)
        Log.d("LOG", "MemberInfoVM _getAllMembersByParty received")
    }
    
    fun onSetRating(rating: Int) {
        viewModelScope.launch {
        }
        
    }
}