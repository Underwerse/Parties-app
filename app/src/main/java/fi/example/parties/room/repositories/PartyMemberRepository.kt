package fi.example.parties.room.repositories

import androidx.lifecycle.LiveData
import fi.example.parties.room.DB
import fi.example.parties.room.entities.PartyMember
import fi.example.parties.room.entities.PartyMemberDao

class PartyMemberRepository(private val partyMemberDao: PartyMemberDao) {

    val getAllMembers: LiveData<List<PartyMember>> = partyMemberDao.getAllMembers()

    suspend fun addMember(member: PartyMember) {
        partyMemberDao.addMember(member)
    }

    /*suspend fun addPartyMember(
        personNumber: Int,
        seatNumber: Int,
        last: String,
        first: String,
        party: String,
        minister: Boolean,
        picture: String,
        twitter: String,
        bornYear: Int,
        constituency: String
    ) {
        DB.getInstance()
            .partyMemberDao
            .insertOrUpdate(PartyMember(personNumber, seatNumber, last, first, party, minister, picture, twitter, bornYear, constituency))
    }*/

    suspend fun deleteAll() {
        DB.getInstance()
            .partyMemberDao()
            .deleteAll()
    }

    val partyMembers = DB.getInstance().partyMemberDao().getAllMembers()

    val parties = DB.getInstance().partyMemberDao().getParties()
}