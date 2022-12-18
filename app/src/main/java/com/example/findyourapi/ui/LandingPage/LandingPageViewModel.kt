package com.example.findyourapi.ui.LandingPage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findyourapi.model.APIs
import com.example.findyourapi.model.Categories
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

    fun getAPIs(){
        viewModelScope.launch{
            _apis.value = publicApiRepository.getApis()
        }
    }

    fun getCategories(){
        viewModelScope.launch{
            _categories.value = publicApiRepository.getCategories()
        }
    }
}