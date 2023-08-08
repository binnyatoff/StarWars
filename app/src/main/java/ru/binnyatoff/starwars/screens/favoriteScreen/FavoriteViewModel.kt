package ru.binnyatoff.starwars.screens.favoriteScreen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.binnyatoff.starwars.data.database.Dao
import ru.binnyatoff.starwars.data.database.ResultMainDTO
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private var dao: Dao) : ViewModel() {
    private val _viewState = MutableLiveData<FavoriteState>()
    val viewState: LiveData<FavoriteState> = _viewState


    fun obtainEvent(event: FavoriteEvent) {
        when (event) {
            is FavoriteEvent.ScreenInit -> getFromDatabase()
            is FavoriteEvent.DeleteItem -> {
                deleteItem(event.resultMainDTO)
                getFromDatabase()
            }
        }
    }

    private fun getFromDatabase() {
        viewModelScope.launch {
            val resultList = dao.selectResult()
            _viewState.postValue(FavoriteState.Loaded(resultList))
        }
    }
    private fun deleteItem(resultMainDTO: ResultMainDTO){
        viewModelScope.launch {
            dao.deleteResult(resultMainDTO)
        }
    }
}
