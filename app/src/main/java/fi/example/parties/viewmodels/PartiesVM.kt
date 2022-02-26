package fi.example.parties.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import fi.example.parties.room.DB
import fi.example.parties.data.MembersRepository

class PartiesVM(application: Application
) : AndroidViewModel(application) {
    private val _allPartiesFromDb: LiveData<List<String>>
    private val membersRepository: MembersRepository
    val allPartiesFromDb: LiveData<List<String>>
        get() = _allPartiesFromDb

    init {
        Log.i("LOG", "PartiesVM created")
        val partyMemberDao = DB.getInstance(application).partyMemberDao
        membersRepository = MembersRepository(partyMemberDao)
        _allPartiesFromDb = membersRepository.allPartiesFromDb
        Log.d("LOG", "PartiesVM: _allPartiesFromDb received")
    }
    
    override fun onCleared() {
        super.onCleared()
        Log.i("LOG", "PartiesVM destroyed")
    }
}