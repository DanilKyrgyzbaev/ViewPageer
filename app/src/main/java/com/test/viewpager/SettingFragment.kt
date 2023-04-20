package com.test.viewpager

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.test.viewpager.databinding.FragmentSettingBinding
import kotlinx.coroutines.launch

class SettingFragment : Fragment() {
    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<MainViewModel>()
    private lateinit var fragmentAdapter: MyPagerAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        val view = binding.root

        fragmentAdapter = MyPagerAdapter(requireActivity())

        viewModel.fragmentList.observe(requireActivity()) { fragmentList ->
            fragmentList?.let { fragmentAdapter.submitList(it) }
        }

        val navController = activity?.findNavController(R.id.nav_host_fragment)

        binding.save.setOnClickListener {
            val uniqueId = View.generateViewId()
            val title = binding.titleView.text
            val subTitle = binding.subtitleView.text
            lifecycleScope.launch {
                viewModel.addFragment(uniqueId, title.toString(), subTitle.toString())
            }
            Log.e("Check", "$uniqueId,$title, $subTitle")
            navController?.navigate(R.id.action_settingFragment_to_homeFragment)
        }
        return view
    }
}
