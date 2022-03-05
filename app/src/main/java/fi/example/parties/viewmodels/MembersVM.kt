package fi.example.parties.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import fi.example.parties.data.MembersRepository
import fi.example.parties.room.DB
import fi.example.parties.room.entities.PartyMember

/**
 * ViewModel for Members View
 */
class MembersVM(application: Application,
                selectedParty: String
) : AndroidViewModel(application) {
    private val _getAllMembersByParty: LiveData<List<PartyMember>>
    private val membersRepository: MembersRepository
    
    val membersList: LiveData<List<PartyMember>>
        get() = _getAllMembersByParty

    init {
        val partyMemberDao = DB.getInstance(application).partyMemberDao
        membersRepository = MembersRepository(partyMemberDao)
        // get list of parliament members depending on the selected party
        _getAllMembersByParty = membersRepository.getMembersByParty(selectedParty)
    }
}