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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor

/**
 * A [PlaceholderHighlight] that pulses the highlight color in and out.
 *
 * This is achieved by animating the alpha of a solid color brush.
 *
 * @param highlightColor The color of the pulse highlight.
 * @param animationSpec The specification for the animation.
 */
public data class Pulse(
  private val highlightColor: Color,
  override val animationSpec: InfiniteRepeatableSpec<Float>,
) : PlaceholderHighlight {
  private val brush = SolidColor(highlightColor)

  override fun brush(
    @FloatRange(from = 0.0, to = 1.0) progress: Float,
    size: Size,
  ): Brush = brush

  /**
   * The alpha value is directly driven by the animation progress.
   */
  override fun alpha(
    @FloatRange(from = 0.0, to = 1.0) progress: Float,
  ): Float = progress
}
