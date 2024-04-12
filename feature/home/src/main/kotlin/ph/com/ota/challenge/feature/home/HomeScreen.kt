/*
* Copyright 2024
*/
@file:OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)

package ph.com.ota.challenge.feature.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import ph.com.ota.challenge.feature.home.HomeScreenViewModel.Companion.homeScreenViewModel
import ph.com.ota.challenge.feature.home.milestone.MilestoneView
import ph.com.ota.challenge.ui.R
import ph.com.ota.challenge.ui.compose.theme.Typography

@Composable
fun HomeScreen(
  name: String,
  onActivitySelected: (String) -> Unit,
) {
  val viewModel: HomeScreenViewModel = homeScreenViewModel(name)
  val name: String = viewModel.name
  val progress: Float = viewModel.progress
  val tabs: Array<String> = stringArrayResource(ph.com.ota.challenge.ui.R.array.schedules)
  val scope: CoroutineScope = rememberCoroutineScope()
  val pagerState: PagerState = rememberPagerState(pageCount = { tabs.size })
  val selectedTabIndex: MutableIntState = remember { mutableIntStateOf(pagerState.currentPage) }

  LaunchedEffect(pagerState) {
    snapshotFlow { pagerState.currentPage }.collect { index ->
      selectedTabIndex.value = index
    }
  }

  Scaffold(
    topBar = {
      Toolbar(name, progress)
    },
    bottomBar = {
      BottomBar()
    },
  ) { padding ->
    val spacing = padding.calculateTopPadding()
    Column(
      modifier = Modifier
        .fillMaxSize()
        .padding(
          top = spacing,
          bottom = spacing,
        ),
    ) {
      TabRow(
        selectedTabIndex = selectedTabIndex.value,
        modifier = Modifier.fillMaxWidth(),
        indicator = { tabPositions ->
          Box(
            modifier = Modifier
              .tabIndicatorOffset(tabPositions[selectedTabIndex.value])
              .height(3.dp)
              .background(color = Color(0xFF6442EF)),
          )
        },
        divider = {
          HorizontalDivider(
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.primaryContainer,
          )
        },
      ) {
        tabs.forEachIndexed { index, tab ->
          val selected = (selectedTabIndex.value == index)
          Column(
            modifier = Modifier
              .padding(8.dp)
              .clickable {
                scope.launch {
                  selectedTabIndex.value = index
                  pagerState.animateScrollToPage(index)
                }
              },
          ) {
            if (selected) {
              Box(
                modifier = Modifier
                  .size(16.dp)
                  .clip(CircleShape)
                  .background(
                    MaterialTheme.colorScheme.onPrimaryContainer,
                  )
                  .align(Alignment.CenterHorizontally),
              )
            } else {
              Box(
                modifier = Modifier
                  .size(16.dp)
                  .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.primaryContainer,
                    shape = CircleShape,
                  )
                  .align(Alignment.CenterHorizontally),
              )
            }
            Spacer(
              modifier = Modifier.size(12.dp),
            )
            Text(
              modifier = Modifier.align(Alignment.CenterHorizontally),
              text = tab.uppercase(),
              style = Typography.mediumXXSmall,
              color = if (selected) {
                MaterialTheme.colorScheme.onPrimaryContainer
              } else {
                MaterialTheme.colorScheme.primaryContainer
              },
            )
          }
        }
      }
      HorizontalPager(
        state = pagerState,
        modifier = Modifier
          .fillMaxWidth()
          .weight(1f),
      ) {
        val tab = tabs[selectedTabIndex.value]
        MilestoneView(tab, onActivitySelected)
      }
    }
  }
}

@Composable
fun Toolbar(
  name: String,
  progress: Float,
) {
  TopAppBar(
    title = {
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .padding(
            top = 16.dp,
            bottom = 16.dp,
          ),
      ) {
        Row {
          Image(
            modifier = Modifier
              .size(40.dp)
              .align(Alignment.CenterVertically),
            painter = painterResource(ph.com.ota.challenge.ui.R.drawable.ic_journey_40),
            contentDescription = null,
          )
          Spacer(
            modifier = Modifier.size(12.dp),
          )
          Column(
            modifier = Modifier.align(Alignment.CenterVertically),
          ) {
            Text(
              text = name,
              style = Typography.medium,
              color = MaterialTheme.colorScheme.onPrimary,
            )
            Spacer(
              modifier = Modifier.size(6.dp),
            )
            Row {
              LinearProgressIndicator(
                progress = { progress / 100F },
                modifier = Modifier
                  .size(70.dp, 8.dp)
                  .align(Alignment.CenterVertically),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                trackColor = MaterialTheme.colorScheme.primaryContainer,
                strokeCap = StrokeCap.Round,
              )
              Spacer(
                modifier = Modifier.size(6.dp),
              )
              Text(
                text = "${progress.toInt()}%",
                style = Typography.mediumXXXSmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
              )
            }
          }
        }
        Row(
          modifier = Modifier.align(Alignment.CenterEnd),
        ) {
          Image(
            modifier = Modifier.align(Alignment.CenterVertically),
            painter = painterResource(ph.com.ota.challenge.ui.R.drawable.ic_fire_24),
            contentDescription = null,
          )
          Spacer(
            modifier = Modifier.size(8.dp),
          )
          Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = "0",
            style = Typography.mediumSmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
          )
          Spacer(
            modifier = Modifier.size(16.dp),
          )
          ElevatedButton(
            onClick = {
            },
            modifier = Modifier.size(40.dp),
            contentPadding = PaddingValues(0.dp),
            colors = ButtonDefaults.buttonColors(
              containerColor = MaterialTheme.colorScheme.primary,
              contentColor = MaterialTheme.colorScheme.primary,
            ),
          ) {
            Image(
              painter = painterResource(R.drawable.ic_person_18),
              contentDescription = null,
            )
          }
          Spacer(
            modifier = Modifier.size(16.dp),
          )
        }
      }
    },
  )
}

@Composable
fun BottomBar() {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .background(
        color = MaterialTheme.colorScheme.primary,
      ),
  ) {
    Column(
      modifier = Modifier.align(Alignment.Center),
    ) {
      HorizontalDivider(
        thickness = 0.1.dp,
        color = MaterialTheme.colorScheme.primaryContainer,
      )
      Spacer(
        modifier = Modifier.size(8.dp),
      )
      Icon(
        modifier = Modifier.align(Alignment.CenterHorizontally),
        painter = painterResource(R.drawable.ic_flag_24),
        contentDescription = null,
        tint = MaterialTheme.colorScheme.onPrimaryContainer,
      )
      Spacer(
        modifier = Modifier.size(8.dp),
      )
      Text(
        text = stringResource(ph.com.ota.challenge.ui.R.string.journey),
        modifier = Modifier.align(Alignment.CenterHorizontally),
        textAlign = TextAlign.Center,
        style = Typography.regular,
        color = MaterialTheme.colorScheme.onPrimaryContainer,
      )
      Spacer(
        modifier = Modifier.size(8.dp),
      )
    }
  }
}
