package fi.example.parties

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fi.example.parties.room.entities.PartyMember
import fi.example.parties.databinding.FragmentPartiesListBinding
import fi.example.parties.recyclerviews.PartiesListAdapter
import fi.example.parties.recyclerviews.PartyOnClickListener
import fi.example.parties.viewmodels.PartyMemberVM
import fi.example.parties.viewmodels.PartiesListVM

class PartiesListFragment : Fragment() {
    val bundle = Bundle()
    private lateinit var binding: FragmentPartiesListBinding
    private val parliamentMembers = ParliamentMembersData.members
//    private lateinit var vmPartyMemberScope: PartyMemberVMScope
    private lateinit var vmPartiesList: PartiesListVM
    private lateinit var vmPartyMember: PartyMemberVM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_parties_list,container,false)

        vmPartyMember = ViewModelProvider(this).get(PartyMemberVM::class.java)
        vmPartiesList = ViewModelProvider(this).get(PartiesListVM::class.java)

//        vmPartyMemberScope = ViewModelProvider(this).get(PartyMemberVMScope::class.java)
        vmPartiesList = ViewModelProvider(this).get(PartiesListVM::class.java)

//        vmPartiesList.partiesList.observe(viewLifecycleOwner) {
//            createPartiesList(it)
//        }

        binding.btnAddToDb.setOnClickListener {
            insertDataToDB()
        }

        binding.btnCleanDb.setOnClickListener {
            vmPartyMember.deleteAll()
            Toast.makeText(requireContext(), "DB has been cleaned.", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    private fun insertDataToDB() {
        parliamentMembers.forEach {
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
            vmPartyMember.addMember(member)
        }
        Toast.makeText(requireContext(), "All the members have been added.", Toast.LENGTH_SHORT).show()
    }

    fun createPartiesList(partiesList: List<String>) {
        val recyclerView: RecyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = PartiesListAdapter(partiesList, object: PartyOnClickListener {
            override fun onClick(party: String) {
                Log.d("click", "party = $party")
                bundle.putString("selectedParty", party)
                view?.findNavController()
                    ?.navigate(R.id.action_selectPartyFragment_to_partyDetailsFragment, bundle)
            }
        })
    }
}



