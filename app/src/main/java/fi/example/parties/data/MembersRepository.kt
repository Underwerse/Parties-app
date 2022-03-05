package fi.example.parties.data

import androidx.lifecycle.LiveData
import fi.example.parties.room.entities.PartyMember
import fi.example.parties.room.entities.PartyMemberDao

class MembersRepository(private val partyMemberDao: PartyMemberDao) {
    val allPartiesFromDb = partyMemberDao.getParties()
    
    suspend fun addMember(member: PartyMember) {
        partyMemberDao.addMember(member)
    }
    
    fun getMembersByParty(partyName: String): LiveData<List<PartyMember>> {
        return partyMemberDao.getPartyMembersByParty(partyName)
    }
    
    fun getMemberByPersNumber(personNumber: Int): LiveData<PartyMember> {
        return partyMemberDao.getMemberByPersNumber(personNumber)
    }
    
    suspend fun deleteAll() {
        partyMemberDao.deleteAll()
    }
}