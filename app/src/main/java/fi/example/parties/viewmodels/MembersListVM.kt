package fi.example.parties.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import fi.example.parties.room.DB
import fi.example.parties.room.repositories.PartyMemberRepository

class MembersListVM(application: Application,
                    selectedParty: String
) : AndroidViewModel(application) {
    private val _getAllMembersByParty: LiveData<List<String>>
    private val repository: PartyMemberRepository
    val membersList: LiveData<List<String>>
        get() = _getAllMembersByParty

    init {
        val partyMemberDao = DB.getInstance(application).partyMemberDao
        repository = PartyMemberRepository(partyMemberDao)
        _getAllMembersByParty = repository.getPartyMemberByParty(selectedParty)
        Log.d("LOG", "MembersListVM _getAllMembersByParty received")
        Log.d("LOG", "Selected party: $selectedParty")
    }
}