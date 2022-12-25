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

    private val _entries = MutableStateFlow<List<Entrie>>(mutableStateListOf())
    val entries = _entries.asStateFlow()

    fun getFilteredApiList(text:String):ArrayList<Entrie>{
        var filteredList = ArrayList<Entrie>()

        for(ent in apis.value.entries){
            if (ent.API.lowercase().contains(text.lowercase())){
                filteredList.add(ent)
            }
        }
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
        }
    }
}