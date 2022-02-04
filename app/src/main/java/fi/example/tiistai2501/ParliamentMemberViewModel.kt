package fi.example.tiistai2501

import android.util.Log
import androidx.lifecycle.ViewModel

class ParliamentMemberViewModel : ViewModel() {
    var currentParty: String = ""
        set(value) {
            field = value
            Log.i("ParliamentMemberViewModel", "Party's name set to '${currentParty}'!")
            Log.i("ParliamentMemberViewModel", "Current member: ${currentMember.first}")
        }
    private val parliamentMembers = ParliamentMembersData.members
    private val partiesList = parliamentMembers.map { it.party }.toSet().toList()
    var currentMember: MemberOfParliament = getMember(currentParty)

    init {
        Log.i("ParliamentMemberViewModel", "ParliamentMemberViewModel created!")
        Log.i("ParliamentMemberViewModel", "Party's name '${currentParty}' received!")
    }

    override fun onCleared() {
        super.onCleared()
        Log.i("ParliamentMemberViewModel", "ParliamentMemberViewModel destroyed!")
    }

    fun getMember (partyName: String): MemberOfParliament {
        if (partiesList.contains(partyName))
            currentMember = parliamentMembers.filter { it.party == partyName }.random()
        else currentMember = parliamentMembers.random()

        return currentMember
    }
}