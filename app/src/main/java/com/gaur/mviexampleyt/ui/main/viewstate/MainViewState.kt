package com.gaur.mviexampleyt.ui.main.viewstate

import com.gaur.mviexampleyt.data.model.FakeDTO

sealed class MainViewState{
    object Idle : MainViewState()
    object Loading : MainViewState()
    class Error(val message:String) : MainViewState()
    class Success(val data:List<FakeDTO>): MainViewState()
}
