package fi.example.parties.room.data

class ApiRepository(private val apiHelper: ApiHelper) {
	suspend fun getMembers() = apiHelper.getMembers()
}