package fi.example.parties.database

import androidx.lifecycle.LiveData

class ParliamentRepository(private val parliamentMemberDao: ParliamentMemberDao) {
    val readAllData: LiveData<List<ParliamentMember>> = parliamentMemberDao.getAllMembers()

    suspend fun addMember(parliamentMember: ParliamentMember) {
        parliamentMemberDao.addMember(parliamentMember)
    }
}