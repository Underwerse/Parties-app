package fi.example.parties.room.repositories

import android.util.Log
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
            .partyMemberDao
            .deleteAll()
    }
    
    fun getPartyMemberByParty(selectedParty: String): LiveData<List<String>> {
        Log.d("LOG", "Repository: getPartyMemberByParty run")
        return partyMemberDao.getPartyMemberByParty(selectedParty)
    }
    
}