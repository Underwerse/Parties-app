package fi.example.tiistai2501.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import fi.example.tiistai2501.MemberOfParliament
import fi.example.tiistai2501.ParliamentMembersData

class ParliamentMemberViewModel(selectedParty: String) : ViewModel() {
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