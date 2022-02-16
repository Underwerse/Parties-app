package fi.example.parties.database

import androidx.lifecycle.LiveData

class ParliamentRepository(private val parliamentMemberDao: ParliamentMemberDao) {
    val readAllData: LiveData<List<ParliamentMember>> = parliamentMemberDao.getAllMembers()

    suspend fun addMember(
        personNumber: ParliamentMember,
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
        parliamentMemberDao.addMember(ParliamentMember(personNumber, seatNumber, last, first, party, minister, picture, twitter, bornYear, constituency))
    }

    suspend fun deleteAll() {
        parliamentMemberDao.deleteAll()
    }
}