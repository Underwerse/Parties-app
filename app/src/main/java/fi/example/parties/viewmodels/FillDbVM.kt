package fi.example.parties.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import fi.example.parties.MemberOfParliament
import fi.example.parties.ParliamentMembersData

class FillDbVM(application: Application) : AndroidViewModel(application) {
    
    private val _membersFromFile: List<MemberOfParliament>
    val membersFromFile: List<MemberOfParliament>
        get() = _membersFromFile
    
    init {
        Log.i("LOG", "FillDbVM created")
        _membersFromFile = ParliamentMembersData.members
    }
    
    override fun onCleared() {
        super.onCleared()
        Log.i("LOG", "FillDbVM destroyed")
    }
}