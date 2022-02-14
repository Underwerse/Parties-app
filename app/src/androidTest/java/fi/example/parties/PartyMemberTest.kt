package fi.example.parties

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import fi.example.parties.database.DB
import fi.example.parties.database.PartyMember
import fi.example.parties.database.PartyMemberDao
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.io.IOException

class PartyMemberTest {
    private lateinit var partyDao: PartyMemberDao
    private lateinit var db: DB

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, DB::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        partyDao = db.partyMembersDatabaseDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetPartyMember() {
        val partyMember = PartyMember(
            id = 0L,
            personNumber = 1467,
            seatNumber = 64,
            last = "Huru",
            first = "Petri",
            party = "ps",
            minister = false,
            picture = "attachment/member/pictures/Huru-Petri-web-v0003-1467.jpg",
            twitter = "https://twitter.com/HuruPetri",
            bornYear = 1966,
            constituency = "Satakunta",
        )
        partyDao.insertOrUpdate()
        val partyMemberByNumber = partyDao.getPartyMemberByPersonNumber(1467)
        Assert.assertEquals(partyMemberByNumber?.personNumber, 1467)
    }
}