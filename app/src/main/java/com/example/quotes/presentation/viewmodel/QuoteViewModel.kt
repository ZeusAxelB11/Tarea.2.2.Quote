package com.example.quotes.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import edu.itvo.quotes.domain.model.QuoteModel
import edu.itvo.quotes.domain.usecase.GetQuoteRandomUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

/*class QuoteViewModel : ViewModel() {

    private val _quoteModel = MutableStateFlow(QuoteModel("",""))
    val quoteModel: StateFlow<QuoteModel> = _quoteModel

    //---  Load data from a suspend fun and mutate state
    fun randomQuote() {
        viewModelScope.launch {
            val quote= QuoteProvider.random()
            _quoteModel.value = quote
        }
    }
}*/
@HiltViewModel
class QuoteViewModel
@Inject constructor(private val getQuoteRandomUseCase: GetQuoteRandomUseCase
): ViewModel() {

    private val quoteModelRandomMutableStateFlow = MutableStateFlow(QuoteModel(0,"",""))
    val quoteModel: StateFlow<QuoteModel> = quoteModelRandomMutableStateFlow


    fun randomQuote() {
        viewModelScope.launch {
            quoteModelRandomMutableStateFlow.value = getQuoteRandomUseCase.getQuoteRandom().first()
            //_quoteModel.value = GetQuoteUseCase(quoteDAO).getQuote(1).first()
        }
    }
}