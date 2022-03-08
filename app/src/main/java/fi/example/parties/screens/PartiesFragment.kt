package fi.example.parties.screens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fi.example.parties.R
import fi.example.parties.databinding.FragmentPartiesBinding
import fi.example.parties.recyclerviews.PartiesAdapter
import fi.example.parties.recyclerviews.PartyOnClickListener
import fi.example.parties.viewmodels.PartiesVM

/**
 * 5.3.2022, Pavel Chernov, opnro. 2110598
 * Fragment for selected by party's name members list's data screen
 */
class PartiesFragment : Fragment() {
    val bundle = Bundle()
    private lateinit var binding: FragmentPartiesBinding
    private lateinit var vmParties: PartiesVM

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,
			R.layout.fragment_parties,container,false)

        vmParties = ViewModelProvider(this).get(PartiesVM::class.java)

        vmParties.allPartiesFromDb.observe(viewLifecycleOwner) {
            createPartiesList(it)
        }

        return binding.root
    }
    
    /**
     * Fills RecyclerView-list with data
     */
    private fun createPartiesList(partiesList: List<String>) {
        val recyclerView: RecyclerView = binding.recyclerViewAllParties
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = PartiesAdapter(partiesList, object: PartyOnClickListener {
            override fun onClick(party: String) {
                
                // put data for transit to the next View
                bundle.putString("selectedParty", party)
                view?.findNavController()
                    ?.navigate(R.id.action_partiesFragment_to_membersFragment, bundle)
            }
        })
    }
}



