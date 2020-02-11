package com.swivel.sample_feature.ui.post

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.test.filters.SmallTest
import com.swivel.navigation.router.Router
import com.swivel.repository.post_service_repositories.PostRepository
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
@SmallTest
class PostViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var router: Router

    @Mock
    lateinit var postRepository: PostRepository

    lateinit var postViewModel : PostViewModel


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        postViewModel = PostViewModel(this.router,this.postRepository)
    }

    @Test
    fun myTest() {
        //postViewModel.fetchPosts()

        val mutableLiveData = MutableLiveData<String>()

        mutableLiveData.postValue("test")

        assertEquals("test", mutableLiveData.value)
    }


}