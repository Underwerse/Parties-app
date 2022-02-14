package fi.example.parties.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import fi.example.parties.database.PartyMemberRepository
import kotlinx.coroutines.launch

class PartyMemberViewModelScope (application: Application) : AndroidViewModel(application) {
    fun addPartyMember(
        personNumber: Int,
        seatNumber: Int,
        last: String,
        first: String,
        party: String,
        minister:Boolean,
        picture: String,
        twitter: String,
        bornYear:Int,
        constituency: String
    ) {
        viewModelScope.launch {
            PartyMemberRepository.addPartyMember(
                personNumber,
                seatNumber,
                last,
                first,
                party,
                minister,
                picture,
                twitter,
                bornYear,
                constituency
            )
        }
    }

    fun cleanDb() {
        viewModelScope.launch {
            PartyMemberRepository.deleteAll()
        }
    }
}