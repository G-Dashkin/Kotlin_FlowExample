package com.example.kotlin_flowexample.FlowRetrofit.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlin_flowexample.FlowRetrofit.adapter.CryptosAdapter
import com.example.kotlin_flowexample.FlowRetrofit.utils.DataStatus
import com.example.kotlin_flowexample.FlowRetrofit.utils.initRecyclerView
import com.example.kotlin_flowexample.FlowRetrofit.utils.isVisible
import com.example.kotlin_flowexample.R
import com.example.kotlin_flowexample.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel : CryptoAppViewModel by viewModels()

    @Inject
    lateinit var cryptosAdapter: CryptosAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        lifecycleScope.launch {
            binding.apply {
                viewModel.getCoinsList("eur")
                viewModel.coinsList.observe(viewLifecycleOwner) {
                    when(it.status) {

                        DataStatus.Status.LOADING -> {
                            pBarLoading.isVisible(true, rvCrypto)
                        }
                        DataStatus.Status.SUCCESS -> {
                            pBarLoading.isVisible(false, rvCrypto)
                            cryptosAdapter.differ.submitList(it.data)
                            cryptosAdapter.setOnItemClickListener {
                                val direction = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(it.id)
                                findNavController().navigate(direction)
                            }
                        }
                        DataStatus.Status.ERROR -> {
                            pBarLoading.isVisible(true, rvCrypto)
                            Toast.makeText(requireContext(), "There is something wrong", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    fun setUpRecyclerView() {
        binding.rvCrypto.initRecyclerView(LinearLayoutManager(requireContext()), cryptosAdapter)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}


