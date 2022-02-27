package fi.example.parties.data

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import fi.example.parties.MainActivity.Companion.appContext
import fi.example.parties.databinding.FragmentFillDbBinding
import fi.example.parties.room.DB
import fi.example.parties.room.entities.PartyMember
import fi.example.parties.room.entities.PartyMemberDao
import fi.example.parties.viewmodels.FillDbVM

class MembersRepository(private val partyMemberDao: PartyMemberDao) {
    val allPartiesFromDb = partyMemberDao.getParties()
    
    suspend fun addMember(member: PartyMember) {
        partyMemberDao.addMember(member)
    }

    suspend fun deleteAll() {
        DB.getInstance()
            .partyMemberDao
            .deleteAll()
    }
    
    fun getPartyMembersByParty(selectedParty: String): LiveData<List<PartyMember>> {
        Log.d("LOG", "Repository: getPartyMemberByParty call")
        return partyMemberDao.getPartyMembersByParty(selectedParty)
    }
    
    fun getMemberByPersNumber(personNumber: Int): LiveData<PartyMember> {
        Log.d("LOG", "Repository: getMemberByPersNumber call")
        return partyMemberDao.getMemberByPersNumber(personNumber)
    }
}