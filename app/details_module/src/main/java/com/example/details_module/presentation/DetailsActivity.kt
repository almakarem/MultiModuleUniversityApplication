package com.example.details_module.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.details_module.presentation.components.UniversityDetail
import com.example.details_module.presentation.ui.theme.MultiModuleUniversityApplicationTheme
import com.example.presentation.University
import com.example.presentation.UniversityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MultiModuleUniversityApplicationTheme {
                var university = University(
                    country = "",
                    domains = listOf(""),
                    name = "",
                    stateProvince = "",
                    webPages = listOf(""),
                    alpha_two_code = ""
                )
                val intent = intent.getStringExtra("university")
                val viewModel = hiltViewModel<UniversityViewModel>()
                viewModel.universityPaginFlow.collectAsLazyPagingItems().itemSnapshotList.items.forEach{
                        item ->
                    if (item.name == intent)
                        university = item
                }
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    UniversityDetail(university = university,viewModel)
                }
            }
        }
    }
}