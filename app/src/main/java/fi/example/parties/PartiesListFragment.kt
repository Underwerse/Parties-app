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
    private lateinit var vmPartiesList: PartiesListVM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle? ): View? {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_parties_list,container,false)

        vmPartiesList = ViewModelProvider(this).get(PartiesListVM::class.java)

        vmPartiesList.partiesList.observe(viewLifecycleOwner) {
            createPartiesList(it)
            Log.d("LOG", "PartiesListFragment: partiesList.observe run")
        }

        return binding.root
    }

    fun createPartiesList(partiesList: List<String>) {
        val recyclerView: RecyclerView = binding.recyclerViewAllParties
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = PartiesListAdapter(partiesList, object: PartyOnClickListener {
            override fun onClick(party: String) {
                Log.d("click", "party = $party")
                bundle.putString("selectedParty", party)
                view?.findNavController()
                    ?.navigate(R.id.action_selectPartyFragment_to_selectedPartyMembersFragment, bundle)
            }
        })
    }
}



