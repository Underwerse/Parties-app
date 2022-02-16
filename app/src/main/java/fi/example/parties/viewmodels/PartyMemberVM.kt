package fi.example.parties.viewmodels

import android.app.Application
import androidx.lifecycle.*
import fi.example.parties.MemberOfParliament
import fi.example.parties.ParliamentMembersData
import fi.example.parties.room.DB
import fi.example.parties.room.entities.PartyMember
import fi.example.parties.room.repositories.PartyMemberRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PartyMemberVM(application: Application) : AndroidViewModel(application) {

    private val getAllMembers: LiveData<List<PartyMember>>
    private val repository: PartyMemberRepository

    init {
        val partyMemberDao = DB.getInstance(application).partyMemberDao()
        repository = PartyMemberRepository(partyMemberDao)
        getAllMembers = repository.getAllMembers
    }

    fun addMember(member: PartyMember) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMember(member)
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }

    /*private var currentParty = selectedParty
    private val parliamentMembers = ParliamentMembersData.members
    private var currentMember = MutableLiveData<MemberOfParliament>()

    init {
        setMember()
    }

    fun newMember(): LiveData<MemberOfParliament> {
        return currentMember
    }

    fun setMember() {
        currentMember.value = parliamentMembers.filter { it.party == currentParty }.random()
    }

    fun cleanDb() {
        viewModelScope.launch {
            PartyMemberRepository.deleteAll()
        }
    }

    fun addMember(member: PartyMember) {
        viewModelScope.launch {
            PartyMemberRepository.addPartyMember(
                member.personNumber,
                member.seatNumber,
                member.last,
                member.first,
                member.party,
                member.minister,
                member.picture,
                member.twitter,
                member.bornYear,
                member.constituency
            )
        }
    }*/
}