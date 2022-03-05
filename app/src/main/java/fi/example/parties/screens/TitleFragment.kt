package fi.example.parties.screens

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import fi.example.parties.R
import fi.example.parties.databinding.FragmentTitleBinding

/**
 * Fragment for app start screen
 */
class TitleFragment : Fragment() {
    private lateinit var binding: FragmentTitleBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_title, container, false)

        binding.btnStart.setOnClickListener { view : View ->
            view.findNavController()
                .navigate(R.id.action_titleFragment_to_partiesFragment)

        }

        // add Options-menu
        setHasOptionsMenu(true)

        return binding.root
    }
    
    /**
     * Builds Options-menu
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }
    
    /**
     * Process Options-menu on-item click
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.
        onNavDestinationSelected(item,requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }
}