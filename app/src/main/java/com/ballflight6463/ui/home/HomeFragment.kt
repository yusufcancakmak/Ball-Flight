package com.ballflight6463.ui.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.ballflight6463.R
import com.ballflight6463.databinding.FragmentHomeBinding
import com.ballflight6463.ui.play.PlayActivity
import com.ballflight6463.ui.setting.SettingFragment
import com.ballflight6463.utils.BaseInjectionFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseInjectionFragment<FragmentHomeBinding,HomeViewModel>() {
    private lateinit var ivSetting:ImageView
    override fun getLayoutRes(): Int = R.layout.fragment_home


    override fun getViewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
    }
    private fun initBinding() {
       binding.ivSettingBackground.setOnClickListener {
             // findNavController().navigate(R.id.action_homeFragment_to_settingFragment)
           val settingFragment = SettingFragment()
           settingFragment.show(childFragmentManager, "setting_dialog_tag")

       }
        binding.tvShop.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_shopFragment)

        }
        binding.tvPlay.setOnClickListener {
            val intent= Intent(requireContext(),PlayActivity::class.java)
            startActivity(intent)
        }
    }

}