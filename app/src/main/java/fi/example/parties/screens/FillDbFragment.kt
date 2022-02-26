package fi.example.parties.screens

import android.app.Application
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import fi.example.parties.ParliamentMembersData
import fi.example.parties.R
import fi.example.parties.data.Repository
import fi.example.parties.databinding.FragmentDbHandleBinding
import fi.example.parties.room.DB
import fi.example.parties.room.entities.PartyMember
import fi.example.parties.room.entities.PartyMemberDao
import fi.example.parties.viewmodels.FillDbVM
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FillDbFragment : Fragment() {
    private lateinit var binding: FragmentDbHandleBinding
    private lateinit var vmFillDb: FillDbVM
    private lateinit var repository: Repository
    private lateinit var partyMemberDao: PartyMemberDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        
        binding = DataBindingUtil.inflate(inflater,
			R.layout.fragment_db_handle, container, false)

        vmFillDb = ViewModelProvider(this).get(FillDbVM::class.java)
        
        partyMemberDao = DB.getInstance(requireContext()).partyMemberDao
        repository = Repository(partyMemberDao)
        
        binding.btnAddToDb.setOnClickListener {
            insertDataToDB()
        }

        binding.btnCleanDb.setOnClickListener {
            lifecycleScope.launch {
                repository.deleteAll()
            }
            Toast.makeText(requireContext(), "DB has been cleaned.", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    private fun insertDataToDB() {
        vmFillDb.membersFromFile.forEach {
            val member = PartyMember(
                it.personNumber,
                it.seatNumber,
                it.last,
                it.first,
                it.party,
                it.minister,
                it.picture,
                it.twitter,
                it.bornYear,
                it.constituency
            )
            GlobalScope.launch {
                repository.addMember(member)
            }
        }
        Toast.makeText(requireContext(), "All the members have been added.", Toast.LENGTH_SHORT).show()
    }
}