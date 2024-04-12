/*
* Copyright 2024
*/
@file:OptIn(ExperimentalGlideComposeApi::class)

package ph.com.ota.challenge.feature.home.milestone

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.mvrx.Loading
import com.airbnb.mvrx.Success
import com.airbnb.mvrx.compose.collectAsState
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import ph.com.ota.challenge.feature.home.milestone.MilestoneViewModel.Companion.milestoneViewModel
import ph.com.ota.challenge.model.Activity
import ph.com.ota.challenge.model.Level
import ph.com.ota.challenge.ui.R
import ph.com.ota.challenge.ui.compose.component.LoadingView
import ph.com.ota.challenge.ui.compose.theme.Typography
import ph.com.ota.challenge.ui.glide.PdfUrl

@Composable
fun MilestoneView(
  id: String,
  onActivitySelected: (String) -> Unit,
) {
  val viewModel: MilestoneViewModel = milestoneViewModel(id)
  val state = viewModel
    .collectAsState()
    .value
  val milestone = state.milestone
  val loading = state.loading
  Box(
    modifier = Modifier.fillMaxSize(),
  ) {
    if (loading is Loading) {
      LoadingView(
        modifier = Modifier.align(Alignment.Center),
      )
    } else if (loading is Success || milestone != null) {
      LazyColumn {
        milestone
          ?.forEach { entry ->
            val level = entry.key
            val activities = entry.value
            item(
              key = level.id,
              contentType = level.javaClass,
            ) {
              LevelView(level)
            }

            activities(level, activities, onActivitySelected)
          }
      }
    }
  }
  LaunchedEffect(Unit) {
    viewModel.load()
  }
}

@Composable
fun LevelView(level: Level) {
  LevelView(
    level.number,
    level.title,
    level.description,
  )
}

@Preview
@Composable
fun LevelView(
  number: Int = 1,
  title: String = "",
  description: String = "",
) {
  Column(
    modifier = Modifier
      .padding(16.dp)
      .fillMaxWidth(),
  ) {
    Box(
      modifier = Modifier.align(Alignment.CenterHorizontally),
    ) {
      Image(
        modifier = Modifier
          .size(100.dp)
          .align(Alignment.Center),
        painter = painterResource(R.drawable.ic_chapter_100),
        contentDescription = null,
      )
      Text(
        text = stringResource(R.string.level_x)
          .format(number)
          .uppercase(),
        modifier = Modifier
          .align(Alignment.BottomCenter)
          .padding(6.dp),
        style = Typography.mediumXXSmall,
        color = MaterialTheme.colorScheme.onPrimary,
      )
    }
    Spacer(
      modifier = Modifier.size(16.dp),
    )
    Text(
      text = title,
      modifier = Modifier.align(Alignment.CenterHorizontally),
      textAlign = TextAlign.Center,
      style = Typography.medium,
      color = MaterialTheme.colorScheme.onPrimary,
    )
    Spacer(
      modifier = Modifier.size(16.dp),
    )
    Text(
      text = description,
      modifier = Modifier.align(Alignment.CenterHorizontally),
      textAlign = TextAlign.Center,
      style = Typography.regularXXSmall,
      color = MaterialTheme.colorScheme.onPrimary,
    )
  }
}

fun LazyListScope.activities(
  level: Level,
  activities: List<Activity>,
  onActivitySelected: (String) -> Unit,
) {
  val iterator = activities.iterator()
  while (iterator.hasNext()) {
    val first = iterator.next()
    if (iterator.hasNext()) {
      val second = iterator.next()
      item(
        key = "${first.id}|${second.id}",
        contentType = first.javaClass,
      ) {
        Row {
          Box(
            modifier = Modifier.weight(1f),
          ) {
            ActivityView(level.active, first, onActivitySelected)
          }
          Box(
            modifier = Modifier.weight(1f),
          ) {
            ActivityView(level.active, second, onActivitySelected)
          }
        }
      }
    } else {
      item(
        key = "${first.id}",
        contentType = first.javaClass,
      ) {
        Box {
          ActivityView(level.active, first, onActivitySelected)
        }
      }
    }
  }
}

@ExperimentalGlideComposeApi
@Composable
fun ActivityView(
  active: Boolean,
  activity: Activity,
  onActivitySelected: (String) -> Unit,
) {
  val url = if (active) {
    activity.icon.active
  } else {
    activity.icon.inactive
  }
  Column(
    modifier = Modifier
      .padding(16.dp)
      .fillMaxWidth()
      .clickable(active) {
        onActivitySelected(activity.id)
      },
  ) {
    Box(
      modifier = Modifier.align(Alignment.CenterHorizontally),
    ) {
      GlideImage(
        modifier = Modifier.size(100.dp),
        model = PdfUrl(url),
        contentDescription = null,
      )
    }
    Spacer(
      modifier = Modifier.size(10.dp),
    )
    Text(
      text = activity.title,
      textAlign = TextAlign.Center,
      style = Typography.regular,
      modifier = Modifier.align(Alignment.CenterHorizontally),
      color = MaterialTheme.colorScheme.onPrimary,
    )
  }
}
