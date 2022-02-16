package fi.example.parties.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

//class PartyMemberVMFactory(private val application: Application,
//                           private val selectedParty: String)
//    : ViewModelProvider.Factory {
//    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(PartyMemberVM::class.java)){
//            return PartyMemberVM(application, selectedParty) as T
//        }
//        throw IllegalArgumentException("Unknown View Model Class")
//    }
//}