package com.example.productdemovalu.view.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.productdemovalu.R
import com.example.productdemovalu.databinding.FragmentDetailsBinding

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding
    private val args by navArgs<DetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            args.product?.apply {
                tvName.text = title
                tvPrice.text = price.toString().plus(getString(R.string.currency))
                tvRate.text = getString(R.string.rating).plus(rating?.rate.toString())
                tvDescription.text = description.toString()
                Glide.with(requireContext()).load(image).into(ivPic)
            }
        }
    }
}