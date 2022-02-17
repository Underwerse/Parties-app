package fi.example.parties.room.repositories

import androidx.lifecycle.LiveData
import fi.example.parties.room.DB
import fi.example.parties.room.entities.PartyMember
import fi.example.parties.room.entities.PartyMemberDao

class PartyMemberRepository(private val partyMemberDao: PartyMemberDao) {

    val getAllMembers: LiveData<List<PartyMember>> = partyMemberDao.getAllMembers()
    val getAllParties: LiveData<List<String>> = partyMemberDao.getParties()

    suspend fun addMember(member: PartyMember) {
        partyMemberDao.addMember(member)
    }

    suspend fun deleteAll() {
        DB.getInstance()
            .partyMemberDao()
            .deleteAll()
    }

}