/*
 * Copyright (c) 2025 DuckDuckGo
 * ... (license header) ...
 */

package com.duckduckgo.app.automation

import com.duckduckgo.app.browser.R
import com.duckduckgo.mobile.android.R.drawable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.duckduckgo.common.ui.compose.theme.DuckDuckGoTheme

@Composable
fun AutomationHomeScreen(
    onBack: () -> Unit,
    onMyDataClick: () -> Unit,
    onAddDataClick: () -> Unit,
    onCreateAutomationClick: () -> Unit,
    onViewLogsClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DuckDuckGoTheme.colors.backgrounds.background)
            .verticalScroll(rememberScrollState()),
    ) {
        AutomationTopBar(
            title = stringResource(R.string.automation_home_title),
            subtitle = stringResource(R.string.automation_home_subtitle),
            onBack = onBack,
        )

        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            HeroCard(onAddDataClick = onAddDataClick)

            Spacer(Modifier.height(12.dp))

            InfoRowCard(
                iconRes = drawable.ic_bookmarks_24,
                title = stringResource(R.string.automation_my_data),
                subtitle = stringResource(R.string.automation_data_sets_count, VaultRepository.groups.value.size),
                onClick = onMyDataClick,
            )

            Spacer(Modifier.height(12.dp))

            InfoRowCard(
                iconRes = drawable.ic_reload_24,
                title = stringResource(R.string.automation_status),
                subtitle = stringResource(R.string.automation_status_not_running),
                onClick = onCreateAutomationClick,
            )

            Spacer(Modifier.height(24.dp))

            Text(
                text = stringResource(R.string.automation_quick_actions),
                color = DuckDuckGoTheme.colors.text.primary,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
            )

            Spacer(Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                QuickActionButton(
                    modifier = Modifier.weight(1f),
                    iconRes = drawable.ic_add_24,
                    label = stringResource(R.string.automation_create_automation),
                    onClick = onCreateAutomationClick,
                )
                QuickActionButton(
                    modifier = Modifier.weight(1f),
                    iconRes = drawable.ic_document_download_24,
                    label = stringResource(R.string.automation_view_logs),
                    onClick = onViewLogsClick,
                )
            }

            Spacer(Modifier.height(32.dp))
        }
    }
}

@Composable
private fun HeroCard(onAddDataClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = DuckDuckGoTheme.colors.backgrounds.surface,
                shape = RoundedCornerShape(16.dp),
            )
            .padding(16.dp),
    ) {
        Text(
            text = stringResource(R.string.automation_fill_forms_title),
            color = DuckDuckGoTheme.colors.text.primary,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = stringResource(R.string.automation_fill_forms_subtitle),
            color = DuckDuckGoTheme.colors.text.secondary,
            fontSize = 14.sp,
        )
        Spacer(Modifier.height(16.dp))
        Button(
            onClick = onAddDataClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = DuckDuckGoTheme.colors.brand.accentBlue,
                contentColor = Color.White,
            ),
            shape = RoundedCornerShape(12.dp),
        ) {
            Icon(
                painter = painterResource(drawable.ic_add_24),
                contentDescription = null,
                modifier = Modifier.size(20.dp),
            )
            Spacer(Modifier.width(8.dp))
            Text(text = stringResource(R.string.automation_add_data))
        }
    }
}

@Composable
private fun InfoRowCard(
    iconRes: Int,
    title: String,
    subtitle: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = DuckDuckGoTheme.colors.backgrounds.surface,
                shape = RoundedCornerShape(12.dp),
            )
            .clickable(onClick = onClick)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            painter = painterResource(iconRes),
            contentDescription = null,
            tint = DuckDuckGoTheme.colors.icons.primary,
            modifier = Modifier.size(24.dp),
        )
        Spacer(Modifier.width(16.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                color = DuckDuckGoTheme.colors.text.primary,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = subtitle,
                color = DuckDuckGoTheme.colors.text.secondary,
                fontSize = 14.sp,
            )
        }
    }
}

@Composable
private fun QuickActionButton(
    modifier: Modifier = Modifier,
    iconRes: Int,
    label: String,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .background(
                color = DuckDuckGoTheme.colors.backgrounds.surface,
                shape = RoundedCornerShape(12.dp),
            )
            .clickable(onClick = onClick)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            painter = painterResource(iconRes),
            contentDescription = null,
            tint = DuckDuckGoTheme.colors.brand.accentBlue,
            modifier = Modifier.size(24.dp),
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = label,
            color = DuckDuckGoTheme.colors.text.primary,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
        )
    }
}

@Composable
fun AutomationTopBar(title: String, subtitle: String? = null, onBack: () -> Unit) {
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
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                color = DuckDuckGoTheme.colors.text.primary,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
            )
            if (subtitle != null) {
                Text(
                    text = subtitle,
                    color = DuckDuckGoTheme.colors.text.secondary,
                    fontSize = 13.sp,
                )
            }
        }
    }
}