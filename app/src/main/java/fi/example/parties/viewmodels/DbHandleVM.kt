package fi.example.parties.viewmodels

import android.app.Application
import androidx.lifecycle.*
import fi.example.parties.room.data.DB
import fi.example.parties.room.entities.PartyMember
import fi.example.parties.room.data.Repository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DbHandleVM(application: Application) : AndroidViewModel(application) {
    
    private val getAllMembers: LiveData<List<PartyMember>>
    private val repository: Repository
    
    init {
        val partyMemberDao = DB.getInstance(application).partyMemberDao
        repository = Repository(partyMemberDao)
        getAllMembers = repository.getAllMembers
    }
    
    fun addMember(member: PartyMember) {
        GlobalScope.launch {
            repository.addMember(member)
        }
    }
    
    fun deleteAll() {
        GlobalScope.launch {
            repository.deleteAll()
        }
    }
}