package fi.example.parties.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import fi.example.parties.room.DB
import fi.example.parties.room.entities.PartyMember
import fi.example.parties.room.repositories.PartyMemberRepository

class MemberInfoVM(application: Application,
                   memberPersNumber: Int
): AndroidViewModel(application) {
    private val _getMemberByPersNumber: LiveData<PartyMember>
    private val repository: PartyMemberRepository
    val memberByPersNumber: LiveData<PartyMember>
        get() = _getMemberByPersNumber
    
    init {
        val partyMemberDao = DB.getInstance(application).partyMemberDao
        repository = PartyMemberRepository(partyMemberDao)
        _getMemberByPersNumber = repository.getMemberByPersNumber(memberPersNumber)
        Log.d("LOG", "MemberInfoVM _getAllMembersByParty received")
        Log.d("LOG", "Selected member pers. num: $memberPersNumber")
    }
}