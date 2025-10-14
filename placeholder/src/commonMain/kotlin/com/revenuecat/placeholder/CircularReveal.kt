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
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import kotlin.math.max
import kotlin.math.sqrt

/**
 * A [PlaceholderHighlight] that draws a circular reveal effect.
 *
 * This highlight originates from the center of the placeholder, expanding outwards
 * in a circular ripple.
 *
 * @param highlightColor The color of the reveal circle.
 * @param animationSpec The specification for the animation.
 */
public data class CircularReveal(
  private val highlightColor: Color,
  override val animationSpec: InfiniteRepeatableSpec<Float>,
) : PlaceholderHighlight {

  override fun brush(
    @FloatRange(from = 0.0, to = 1.0) progress: Float,
    size: Size,
  ): Brush {
    val maxRadius = sqrt(size.width * size.width + size.height * size.height) / 2f
    val currentRadius = maxRadius * progress
    val effectiveRadius = max(currentRadius, 0.001f)
    return Brush.radialGradient(
      colors = listOf(highlightColor, highlightColor.copy(alpha = 0f)),
      center = size.center,
      radius = effectiveRadius,
    )
  }

  /**
   * The alpha of the entire highlight can also fade out as it expands.
   * This implementation fades out in the second half of the animation.
   */
  override fun alpha(
    @FloatRange(from = 0.0, to = 1.0) progress: Float,
  ): Float {
    return 1f - progress
  }
}
