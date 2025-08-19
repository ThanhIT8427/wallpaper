package com.example.thanhvip123wallpaper

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.thanhvip123wallpaper.databinding.FragmentChoiceWallpaperScreenBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ChoiceWallpaperScreenFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentChoiceWallpaperScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate<FragmentChoiceWallpaperScreenBinding>(
            inflater,
            R.layout.fragment_choice_wallpaper_screen,
            container,
            false
        )
        return binding.root
    }

}