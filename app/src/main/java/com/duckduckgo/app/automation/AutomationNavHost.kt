/*
 * Copyright (c) 2025 DuckDuckGo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.duckduckgo.app.automation

import com.duckduckgo.app.browser.R
import com.duckduckgo.mobile.android.R.drawable
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

sealed interface AutomationScreen {
    object Home : AutomationScreen
    object MyData : AutomationScreen
    object AddData : AutomationScreen
}

@Composable
fun AutomationNavHost(onBack: () -> Unit) {
    var current by remember { mutableStateOf<AutomationScreen>(AutomationScreen.Home) }

    BackHandler {
        when (current) {
            AutomationScreen.Home -> onBack()
            else -> current = AutomationScreen.Home
        }
    }

    when (current) {
        AutomationScreen.Home -> AutomationHomeScreen(
            onBack = onBack,
            onMyDataClick = { current = AutomationScreen.MyData },
            onAddDataClick = { current = AutomationScreen.AddData },
            onCreateAutomationClick = { /* Phase 3 */ },
            onViewLogsClick = { /* Phase 3 */ },
        )
        AutomationScreen.MyData -> MyDataScreen(
            onBack = { current = AutomationScreen.Home },
            onAddClick = { current = AutomationScreen.AddData },
        )
        AutomationScreen.AddData -> AddDataChoiceScreen(
            onBack = { current = AutomationScreen.Home },
            onPasteClick = { /* Phase 2 */ },
            onUploadClick = { /* Phase 2 */ },
            onCreateGroupClick = { /* Phase 2 */ },
        )
    }
}