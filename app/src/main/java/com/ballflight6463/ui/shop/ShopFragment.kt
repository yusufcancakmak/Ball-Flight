package com.ballflight6463.ui.shop

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ballflight6463.R
import com.ballflight6463.databinding.FragmentShopBinding
import com.ballflight6463.ui.play.PlayActivity
import com.ballflight6463.utils.BaseInjectionDialogFragment
import com.ballflight6463.utils.CharacterManager
import com.ballflight6463.utils.Utils


class ShopFragment : BaseInjectionDialogFragment<FragmentShopBinding,ShopViewModel>() {
    override fun getLayoutRes(): Int = R.layout.fragment_shop

    override fun getViewModelClass(): Class<ShopViewModel> = ShopViewModel::class.java

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.dialog?.window?.setBackgroundDrawableResource(R.drawable.ic_shop_bg)

        initBinding()
        showScoree()

        val prefs = requireActivity().getSharedPreferences("prefs", MODE_PRIVATE)
        val ball1 = prefs.getBoolean("ball1", false)
        val ball2 = prefs.getBoolean("ball2", false)
        val ball3 = prefs.getBoolean("ball3", false)
        val ball4 = prefs.getBoolean("ball4", false)




        if(ball1){
            binding.ivShopBallOne.visibility=View.GONE
            binding.tvShopBallPriceOne.visibility=View.GONE
        }
        if(ball2){
            binding.ivShopBallTwo.visibility=View.GONE
            binding.tvShopBallPriceTwo.visibility=View.GONE
        }
        if(ball3){
            binding.ivShopBallThree.visibility=View.GONE
            binding.tvShopBallPriceThree.visibility=View.GONE
        }
        if(ball4){
            binding.ivShopBallFour.visibility=View.GONE
            binding.tvShopBallPriceFour.visibility=View.GONE
        }
    }

    private fun initBinding() = with(binding) {
        val score = CharacterManager.score
        ivDialogClose1.setOnClickListener {
        Utils.characterPosition=0

            CharacterManager.saveCharacterPosition(requireContext(), 0)
            ivBallSelected.setImageResource(R.drawable.ic_ball_stand)
            ivDialogClose1.setImageResource(R.drawable.ic_ball1)
            ivDialogClose2.setImageResource(R.drawable.ic_ball2)
            ivDialogClose3.setImageResource(R.drawable.ic_ball4)
            ivDialogClose4.setImageResource(R.drawable.ic_ball3)
            ivDialogClose5.setImageResource(R.drawable.ic_ball5)
            ivBallSelected1.setImageResource(R.drawable.ic_ball_stand1)
            ivBallSelected2.setImageResource(R.drawable.ic_ball_stand1)
            ivBallSelected3.setImageResource(R.drawable.ic_ball_stand1)
            ivBallSelected4.setImageResource(R.drawable.ic_ball_stand1)

        }
        ivDialogClose2.setOnClickListener {
            Utils.characterPosition=1
            if(score>=20){
                CharacterManager.score=score-20
                binding.ivShopBallOne.visibility=View.GONE
                binding.tvShopBallPriceOne.visibility=View.GONE
                val prefs = requireActivity().getSharedPreferences("prefs", MODE_PRIVATE)
                val editor = prefs.edit()
                editor.putBoolean("ball1", true)
                editor.apply()

            }
            CharacterManager.saveCharacterPosition(requireContext(), 1)
            ivBallSelected1.setImageResource(R.drawable.ic_ball_stand)
            ivDialogClose1.setImageResource(R.drawable.ic_ball1)
            ivDialogClose2.setImageResource(R.drawable.ic_ball2)
            ivDialogClose3.setImageResource(R.drawable.ic_ball4)
            ivDialogClose4.setImageResource(R.drawable.ic_ball3)
            ivDialogClose5.setImageResource(R.drawable.ic_ball5)
            ivBallSelected.setImageResource(R.drawable.ic_ball_stand1)
            ivBallSelected2.setImageResource(R.drawable.ic_ball_stand1)
            ivBallSelected3.setImageResource(R.drawable.ic_ball_stand1)
            ivBallSelected4.setImageResource(R.drawable.ic_ball_stand1)

        }
        ivDialogClose4.setOnClickListener {
            Utils.characterPosition=2
            if(score>=40){
                CharacterManager.score=score-40
                binding.ivShopBallTwo.visibility=View.GONE
                binding.tvShopBallPriceTwo.visibility=View.GONE
                val prefs = requireActivity().getSharedPreferences("prefs", MODE_PRIVATE)
                val editor = prefs.edit()
                editor.putBoolean("ball2", true)
                editor.apply()
            }
            CharacterManager.saveCharacterPosition(requireContext(), 2)
            ivBallSelected2.setImageResource(R.drawable.ic_ball_stand)
            ivDialogClose1.setImageResource(R.drawable.ic_ball1)
            ivDialogClose2.setImageResource(R.drawable.ic_ball2)
            ivDialogClose3.setImageResource(R.drawable.ic_ball4)
            ivDialogClose4.setImageResource(R.drawable.ic_ball3)
            ivDialogClose5.setImageResource(R.drawable.ic_ball5)
            ivBallSelected.setImageResource(R.drawable.ic_ball_stand1)
            ivBallSelected1.setImageResource(R.drawable.ic_ball_stand1)
            ivBallSelected3.setImageResource(R.drawable.ic_ball_stand1)
            ivBallSelected4.setImageResource(R.drawable.ic_ball_stand1)
        }
        ivDialogClose3.setOnClickListener {
            Utils.characterPosition=3
            if(score>=50){
                CharacterManager.score=score-50
                binding.ivShopBallThree.visibility=View.GONE
                binding.tvShopBallPriceThree.visibility=View.GONE
                val prefs = requireActivity().getSharedPreferences("prefs", MODE_PRIVATE)
                val editor = prefs.edit()
                editor.putBoolean("ball3", true)
                editor.apply()
            }
            CharacterManager.saveCharacterPosition(requireContext(), 3)
            ivBallSelected3.setImageResource(R.drawable.ic_ball_stand)
            ivDialogClose1.setImageResource(R.drawable.ic_ball1)
            ivDialogClose2.setImageResource(R.drawable.ic_ball2)
            ivDialogClose3.setImageResource(R.drawable.ic_ball4)
            ivDialogClose4.setImageResource(R.drawable.ic_ball3)
            ivDialogClose5.setImageResource(R.drawable.ic_ball5)
            ivBallSelected.setImageResource(R.drawable.ic_ball_stand1)
            ivBallSelected1.setImageResource(R.drawable.ic_ball_stand1)
            ivBallSelected2.setImageResource(R.drawable.ic_ball_stand1)
            ivBallSelected4.setImageResource(R.drawable.ic_ball_stand1)
        }
        ivDialogClose5.setOnClickListener {
            Utils.characterPosition=4
            viewModel.characterPosition = 4
            if(score>=60){
                CharacterManager.score=score-60
                binding.ivShopBallFour.visibility=View.GONE
                binding.tvShopBallPriceFour.visibility=View.GONE
                val prefs = requireActivity().getSharedPreferences("prefs", MODE_PRIVATE)
                val editor = prefs.edit()
                editor.putBoolean("ball4", true)
                editor.apply()
            }
            CharacterManager.saveCharacterPosition(requireContext(), 4)
            ivBallSelected4.setImageResource(R.drawable.ic_ball_stand)
            ivDialogClose1.setImageResource(R.drawable.ic_ball1)
            ivDialogClose2.setImageResource(R.drawable.ic_ball2)
            ivDialogClose3.setImageResource(R.drawable.ic_ball4)
            ivDialogClose4.setImageResource(R.drawable.ic_ball3)
            ivDialogClose5.setImageResource(R.drawable.ic_ball5)
            ivBallSelected.setImageResource(R.drawable.ic_ball_stand1)
            ivBallSelected1.setImageResource(R.drawable.ic_ball_stand1)
            ivBallSelected2.setImageResource(R.drawable.ic_ball_stand1)
            ivBallSelected3.setImageResource(R.drawable.ic_ball_stand1)

        }
        ivNext.setOnClickListener {
            startActivity(Intent(requireContext(),PlayActivity::class.java))
        }



    }
    private fun showScoree() {
        val score = CharacterManager.score


        binding.tvShopCoin.text = score.toString()

        //binding.tvShopCoin.text = score.toString()
    }

    private fun setBuyVisiblty(){





    }




}

