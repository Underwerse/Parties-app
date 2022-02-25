package fi.example.parties.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import fi.example.parties.room.data.DB
import fi.example.parties.room.data.Repository

class PartiesVM(application: Application
) : AndroidViewModel(application) {
    private val _getAllParties: LiveData<List<String>>
    private val repository: Repository
    val partiesList: LiveData<List<String>>
        get() = _getAllParties

    init {
        val partyMemberDao = DB.getInstance(application).partyMemberDao
        repository = Repository(partyMemberDao)
        _getAllParties = repository.getAllParties
        Log.d("LOG", "PartiesListVM: _getAllParties received")
    }
}