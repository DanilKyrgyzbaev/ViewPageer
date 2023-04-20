package com.test.viewpager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.test.viewpager.databinding.FragmentMyBinding
import com.test.viewpager.model.Data

class MyFragment : Fragment() {
    private var _binding: FragmentMyBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentMyBinding.inflate(inflater, container, false)
        val view = binding.root
        arguments?.let {
            val data = it.getParcelable<Data>("data") ?: return@let
            binding.titleView.text = data.title
            binding.subtitleView.text = data.subtitle
        }
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(data: Data): MyFragment {
            val args = Bundle().apply {
                putParcelable("data", data)
            }
            val fragment = MyFragment()
            fragment.arguments = args
            return fragment
        }
    }
}





