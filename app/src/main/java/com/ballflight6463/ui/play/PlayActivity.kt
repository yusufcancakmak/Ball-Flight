package com.ballflight6463.ui.play

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import com.ballflight6463.R
import com.ballflight6463.databinding.ActivityPlayBinding
import com.ballflight6463.databinding.LayoutLeaderboardBinding
import com.ballflight6463.models.Leader
import com.ballflight6463.ui.main.MainActivity
import com.ballflight6463.ui.shop.ShopFragment
import com.ballflight6463.ui.shop.ShopViewModel
import com.ballflight6463.utils.BaseInjectionActivity
import com.ballflight6463.utils.CharacterManager
import com.ballflight6463.utils.Utils
import okhttp3.internal.wait
import java.util.*

class PlayActivity : BaseInjectionActivity<ActivityPlayBinding, PlayViewModel>() {
    private val shopViewModel: ShopViewModel by viewModels()
    override fun getLayoutRes(): Int = R.layout.activity_play

    override fun getViewModelClass(): Class<PlayViewModel> = PlayViewModel::class.java
    private lateinit var cl: ConstraintLayout
   // private lateinit var textViewoyunabasla: TextView
    private lateinit var textViewSkor: TextView
    private lateinit var yellowcircle: ImageView
    private lateinit var redtriangle: ImageView
    private lateinit var leaderboardBinding: LayoutLeaderboardBinding
    private lateinit var tvcointext: TextView
    private lateinit var gamestop: ImageView

    private var lastPassedBlackSquare: ImageView? = null
    private var lastPassedRedTriangle: ImageView? = null



    private lateinit var maincarackter: ImageView
    private lateinit var blacksquare: ImageView
    private val wallOneIDs = listOf(
        R.drawable.ic_wall_one, R.drawable.ic_wall_two, R.drawable.ic_wall_three,
        R.drawable.ic_wall_four, R.drawable.ic_wall_five
    )

    // Pozisyonlar
    private var maincarackterX: Int = 0
    private var maincarackterY: Int = 0
    private var yellowcircleX: Int = 0
    private var yellowcircleY: Int = 0
    private var redtriangleX: Int = 0
    private var redtriangleY: Int = 0
    private var blacksquraX: Int = 0
    private var blacksquareY: Int = 0


    // Boyutlar
    private var screenwidth: Int = 0
    private var screenhight: Int = 0
    private var maincarackterwidth: Int = 0
    private var maincarackterhight: Int = 0

    // Hızlar
    private var maincarackterspeed: Int = 0
    private var yellowcirclespeed: Int = 0
    private var redtrianglespeed: Int = 0
    private var blacksquarespeed: Int = 0
    private var gameStopp = false


    // Kontroller
    private var dokunmaKontrol: Boolean = false
    private var initialcontrol: Boolean = false
    private var isGameStarted: Boolean = false
    private var isGamePaused: Boolean = false
    private var isShowedDialog = false
    private var isTiklandi: Boolean = false
    private var isTiklandiOnce: Boolean = false

    private var yellowcirclePassed = false
    private var redtrianglePassed = false
    private var blacksquarePassed = false
    private var isGameOverDialogShown = false

    private var gameOverSound: MediaPlayer? = null





    private var skor: Int = 0
    private var score: Int = 0
    private var timer: Timer? = Timer()
    private var handler: Handler = Handler()

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Utils.startMusic(this)

        cl = findViewById(R.id.cl)
        leaderboardBinding = LayoutLeaderboardBinding.inflate(layoutInflater)
        val characterPosition = shopViewModel.characterPosition
        cl.addView(leaderboardBinding.root)
        leaderboardBinding.root.isVisible = false

       // textViewoyunabasla = findViewById(R.id.textViewoyunabasla)
        textViewSkor = findViewById(R.id.textViewSkor)
        yellowcircle = findViewById(R.id.saridaire)
        tvcointext = findViewById(R.id.tv_coin)
        gamestop = findViewById(R.id.imageView2)
        redtriangle = findViewById(R.id.kirmiziucgen)
        maincarackter = findViewById(R.id.anakarakter)
        blacksquare = findViewById(R.id.siyahkare)

        blacksquare.x = -80f
        blacksquare.y = -80f
        //  kirmiziucgen.x = -80f
        //  kirmiziucgen.y = -80f
        yellowcircle.x = -80f
        yellowcircle.y = -80f

        leaderboardBinding.textView.setOnClickListener {
            leaderboardBinding.root.isVisible = false
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            isShowedDialog = false
        }
        viewModel.getLeaders()

