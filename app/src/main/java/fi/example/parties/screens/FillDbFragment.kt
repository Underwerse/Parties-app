package fi.example.parties.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import fi.example.parties.R
import fi.example.parties.data.MembersRepository
import fi.example.parties.databinding.FragmentFillDbBinding
import fi.example.parties.room.DB
import fi.example.parties.room.entities.PartyMember
import fi.example.parties.room.entities.PartyMemberDao
import fi.example.parties.viewmodels.FillDbVM
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class FillDbFragment : Fragment() {
    private lateinit var binding: FragmentFillDbBinding
    private lateinit var vmFillDb: FillDbVM
    private lateinit var membersRepository: MembersRepository
    private lateinit var partyMemberDao: PartyMemberDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        
        binding = DataBindingUtil.inflate(inflater,
			R.layout.fragment_fill_db, container, false)

        vmFillDb = ViewModelProvider(this).get(FillDbVM::class.java)
        
        partyMemberDao = DB.getInstance(requireContext()).partyMemberDao
        membersRepository = MembersRepository(partyMemberDao)
        
        binding.btnFillDbFromFile.setOnClickListener {
            insertDataToDB()
        }
        
        binding.btnFillDbFromNetwork.setOnClickListener {
            insertDataToDBFromNetwork()
        }

        binding.btnCleanDb.setOnClickListener {
            lifecycleScope.launch {
                membersRepository.deleteAll()
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
                membersRepository.addMember(member)
            }
        }
        Toast.makeText(requireContext(), "All the members from file have been added.", Toast.LENGTH_SHORT).show()
    }
    
    private fun insertDataToDBFromNetwork() {
        (vmFillDb.membersFromNetwork.value)?.forEach {
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
                membersRepository.addMember(member)
            }
        }
        Toast.makeText(requireContext(), "All the members from API have been added.", Toast.LENGTH_SHORT).show()
    }
}