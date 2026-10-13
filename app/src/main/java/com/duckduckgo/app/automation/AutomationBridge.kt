/*
 * Copyright (c) 2025 DuckDuckGo
 * ... license header ...
 */

package com.duckduckgo.app.automation

import android.webkit.JavascriptInterface

class AutomationBridge {
    @JavascriptInterface
    fun onFieldsDetected(json: String) {
        AutomationEngine.onFieldsDetected(json)
    }
}