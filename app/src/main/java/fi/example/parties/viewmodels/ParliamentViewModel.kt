package fi.example.parties.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import fi.example.parties.database.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ParliamentViewModel(application: Application): AndroidViewModel(application) {
    private val readAllData: LiveData<List<ParliamentMember>>
    private val repository: ParliamentRepository

    init {
        val parliamentMemberDao = ParliamentDB.getInstance(application).parliamentMemberDao
        repository = ParliamentRepository(parliamentMemberDao)
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
    }
}