package fi.example.parties.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import fi.example.parties.room.DB
import fi.example.parties.room.entities.PartyMember
import fi.example.parties.room.repositories.PartyMemberRepository

class MembersVM(application: Application,
                selectedParty: String
) : AndroidViewModel(application) {
    private val _getAllMembersByParty: LiveData<List<PartyMember>>
    private val repository: PartyMemberRepository
    
    val membersList: LiveData<List<PartyMember>>
        get() = _getAllMembersByParty

    init {
        val partyMemberDao = DB.getInstance(application).partyMemberDao
        repository = PartyMemberRepository(partyMemberDao)
        _getAllMembersByParty = repository.getPartyMembersByParty(selectedParty)
        Log.d("LOG", "MembersListVM _getAllMembersByParty received")
    }
}