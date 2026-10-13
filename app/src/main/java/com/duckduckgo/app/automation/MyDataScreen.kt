/*
 * Copyright (c) 2025 DuckDuckGo
 * ... (license header) ...
 */

package com.duckduckgo.app.automation

import com.duckduckgo.app.browser.R
import com.duckduckgo.mobile.android.R.drawable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.duckduckgo.common.ui.compose.theme.DuckDuckGoTheme

@Composable
fun MyDataScreen(
    onBack: () -> Unit,
    onAddClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DuckDuckGoTheme.colors.backgrounds.background),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(DuckDuckGoTheme.colors.backgrounds.surface)
                .padding(horizontal = 8.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    painter = painterResource(drawable.ic_arrow_left_24),
                    contentDescription = stringResource(R.string.automation_back),
                    tint = DuckDuckGoTheme.colors.icons.primary,
                )
            }
            Text(
                text = stringResource(R.string.automation_my_data),
                color = DuckDuckGoTheme.colors.text.primary,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f),
            )
            IconButton(onClick = onAddClick) {
                Icon(
                    painter = painterResource(drawable.ic_add_24),
                    contentDescription = stringResource(R.string.automation_add_data),
                    tint = DuckDuckGoTheme.colors.icons.primary,
                )
            }
        }

        val groups = VaultRepository.groups.value
        if (groups.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = stringResource(R.string.automation_no_data),
                    color = DuckDuckGoTheme.colors.text.secondary,
                    fontSize = 15.sp,
                )
            }
        } else {
            Column(modifier = Modifier.padding(16.dp)) {
                groups.forEach { group ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = DuckDuckGoTheme.colors.backgrounds.surface,
                                shape = RoundedCornerShape(12.dp),
                            )
                            .clickable { }
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(
                            painter = painterResource(drawable.ic_bookmarks_24),
                            contentDescription = null,
                            tint = DuckDuckGoTheme.colors.icons.primary,
                            modifier = Modifier.size(24.dp),
                        )
                        Spacer(Modifier.width(16.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = group.name,
                                color = DuckDuckGoTheme.colors.text.primary,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium,
                            )
                            Text(
                                text = stringResource(R.string.automation_data_sets_count, group.entries.size),
                                color = DuckDuckGoTheme.colors.text.secondary,
                                fontSize = 13.sp,
                            )
                        }
                    }
                    Spacer(Modifier.height(12.dp))
                }
            }
        }
    }
}