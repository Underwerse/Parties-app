package fi.example.tiistai2501

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fi.example.tiistai2501.databinding.FragmentSelectPartyBinding
import fi.example.tiistai2501.recyclerview.PartiesListAdapter
import fi.example.tiistai2501.recyclerview.PartyOnClickListener

class SelectPartyFragment : Fragment() {
    val bundle = Bundle()
    private lateinit var binding: FragmentSelectPartyBinding
    private var partiesList =
        ParliamentMembersData.members.map { it.party }.toSet().toList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_select_party,container,false)

        createPartiesList()

        return binding.root
    }

    fun createPartiesList() {
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

