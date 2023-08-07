package com.ballflight6463.ui.setting

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Layout
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.akexorcist.localizationactivity.ui.LocalizationActivity
import com.ballflight6463.R
import com.ballflight6463.databinding.FragmentSettingBinding
import com.ballflight6463.utils.BaseInjectionDialogFragment
import com.ballflight6463.utils.BaseInjectionFragment
import com.ballflight6463.utils.Utils
import com.pixplicity.easyprefs.library.Prefs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : BaseInjectionDialogFragment<FragmentSettingBinding,SettingViewModel>() {
    override fun getLayoutRes(): Int =R.layout.fragment_setting

    override fun getViewModelClass(): Class<SettingViewModel> = SettingViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //background corner delete and set transparent
        this.dialog?.window?.setBackgroundDrawableResource(R.drawable.ic_shop_bg)
        binding.tvLanguage.setOnClickListener {
            if (Utils.language == "en") {
                Utils.language = "pt"
                Prefs.putString("language","pt")
                (requireActivity() as LocalizationActivity).setLanguage(Prefs.getString("language","pt"))
            } else if (Utils.language == "pt") {
                Utils.language = "ru"
                Prefs.putString("language","ru")
                (requireActivity() as LocalizationActivity).setLanguage(Prefs.getString("language","ru"))
            } else {
                Utils.language = "en"
                Prefs.putString("language","en")
                (requireActivity() as LocalizationActivity).setLanguage(Prefs.getString("language","en"))
            }
        }
        binding.switchDialogSound.isChecked = Utils.isSoundActive(requireContext())
        binding.switchDialogSound.setOnCheckedChangeListener { _, isChecked ->
            Utils.soundActive = !Utils.soundActive
            Prefs.putBoolean("soundActive",Utils.soundActive)



        }

        binding.switchDialogMusic.isChecked = Utils.isMusicActive(requireContext())
        binding.switchDialogMusic.setOnCheckedChangeListener { _, isChecked ->
            Utils.musicActive = !Utils.musicActive

            if (Utils.soundActive) {
                Prefs.putBoolean("musicActive",Utils.musicActive)
                Utils.startMusic(requireContext())
            }else {

            }



            }


    }





}