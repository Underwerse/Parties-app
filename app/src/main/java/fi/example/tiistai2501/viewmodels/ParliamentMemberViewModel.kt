package fi.example.tiistai2501.viewmodels

import androidx.lifecycle.ViewModel
import fi.example.tiistai2501.MemberOfParliament
import fi.example.tiistai2501.ParliamentMembersData

class ParliamentMemberViewModel(selectedParty: String) : ViewModel() {
    private var currentParty = selectedParty
    private val parliamentMembers = ParliamentMembersData.members
    private val partiesList = parliamentMembers.map { it.party }.toSet().toList()

    fun getMember(): MemberOfParliament {
        if (partiesList.contains(currentParty))
            return parliamentMembers.filter { it.party == currentParty }.random()
        else return parliamentMembers.random()
    }
}