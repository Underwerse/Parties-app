package fi.example.parties.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fi.example.parties.room.DB
import fi.example.parties.room.repositories.PartyMemberRepository

class PartiesListVM(application: Application) : AndroidViewModel(application) {
    private val _partiesList = MutableLiveData<List<String>>()
    private val repository: PartyMemberRepository

    val partiesList: LiveData<List<String>>
        get() = _partiesList

    init {
        val partyMemberDao = DB.getInstance(application).partyMemberDao()
        repository = PartyMemberRepository(partyMemberDao)
        _partiesList.value = repository.parties.value
    }
}