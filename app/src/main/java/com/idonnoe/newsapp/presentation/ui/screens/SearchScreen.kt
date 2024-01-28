package com.idonnoe.newsapp.presentation.ui.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.idonnoe.newsapp.presentation.theme.CardBgColor
import com.idonnoe.newsapp.presentation.theme.DividerColor
import com.idonnoe.newsapp.presentation.theme.ScreenBgColor
import com.idonnoe.newsapp.presentation.theme.TextColor
import com.idonnoe.newsapp.presentation.ui.composables.ArticlesLoader
import com.idonnoe.newsapp.presentation.viewmodels.SearchViewModel

@Composable
fun SearchScreen(
    onItemClick: (url: String) -> Unit
) {

    val searchViewModel: SearchViewModel = hiltViewModel()
    val articlesState = searchViewModel.articles.collectAsState()
    val isFirstTime = searchViewModel.isFirstTime.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(ScreenBgColor)
    ) {
        Spacer(modifier = Modifier.height(5.dp))
        SearchBar { searchViewModel.searchArticlesByQuery(it) }
        Spacer(modifier = Modifier.height(5.dp))
        Divider(thickness = 2.dp, color = DividerColor)
        if (!isFirstTime.value) {
            ArticlesLoader(articles = articlesState.value) {
                onItemClick(it)
            }
        }
    }
}

@Composable
fun SearchBar(onSearchClick: (query: String) -> Unit) {

    val inputValue = remember { mutableStateOf(TextFieldValue()) }
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        OutlinedTextField(
            value = inputValue.value,
            onValueChange = { inputValue.value = it },
            placeholder = { Text(text = "Enter your query...") },
            textStyle = TextStyle(
                color = TextColor,
                fontSize = 18.sp
            ),
            maxLines = 1,
            singleLine = true,
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            ),
            modifier = Modifier.weight(1F)
        )
        Text(
            text = "SEARCH",
            color = TextColor,
            fontSize = 18.sp,
            modifier = Modifier
                .padding(5.dp)
                .background(CardBgColor)
                .padding(5.dp)
                .align(Alignment.CenterVertically)
                .clickable {
                    if (inputValue.value.text.isBlank()) {
                        Toast
                            .makeText(context, "Enter a query!", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        focusManager.clearFocus()
                        onSearchClick(inputValue.value.text)
                    }
                }
        )
    }
}