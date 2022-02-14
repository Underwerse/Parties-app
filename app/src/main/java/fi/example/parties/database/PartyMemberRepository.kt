package fi.example.parties.database

object PartyMemberRepository {
    suspend fun addPartyMember(
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
    }

    suspend fun deleteAll() {
        DB.getInstance()
            .partyMemberDao
            .deleteAllMembers()
    }

    val partyMembers = DB.getInstance().partyMemberDao.getAllMembers()

    val parties = DB.getInstance().partyMemberDao.getParties()
}