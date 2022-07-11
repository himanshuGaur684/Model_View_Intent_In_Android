package com.gaur.mviexampleyt

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.gaur.mviexampleyt.databinding.ActivityMainBinding
import com.gaur.mviexampleyt.ui.main.adapter.MainAdapter
import com.gaur.mviexampleyt.ui.main.intent.MainIntent
import com.gaur.mviexampleyt.ui.main.viewmodel.MainViewModel
import com.gaur.mviexampleyt.ui.main.viewstate.MainViewState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding: ActivityMainBinding
        get() = _binding!!

    private val viewModel: MainViewModel by viewModels()
    private val mainAdapter = MainAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()

        observeViewModels()

        lifecycleScope.launch {
            viewModel.mainIntent.send(MainIntent.GetPosts)
        }


    }

    private fun observeViewModels() {
        lifecycleScope.launch {
            viewModel.state.collect {
                when (it) {
                    is MainViewState.Loading -> {
                        binding.progress.visibility = View.VISIBLE
                    }
                    is MainViewState.Success -> {
                        binding.progress.visibility = View.GONE
                        mainAdapter.addItems(it.data)
                    }
                    is MainViewState.Error -> {
                        binding.progress.visibility = View.GONE
                    }

                }

            }
        }

    }

    private fun initView() {
        binding.rvPosts.adapter = mainAdapter
    }
}