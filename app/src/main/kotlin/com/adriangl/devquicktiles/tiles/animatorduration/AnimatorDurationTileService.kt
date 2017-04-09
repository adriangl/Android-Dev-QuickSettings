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

package com.adriangl.devquicktiles.tiles.animatorduration

import android.graphics.drawable.Icon
import android.provider.Settings
import com.adriangl.devquicktiles.R
import com.adriangl.devquicktiles.tiles.DevelopmentTileService
import com.adriangl.devquicktiles.utils.SettingsUtils

class AnimatorDurationTileService : DevelopmentTileService<Float>() {
    companion object {
        val SETTING = Settings.Global.ANIMATOR_DURATION_SCALE
    }

    override fun isActive(value: Float): Boolean {
        return value != 0f
    }

    override fun queryValue(): Float {
        return SettingsUtils.getFloatSetting(contentResolver, SETTING)
    }

    override fun saveValue(value: Float) {
        SettingsUtils.setFloatSetting(contentResolver, SETTING, value)
    }

    override fun getValueList(): List<Float> {
        return listOf(0f, 0.5f, 1f, 1.5f, 2f, 5f, 10f)
    }

    override fun getIcon(value: Float): Icon? {
        var iconResource = R.drawable.ic_qs_animator_duration_enabled
        if (value <= 0f) {
            iconResource = R.drawable.ic_qs_animator_duration_disabled
        } else if (value <= 0.5f) {
            iconResource = R.drawable.ic_qs_animator_duration_half_x
        } else if (value <= 1f) {
            iconResource = R.drawable.ic_qs_animator_duration_1x
        } else if (value <= 1.5f) {
            iconResource = R.drawable.ic_qs_animator_duration_1_5x
        } else if (value <= 2f) {
            iconResource = R.drawable.ic_qs_animator_duration_2x
        } else if (value <= 5f) {
            iconResource = R.drawable.ic_qs_animator_duration_5x
        } else if (value <= 10f) {
            iconResource = R.drawable.ic_qs_animator_duration_10x
        }

        return Icon.createWithResource(applicationContext, iconResource)
    }

    override fun getLabel(value: Float): CharSequence? {
        var stringResource = R.string.qs_animator_duration
        if (value <= 0f) {
            stringResource = R.string.qs_animator_off
        } else if (value <= 0.5f) {
            stringResource = R.string.qs_animator_scale_0_5x
        } else if (value <= 1f) {
            stringResource = R.string.qs_animator_scale_1x
        } else if (value <= 1.5f) {
            stringResource = R.string.qs_animator_scale_1_5x
        } else if (value <= 2f) {
            stringResource = R.string.qs_animator_scale_2x
        } else if (value <= 5f) {
            stringResource = R.string.qs_animator_scale_5x
        } else if (value <= 10f) {
            stringResource = R.string.qs_animator_scale_10x
        }

        return getString(stringResource)
    }
}