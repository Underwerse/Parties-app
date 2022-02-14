package fi.example.parties.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fi.example.parties.MemberOfParliament
import fi.example.parties.ParliamentMembersData

class ParliamentMemberViewModel(selectedParty: String): ViewModel() {
    private var currentParty = selectedParty
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
}