package com.example.recyclerviewjsonarray

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.recyclerviewjsonarray.model.NewsList
import org.junit.runner.RunWith
import com.example.recyclerviewjsonarray.ui.NewsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class NewsCoroutineTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineRule = CoroutineRule()

    @Mock
    private lateinit var apiUsersObserver: Observer<NewsList>


    @ExperimentalCoroutinesApi
    @Test
    fun test_coroutine_working_fine() {
        coroutineRule.runBlockingTest {
            val viewModel = NewsViewModel()
            viewModel.newsListObserver()
            viewModel.newsListObserver().removeObserver(apiUsersObserver)
        }
    }


}