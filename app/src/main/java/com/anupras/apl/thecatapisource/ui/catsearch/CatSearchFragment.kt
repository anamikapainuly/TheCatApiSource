package com.anupras.apl.thecatapisource.ui.catsearch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.anupras.apl.thecatapisource.adapter.CatPagingAdapter
import com.anupras.apl.thecatapisource.databinding.FragmentCatSearchBinding
import com.anupras.apl.thecatapisource.model.Categories
import com.anupras.apl.thecatapisource.network.Status
import com.anupras.apl.thecatapisource.viewmodel.CatImagesViewModel
import dagger.hilt.android.AndroidEntryPoint
import android.widget.AdapterView

import com.anupras.apl.thecatapisource.adapter.CustomSpinner


@AndroidEntryPoint
class CatSearchFragment : Fragment() {
    private lateinit var binding: FragmentCatSearchBinding
    private val viewModel: CatImagesViewModel by viewModels()
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
        viewModel.getAllCategory()
        viewModel.allCategories.observe(viewLifecycleOwner) {
            when (it.getContentIfNotHandled()?.status) {
                Status.LOADING-> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    it.peekContent()?.data.let {
                        it?.forEach {
                            it.name
                        }
                        if (it != null) {
                            binding.progressBar.visibility = View.GONE
                            populateList(it)
                        }
                    }
                }
            }
        }

        catAdapter.onCatClick {
            val action = CatSearchFragmentDirections.actionCatFragmentToDetailFragment(it)
            findNavController().navigate(action)
        }
        viewModel.list.observe(viewLifecycleOwner) {
            catAdapter.submitData(lifecycle, it)
        }
    }

    private fun populateList(it: List<Categories>) {
        val spinnerAdapter = CustomSpinner(requireContext(), it)
        binding.categorySpinner.adapter = spinnerAdapter

        binding.categorySpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                    viewModel.setQuery(null)
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
//                it[position].id
                    viewModel.setQuery(it[position].id)
                }

            }

    }


    private fun setRecyclerView() {
        val mLayoutManager = LinearLayoutManager(activity)
        binding.catImageRecycler.layoutManager = mLayoutManager
        binding.catImageRecycler.apply {
            adapter = catAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)

        }
    }


}