/*
 * Copyright (C) 2017 Adrián García
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.adriangl.devquicktiles.tiles.usbdebug

import android.graphics.drawable.Icon
import android.provider.Settings
import com.adriangl.devquicktiles.R
import com.adriangl.devquicktiles.tiles.DevelopmentTileService
import com.adriangl.devquicktiles.utils.SettingsUtils

class UsbDebuggingTileService : DevelopmentTileService<Int>() {
    companion object {
        val SETTING = Settings.Global.ADB_ENABLED
    }

    override fun isActive(value: Int): Boolean {
        return value != 0
    }

    override fun queryValue(): Int {
        var value = SettingsUtils.getIntSetting(contentResolver, SETTING)
        if (value > 1) value = 1
        return value
    }

    override fun saveValue(value: Int) {
        SettingsUtils.setIntSetting(contentResolver, SETTING, value)
    }

    override fun getValueList(): List<Int> {
        return listOf(0, 1)
    }

    override fun getIcon(value: Int): Icon? {
        return Icon.createWithResource(applicationContext,
                if (value != 0) R.drawable.ic_qs_usb_debugging_enabled else R.drawable.ic_qs_usb_debugging_disabled)
    }

    override fun getLabel(value: Int): CharSequence? {
        return getString(R.string.qs_usb_debugging)
    }
}
