package fi.example.parties.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import fi.example.parties.data.MembersRepository
import fi.example.parties.room.DB

/**
 * 5.3.2022, Pavel Chernov, opnro. 2110598
 * ViewModel for Parties View
 */
class PartiesVM(application: Application
) : AndroidViewModel(application) {
    private val _allPartiesFromDb: LiveData<List<String>>
    private val membersRepository: MembersRepository
    val allPartiesFromDb: LiveData<List<String>>
        get() = _allPartiesFromDb

    init {
        val partyMemberDao = DB.getInstance(application).partyMemberDao
        membersRepository = MembersRepository(partyMemberDao)
        // get all parties list from the repository
        _allPartiesFromDb = membersRepository.allPartiesFromDb
    }
}