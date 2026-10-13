/*
 * Copyright (c) 2025 DuckDuckGo
 * ... (license header) ...
 */

package com.duckduckgo.app.automation

import com.duckduckgo.app.browser.R
import com.duckduckgo.mobile.android.R.drawable
import androidx.compose.runtime.mutableStateOf

data class VaultEntry(
    val fieldType: String,
    val value: String,
)

data class VaultGroup(
    val name: String,
    val entries: List<VaultEntry>,
)

object VaultRepository {
    val groups = mutableStateOf<List<VaultGroup>>(emptyList())

    fun addGroup(group: VaultGroup) {
        groups.value = groups.value + group
    }

    fun removeGroup(name: String) {
        groups.value = groups.value.filterNot { it.name == name }
    }
}