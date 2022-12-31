package com.example.findyourapi.ui.LandingPage

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findyourapi.model.APIs
import com.example.findyourapi.model.Categories
import com.example.findyourapi.model.Entrie
import com.example.findyourapi.service.repository.PublicApiRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class LandingPageViewModel @Inject constructor(
    private val publicApiRepository: PublicApiRepository
) :ViewModel() {

    private val _apis = MutableStateFlow<APIs>(APIs(0, emptyList()))
    val apis = _apis.asStateFlow()

    private val _categories = MutableStateFlow<Categories>(Categories(0, emptyList()))
    val categories = _categories.asStateFlow()

    private var _entries = MutableStateFlow<List<Entrie>>(mutableStateListOf())
    val entries = _entries.asStateFlow()

    private var _isFiltered = MutableStateFlow<Boolean>(false)
    val isFiltered = _isFiltered.asStateFlow()

    fun getSearchedApiList(text:String):ArrayList<Entrie>{
        var filteredList = ArrayList<Entrie>()

        for(ent in apis.value.entries){
            if (ent.API.lowercase().contains(text.lowercase())){
                filteredList.add(ent)
            }
        }
        _entries.value=filteredList
        return filteredList
    }

    fun getAPIs(){
        viewModelScope.launch{
            _apis.value = publicApiRepository.getApis()
            _entries.value = _apis.value.entries
        }
    }

    fun getCategories(){
        viewModelScope.launch{
            _categories.value = publicApiRepository.getCategories()
            // add kora lagbe
        }
    }

    fun changeFilterStatus(){
        _isFiltered.value = !isFiltered.value
    }

    fun applyFilter(sortOrder:String,sortCategory:String){
        _isFiltered.value=false

        var filteredList = ArrayList<Entrie>()

        for (ent in apis.value.entries){
            if (ent.Category.lowercase().contentEquals(sortCategory)){
                filteredList.add(ent)
            }
        }
        when(sortOrder.lowercase()){
            "asending" -> _entries.value = filteredList.sortedWith(compareBy({ it.API }));
            "desending" -> _entries.value = filteredList.sortedByDescending{it.API};
            "default" -> _entries.value = filteredList

        }
        if (sortOrder.isNotEmpty()){

        }

    }
}