        viewModel.leaderList.observe(this) {
            val adapter = PlayAdapter()
            adapter.playerList = it
            leaderboardBinding.rvLeaderboard.adapter = adapter
        }
        val characterPositionn = CharacterManager.loadCharacterPosition(this)

        // Ana karakteri seçilen karaktere göre ayarlayın
        when (characterPositionn) {
            0 -> {
                maincarackter.setImageResource(R.drawable.ic_game_ball1)
            }
            1 -> {
                maincarackter.setImageResource(R.drawable.ic_game_ball2)
            }
            2 -> {
                maincarackter.setImageResource(R.drawable.ic_game_ball3)
            }
            3 -> {
                maincarackter.setImageResource(R.drawable.ic_game_ball4)
            }
            4 -> {
                maincarackter.setImageResource(R.drawable.ic_game_ball5)
            }
            // Diğer karakterler için de benzer şekilde işlem yapın...
            else -> {
                maincarackter.setImageResource(R.drawable.ic_game_ball1)
            }
        }
        gamestop.setOnClickListener {
            gameStop()
        }
        gameOverSound = MediaPlayer.create(this, R.raw.ms_gameover)
        gameOverSound?.setOnCompletionListener {
            // Release the media player once the sound has finished playing
            gameOverSound?.release()
            gameOverSound = null
        }
        cl.setOnTouchListener { _, motionEvent ->
            if (initialcontrol) {
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        Log.e("MotionEvent", "the screen was touched")
                        // track click status
                        isTiklandi = true
                        // check if i don't click second
                        isTiklandiOnce = !isTiklandiOnce
                    }
                    MotionEvent.ACTION_UP -> {
                        Log.e("MotionEvent", "left on the screen")
                        // reset click state
                        isTiklandi = false
                    }
                }
            } else {
                // Others cods...
                initialcontrol = true
                binding.textViewoyunabasla.visibility = View.INVISIBLE

                maincarackterX = maincarackter.x.toInt()
                maincarackterY = maincarackter.y.toInt()
                maincarackterwidth = maincarackter.width
                maincarackterhight = maincarackter.height
                screenwidth = cl.width
                screenhight = cl.height

                timer?.schedule(object : TimerTask() {
                    override fun run() {
                        handler.post {
                            mainCarackterMove()
                            moveObjects()
                            collisionControl()

                        }
                    }
                }, 0, 20)
            }
            true
        }



    }

    private fun mainCarackterMove() {
        maincarackterspeed = screenhight / 160 // 1/60 ekran yüksekliği kadar hareket eder

        if (isTiklandi && !isTiklandiOnce) {
            // İlk tıklamada sola çapraz hareket et
            maincarackterX -= maincarackterspeed

        } else if (isTiklandi && isTiklandiOnce) {
            // İkinci tıklamada sağa çapraz hareket et
            maincarackterX += maincarackterspeed
        }

        // Sınırları kontrol et
        if (maincarackterX < 0) {
            maincarackterX = 0
        }
        if (maincarackterX > screenwidth - maincarackterwidth) {
            maincarackterX = screenwidth - maincarackterwidth
        }

        // Calculate the threshold for vertical movement to keep the character within the top half of the screen
        val centerYThreshold = screenhight / 1
        val centerMinY = 0
        val centerMaxY = centerYThreshold

        if (maincarackterY < centerMinY) {
            maincarackterY = centerMinY
        }
        if (maincarackterY > centerMaxY - maincarackterhight) {
            maincarackterY = centerMaxY - maincarackterhight
        }

        // Update the character's position
        maincarackter.x = maincarackterX.toFloat()
        maincarackter.y = maincarackterY.toFloat()
    }


    private val marginVertical = 150 // Adjust this value as needed for the top and bottom margin
    private fun moveObjects() {
        redtrianglespeed = screenwidth / 120
        yellowcirclespeed = screenwidth / 120
        blacksquarespeed = screenwidth / 120

        // Siyah Kare hareketini düzenle
        blacksquareY += blacksquarespeed
        if (blacksquareY > screenhight) {
            blacksquraX =
                (Math.random() * (screenwidth - blacksquare.width - 2 * marginVertical)).toInt() + marginVertical
            blacksquareY = -blacksquare.height // Üstten başla
            blacksquare.setImageResource(wallOneIDs.random())
            blacksquarePassed = false // Reset the flag when the object moves out of the screen
        }
        blacksquare.x = blacksquraX.toFloat()
        blacksquare.y = blacksquareY.toFloat()

        // Kırmızı Üçgen hareketini düzenle
        redtriangleY += redtrianglespeed
        if (redtriangleY > screenhight) {
            redtriangleX =
                (Math.random() * (screenwidth - redtriangle.width - 2 * marginVertical)).toInt() + marginVertical
            redtriangleY = -redtriangle.height // Üstten başla
            redtriangle.setImageResource(wallOneIDs.random())
            redtrianglePassed = false // Reset the flag when the object moves out of the screen
        }
        redtriangle.x = redtriangleX.toFloat()
        redtriangle.y = redtriangleY.toFloat()


        // Sarı Daire hareketini düzenle
        yellowcircleY += yellowcirclespeed // Aşağı doğru hareket et
        if (yellowcircleY > screenhight) {
            yellowcircleX =
                (Math.random() * (screenwidth - yellowcircle.width - 2 * marginVertical)).toInt() + marginVertical
            yellowcircleY = -yellowcircle.height // Üstten başla
        }

        // Check for overlapping with kirmiziucgen and siyahkare
        val saridaireRect = Rect(
            yellowcircleX,
            yellowcircleY,
            yellowcircleX + yellowcircle.width,
            yellowcircleY + yellowcircle.height
        )
        val kirmiziucgenRect = Rect(
            redtriangleX,
            redtriangleY,
            redtriangleX + redtriangle.width,
            redtriangleY + redtriangle.height
        )
        val siyahkareRect = Rect(
            blacksquraX,
            blacksquareY,
            blacksquraX + blacksquare.width,
            blacksquareY + blacksquare.height
        )

        // If there is an overlap with kirmiziucgen or siyahkare, reposition the saridaire
        while (Rect.intersects(saridaireRect, kirmiziucgenRect) || Rect.intersects(
                saridaireRect,
                siyahkareRect
            )
        ) {
            yellowcircleX =
                (Math.random() * (screenwidth - yellowcircle.width - 2 * marginVertical)).toInt() + marginVertical
            yellowcircleY = -yellowcircle.height // Üstten başla

            saridaireRect.set(
                yellowcircleX,
                yellowcircleY,
                yellowcircleX + yellowcircle.width,
                yellowcircleY + yellowcircle.height
            )
        }

        yellowcircle.x = yellowcircleX.toFloat()
        yellowcircle.y = yellowcircleY.toFloat()

        // Check for overlapping with saridaire and siyahkare
        //val siyahkareRect = Rect(siyahkareX, siyahkareY, siyahkareX + siyahkare.width, siyahkareY + siyahkare.height)

        // If there is an overlap with saridaire or siyahkare, reposition the kirmiziucgen
        while (Rect.intersects(kirmiziucgenRect, saridaireRect) || Rect.intersects(
                kirmiziucgenRect,
                siyahkareRect
            )
        ) {
            redtriangleX =
                (Math.random() * (screenwidth - redtriangle.width - 2 * marginVertical)).toInt() + marginVertical
            redtriangleY = -redtriangle.height // Üstten başla

            kirmiziucgenRect.set(
                redtriangleX,
                redtriangleY,
                redtriangleX + redtriangle.width,
                redtriangleY + redtriangle.height
            )
        }

        redtriangle.x = redtriangleX.toFloat()
        redtriangle.y = redtriangleY.toFloat()
    }


    private var oyunBitti: Boolean = false
    private fun collisionControl() {

        updateScore(score)
        val shopFragment = ShopFragment()
        val bundle = Bundle()
        bundle.putInt("score", score)
        shopFragment.arguments = bundle
        val anakarakterSolKenar = maincarackterX
        val anakarakterSagKenar = maincarackterX + maincarackterwidth
        val anakarakterUstKenar = maincarackterY
        val anakarakterAltKenar = maincarackterY + maincarackterhight

        val sariDaireSolKenar = yellowcircleX
        val sariDaireSagKenar = yellowcircleX + yellowcircle.width
        val sariDaireUstKenar = yellowcircleY
        val sariDaireAltKenar = yellowcircleY + yellowcircle.height



        if (anakarakterSagKenar >= sariDaireSolKenar && anakarakterSolKenar <= sariDaireSagKenar &&
            anakarakterAltKenar >= sariDaireUstKenar && anakarakterUstKenar <= sariDaireAltKenar
        ) {
            //skor += 10
            score += 1
            // score += 10

            yellowcircleY = -yellowcircle.height // Start from the top
            yellowcircleX =
                (Math.random() * (screenwidth - yellowcircle.width - 2 * marginVertical)).toInt() + marginVertical
        }
        val kirmiziucgenSolKenar = redtriangleX
        val kirmiziucgenSagKenar = redtriangleX + redtriangle.width
        val kirmiziucgenUstKenar = redtriangleY
        val kirmiziucgenAltKenar = redtriangleY + redtriangle.height


        if (anakarakterSagKenar >= kirmiziucgenSolKenar && anakarakterSolKenar <= kirmiziucgenSagKenar &&
            anakarakterAltKenar >= kirmiziucgenUstKenar && anakarakterUstKenar <= kirmiziucgenAltKenar
        ) {


            oyunBitti = true
            // Timer durdur.

        }
        // Check if maincarakter passed redtriangle
        if (anakarakterUstKenar < kirmiziucgenAltKenar && !redtrianglePassed) {
            // maincarakter passed redtriangle
            redtrianglePassed = true
            skor += 5
        }




        val siyahKareSolKenar = blacksquraX
        val siyahKareSagKenar = blacksquraX + blacksquare.width
        val siyahKareUstKenar = blacksquareY
        val siyahKareAltKenar = blacksquareY + blacksquare.height

        if (anakarakterSagKenar >= siyahKareSolKenar && anakarakterSolKenar <= siyahKareSagKenar &&
            anakarakterAltKenar >= siyahKareUstKenar && anakarakterUstKenar <= siyahKareAltKenar
        ) {

            oyunBitti = true
            // Timer durdur.


        }
        // Check if maincarakter passed blacksquare
        if (anakarakterUstKenar < siyahKareAltKenar && !blacksquarePassed) {
            // maincarakter passed blacksquare
            blacksquarePassed = true
            skor += 5
        }

        if (oyunBitti) {

            gameOverSound?.start()
            timer?.cancel()
            timer = null
            val bestscore=CharacterManager.bestScore
            showNameDialog(skor, skor)
        }
        textViewSkor.text = skor.toString()
        tvcointext.text = score.toString()

    }

    private fun showNameDialog(score: Int, bestScore: Int) {
        isShowedDialog = true
        val dialog = androidx.appcompat.app.AlertDialog.Builder(this)
        val editText = EditText(this)
        dialog.setView(editText)
        dialog.setCancelable(false)
        val alert = dialog.create()
        alert.setTitle(getString(R.string.enter_play_name))
        alert.setButton(
            androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE,
            getString(R.string.save)
        ) { _: DialogInterface, i: Int ->
            Utils.playerName = editText.text.toString()
            viewModel.insertLeader(
                Leader(
                    name = editText.text.toString(),
                    score = skor
                )
            )
            viewModel.getLeaders()
            showGameOverDialog(score, bestScore)
        }
        alert.setButton(
            androidx.appcompat.app.AlertDialog.BUTTON_NEGATIVE,
            getString(R.string.home)
        ) { _: DialogInterface, i: Int ->
            //  binding.gameView.reset()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        alert.show()
    }

    private fun showGameOverDialog(score: Int, bestScore: Int) {
        val dialog = androidx.appcompat.app.AlertDialog.Builder(this)
        dialog.setCancelable(false)
        val alert = dialog.create()
        alert.setTitle(getString(R.string.gameover))
        alert.setMessage(getString(R.string.score_format, score, bestScore))
        alert.setButton(
            androidx.appcompat.app.AlertDialog.BUTTON_POSITIVE,
            getString(R.string.home)
        ) { _: DialogInterface, i: Int ->
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        alert.setButton(
            androidx.appcompat.app.AlertDialog.BUTTON_NEGATIVE,
            getString(R.string.leaderboard)
        ) { _: DialogInterface, i: Int ->
            alert.dismiss()
            leaderboardBinding.root.visibility = View.VISIBLE


            // binding.homeIc.isClickable = false
        }

        alert.show()
    }

    private fun updateScore(newScore: Int) {
        CharacterManager.score = newScore
    }

    private fun gameStop() {
        gameStopp = true
        timer?.cancel()
        timer = null
        showPauseDialog()


    }

    private fun showPauseDialog() {
        val dialogView= LayoutInflater.from(this).inflate(R.layout.ic_resume_dialog,null)
        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val btnResume = dialogView.findViewById<TextView>(R.id.tv_resume)
        val btnHome = dialogView.findViewById<ImageView>(R.id.iv_home)

        btnResume.setOnClickListener {
            isGamePaused = false
            // Restart the game loop
            timer = Timer()
            timer?.schedule(object : TimerTask() {
                override fun run() {
                    handler.post {
                        if (!isGamePaused) {
                            mainCarackterMove()
                            moveObjects()
                            collisionControl()
                        }
                    }
                }
            }, 0, 20)
            dialog.dismiss()

        }
        btnHome.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            dialog.dismiss()
        }
        dialog.setCancelable(false)
        dialog.show()


        }

    }



