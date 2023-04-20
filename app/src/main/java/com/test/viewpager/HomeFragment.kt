package com.test.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.test.viewpager.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var fragmentAdapter: MyPagerAdapter
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        fragmentAdapter = MyPagerAdapter(requireActivity())

        binding.viewPager.adapter = fragmentAdapter
        binding.viewPager.offscreenPageLimit = ViewPager2.OFFSCREEN_PAGE_LIMIT_DEFAULT

        viewModel.fragmentList.observe(viewLifecycleOwner) { fragmentList ->
            fragmentList?.let { fragmentAdapter.submitList(it) }
        }

        val navController = activity?.findNavController(R.id.nav_host_fragment)

        binding.fab.setOnClickListener {
            navController?.navigate(R.id.action_homeFragment_to_settingFragment)
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
