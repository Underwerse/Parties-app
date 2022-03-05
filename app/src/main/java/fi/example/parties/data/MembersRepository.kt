package fi.example.parties.data

import androidx.lifecycle.LiveData
import fi.example.parties.room.entities.PartyMember
import fi.example.parties.room.entities.PartyMemberDao

/**
 * 5.3.2022, Pavel Chernov, opnro. 2110598
 * Repository for PartyMember-object
 */
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