package com.hemanthddev.rickandmorty.ui.main

import androidx.activity.viewModels
import com.hemanthddev.rickandmorty.R
import com.hemanthddev.rickandmorty.base.BaseActivity
import com.hemanthddev.rickandmorty.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    private val mainViewModel: MainViewModel by viewModels()

    override val layoutId: Int
        get() = R.layout.activity_main

    override fun getVM(): MainViewModel = mainViewModel

    override fun bindVM(binding: ActivityMainBinding, vm: MainViewModel) = Unit

}