package fi.example.parties.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import fi.example.parties.room.DB
import fi.example.parties.room.repositories.PartyMemberRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PartiesListVM(application: Application
) : AndroidViewModel(application) {
    private val _getAllParties: LiveData<List<String>>
    private val repository: PartyMemberRepository
    val partiesList: LiveData<List<String>>
        get() = _getAllParties

    init {
        val partyMemberDao = DB.getInstance(application).partyMemberDao
        repository = PartyMemberRepository(partyMemberDao)
        _getAllParties = repository.getAllParties
        Log.d("LOG", "PartiesListVM: _getAllParties received")
    }
}