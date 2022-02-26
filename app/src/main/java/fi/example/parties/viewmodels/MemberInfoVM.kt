package fi.example.parties.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import fi.example.parties.room.DB
import fi.example.parties.room.entities.PartyMember
import fi.example.parties.data.Repository

class MemberInfoVM(application: Application,
                   memberPersNumber: Int
): AndroidViewModel(application) {
    private val _getMemberByPersNumber: LiveData<PartyMember>
    private val repository: Repository
    val memberByPersNumber: LiveData<PartyMember>
        get() = _getMemberByPersNumber
    
    init {
        val partyMemberDao = DB.getInstance(application).partyMemberDao
        repository = Repository(partyMemberDao)
        _getMemberByPersNumber = repository.getMemberByPersNumber(memberPersNumber)
        Log.d("LOG", "MemberInfoVM _getAllMembersByParty received")
    }
}