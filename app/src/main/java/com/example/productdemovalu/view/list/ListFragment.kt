package com.example.productdemovalu.view.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.productdemovalu.R
import com.example.productdemovalu.databinding.FragmentListBinding
import com.example.productdemovalu.remote.Product
import com.example.productdemovalu.utils.DataState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.loading_state_layout.*

@AndroidEntryPoint
class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding
    private lateinit var productAdapter: ProductAdapter
    private val listViewModel by viewModels<ListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productAdapter = ProductAdapter(requireContext()) {
            val action = ListFragmentDirections.actionListFragmentToDetailsFragment(it)
            findNavController().navigate(action)
        }

        initObservable()
    }


    private fun initObservable() {

        listViewModel.refreshProducts().observe(viewLifecycleOwner) {

            when (it.getStatus()) {

                DataState.DataStatus.LOADING -> {
                    showHideLoading(isLoading = true)
                }

                DataState.DataStatus.SUCCESS -> {
                    showHideLoading()
                    productAdapter.submitList(it.getData() as ArrayList<Product>)
                    with(binding) {
                        rvProducts.adapter = productAdapter
                    }
                }

                DataState.DataStatus.ERROR -> {
                    showHideLoading(hasError = true, txt = it.getError()?.message.toString())
                }

                DataState.DataStatus.NO_INTERNET -> {
                    showHideLoading(hasError = true, txt = getString(R.string.no_internet))
                }
            }
        }
    }

    private fun showHideLoading(
        isLoading: Boolean = false,
        hasError: Boolean = false,
        txt: String = ""
    ) {

        pb_progressbar.isVisible = isLoading
        tv_error.isVisible = hasError
        tv_error.text = txt
        btn_retry.isVisible = hasError
        btn_retry.setOnClickListener {
            initObservable()
        }
    }

}