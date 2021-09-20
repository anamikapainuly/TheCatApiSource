package com.anupras.apl.thecatapisource.ui.catsearch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.anupras.apl.thecatapisource.adapter.CatPagingAdapter
import com.anupras.apl.thecatapisource.databinding.FragmentCatSearchBinding
import com.anupras.apl.thecatapisource.viewmodel.CatSearchViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatSearchFragment : Fragment() {
    private lateinit var binding: FragmentCatSearchBinding
    private val viewModel: CatSearchViewModel by viewModels()
    private val catAdapter = CatPagingAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCatSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()

        viewModel.setQuery(5)

        viewModel.list.observe(viewLifecycleOwner){
            catAdapter.submitData(lifecycle,it)
        }
    }

    private fun setRecyclerView() {
        val mLayoutManager = LinearLayoutManager(activity)
        binding.catImageRecycler.layoutManager = mLayoutManager
        binding.catImageRecycler.apply {
            adapter = catAdapter
            layoutManager = GridLayoutManager(requireContext(),2)

        }
    }

}