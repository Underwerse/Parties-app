package fi.example.parties

import android.app.Application
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
import fi.example.parties.databinding.FragmentSelectPartyBinding
import fi.example.parties.viewmodels.AddPartyMemberViewModel
import fi.example.parties.recyclerview.PartiesListAdapter
import fi.example.parties.recyclerview.PartyOnClickListener
import fi.example.parties.viewmodels.PartiesListViewModel

class SelectPartyFragment : Fragment() {
    val bundle = Bundle()
    private lateinit var binding: FragmentSelectPartyBinding
    private val parliamentMembers = ParliamentMembersData.members
    private lateinit var viewModelPartyMemberAdd: AddPartyMemberViewModel
    private lateinit var viewModel: PartiesListViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_select_party,container,false)

        viewModelPartyMemberAdd = ViewModelProvider(this).get(AddPartyMemberViewModel::class.java)
        viewModel = ViewModelProvider(this).get(PartiesListViewModel::class.java)

        viewModel.partiesList.observe(viewLifecycleOwner) {
            createPartiesList(it)
        }

        binding.btnAddToDb.setOnClickListener {
            parliamentMembers.forEach {
                viewModelPartyMemberAdd.addPartyMember(
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
            }
            Toast.makeText(requireContext(), "All the members have been added.", Toast.LENGTH_SHORT).show()
        }

        binding.btnCleanDb.setOnClickListener {
            viewModelPartyMemberAdd.cleanDb()
            Toast.makeText(requireContext(), "DB has been cleaned.", Toast.LENGTH_SHORT).show()
        }

        return binding.root
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



