package com.ballflight6463.ui.play

import android.util.Log
import androidx.lifecycle.*
import com.ballflight6463.core.BaseApplication.Companion.db
import com.ballflight6463.models.Leader
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class PlayViewModel@Inject constructor() : ViewModel() {
    fun insertLeader(leader: Leader) {
        viewModelScope.launch {
            db.dao().insertLeader(leader)
        }
    }

    lateinit var leaderList : LiveData<List<Leader>>

    init {
        getLeaders()
    }

    fun getLeaders() {
        viewModelScope.launch {
            try {
                leaderList = db.dao().getAllLeaders().asLiveData()
            } catch (e: Exception) {
                Log.e("RatedVM","Ex:$e")
            }
        }
    }

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    // Diğer değişkenler ve fonksiyonlar...

    fun updateScore(newScore: Int) {
        _score.value = newScore
    }
}