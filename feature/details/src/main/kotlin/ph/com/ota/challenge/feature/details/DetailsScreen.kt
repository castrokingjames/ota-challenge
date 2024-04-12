/*
* Copyright 2024
*/
@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalGlideComposeApi::class)

package ph.com.ota.challenge.feature.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.compose.collectAsState
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import ph.com.ota.challenge.feature.details.DetailsScreenViewModel.Companion.detailsScreenViewModel
import ph.com.ota.challenge.ui.compose.component.LoadingView
import ph.com.ota.challenge.ui.compose.theme.Typography
import ph.com.ota.challenge.ui.glide.PdfUrl

@Composable
fun DetailsScreen(
  activityId: String,
  onBackNavigationSelected: () -> Unit,
) {
  val viewModel: DetailsScreenViewModel = detailsScreenViewModel(activityId)
  val state = viewModel
    .collectAsState()
    .value
  val activity = state.activity
  val loading = state.loading

  Scaffold(
    topBar = {
      Toolbar(
        activity?.title ?: "",
        onBackNavigationSelected,
      )
    },
  ) { padding ->
    Box(
      modifier = Modifier
        .fillMaxSize()
        .padding(padding),
    ) {
      if (loading is Loading) {
        LoadingView(
          modifier = Modifier.align(Alignment.Center),
        )
      } else if (loading is Success && activity != null) {
        Column(
          modifier = Modifier.align(Alignment.TopCenter),
        ) {
          GlideImage(
            modifier = Modifier
              .align(Alignment.CenterHorizontally)
              .size(150.dp),
            model = PdfUrl(activity.icon.active),
            contentDescription = null,
          )
          Spacer(
            modifier = Modifier.size(16.dp),
          )
          Text(
            text = activity.description,
            textAlign = TextAlign.Center,
            style = Typography.regular,
            modifier = Modifier
              .align(Alignment.CenterHorizontally)
              .padding(
                start = 32.dp,
                end = 32.dp,
              ),
            color = MaterialTheme.colorScheme.onPrimary,
          )
        }
      }
    }
  }
}

@Composable
fun Toolbar(
  title: String,
  onBackNavigationSelected: () -> Unit,
) {
  Column {
    TopAppBar(
      title = {
        Text(
          text = title,
          color = MaterialTheme.colorScheme.onPrimary,
        )
      },
      navigationIcon = {
        IconButton(
          onClick = onBackNavigationSelected,
        ) {
          Icon(
            Icons.AutoMirrored.Filled.ArrowBack,
            "ArrowBack",
            tint = MaterialTheme.colorScheme.onPrimary,
          )
        }
      },
    )
    HorizontalDivider(
      thickness = 0.1.dp,
      color = MaterialTheme.colorScheme.primaryContainer,
    )
  }
}
