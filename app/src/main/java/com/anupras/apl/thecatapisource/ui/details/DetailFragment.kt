package com.anupras.apl.thecatapisource.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels

import com.anupras.apl.thecatapisource.databinding.FragmentDetailBinding
import com.anupras.apl.thecatapisource.network.Status
import com.anupras.apl.thecatapisource.viewmodel.CatImagesViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

@AndroidEntryPoint
class DetailFragment : Fragment() {
    lateinit var binding: FragmentDetailBinding
    private val args: DetailFragmentArgs by navArgs()
    private val viewModel: CatImagesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.backPress.setOnClickListener {
            findNavController().popBackStack()
        }

        viewModel.getCatDetails(args.imageId!!)
        viewModel.catDetails.observe(viewLifecycleOwner) {
            when (it.getContentIfNotHandled()?.status) {

                Status.LOADING -> {
                    binding.detailsProgress.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    binding.detailsProgress.visibility = View.GONE
                }
                Status.SUCCESS -> {
                    binding.detailsProgress.visibility = View.GONE
                    binding.catDetails = it.peekContent().data
                }

            }
        }


    }

}