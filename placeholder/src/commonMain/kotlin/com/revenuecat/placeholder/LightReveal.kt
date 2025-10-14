/*
 * Copyright (c) 2025 RevenueCat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.revenuecat.placeholder

import androidx.annotation.FloatRange
import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush

/**
 * A [PlaceholderHighlight] that draws a liquid, flowing shimmer effect.
 *
 * This effect simulates a wavy, distorted band of light moving across the placeholder,
 * giving it an organic and high-quality feel. It is achieved by composing multiple
 * animated gradients.
 *
 * @param highlightColor The primary color of the liquid shimmer.
 * @param animationSpec The specification for the animation.
 */
public data class LightReveal(
  private val highlightColor: Color,
  override val animationSpec: InfiniteRepeatableSpec<Float>,
) : PlaceholderHighlight {

  override fun brush(
    @FloatRange(from = 0.0, to = 1.0) progress: Float,
    size: Size,
  ): Brush {
    val translation = size.width * 2f * (progress - 0.5f)
    return object : ShaderBrush() {
      override fun createShader(size: Size): Shader {
        val largeRadial = RadialGradientShader(
          center = Offset(x = translation, y = size.height / 2f),
          radius = size.width * 1.5f,
          colors = listOf(
            highlightColor.copy(alpha = 0.8f),
            highlightColor.copy(alpha = 0.4f),
            Color.Transparent,
          ),
          colorStops = listOf(0f, 0.4f, 1f),
        )
        return largeRadial
      }
    }
  }

  override fun alpha(
    @FloatRange(from = 0.0, to = 1.0) progress: Float,
  ): Float = 1.0f
}
