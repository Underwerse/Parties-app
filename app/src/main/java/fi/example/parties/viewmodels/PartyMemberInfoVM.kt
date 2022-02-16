package fi.example.parties.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import fi.example.parties.room.*
import fi.example.parties.room.entities.PartyMember
import fi.example.parties.room.repositories.PartyMemberRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PartyMemberInfoVM(application: Application): AndroidViewModel(application) {
    /*private val readAllData: LiveData<List<PartyMember>>
    private val repository: PartyMemberRepository

    init {
        val partyMemberDao = DB.getInstance(application).partyMemberDao
        repository = PartyMemberRepository(partyMemberDao)
        readAllData = repository.readAllData
    }

    fun addMember(parliamentMember: ParliamentMember) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMember(parliamentMember)
        }
    }

    fun deleteAll() {
        viewModelScope.launch {
            repository.deleteAll()
        }
    }*/
}