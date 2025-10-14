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

import androidx.compose.animation.core.InfiniteRepeatableSpec
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import kotlin.math.max
import kotlin.math.min
import kotlin.math.tan

/**
 * A [PlaceholderHighlight] that applies a shimmer effect.
 *
 * This implementation is based on the concepts from the reference shimmer composable,
 * adapting its properties like intensity, dropOff, and tilt for use with a `Brush`.
 *
 * @param highlightColor The color of the shimmer highlight.
 * @param animationSpec The animation spec for the shimmer progress.
 * @param intensity Controls the brightness of the highlight at the center. Values are typically between 0f and 1f.
 * @param dropOff Controls the size of the fading edge of the highlight. Values are typically between 0f and 1f.
 * @param tilt The angle at which the highlight is tilted, in degrees.
 * @param durationMillis The duration of one shimmer animation cycle.
 */
public data class Shimmer(
  private val highlightColor: Color,
  override val animationSpec: InfiniteRepeatableSpec<Float> = infiniteRepeatable(
    animation = tween(durationMillis = 650, easing = LinearEasing),
  ),
  private val intensity: Float = 0f,
  private val dropOff: Float = 0.5f,
  private val tilt: Float = 20f,
) : PlaceholderHighlight {

  override fun brush(
    progress: Float,
    size: Size,
  ): Brush {
    val shimmerColorStops = arrayOf(
      max((1f - intensity - dropOff) / 2f, 0f) to Color.Transparent,
      max((1f - intensity - 0.001f) / 2f, 0f) to highlightColor,
      min((1f + intensity + 0.001f) / 2f, 1f) to highlightColor,
      min((1f + intensity + dropOff) / 2f, 1f) to Color.Transparent,
    )
    val tiltRad = tilt * 3.14f / 180f
    val totalWidth = size.width + tan(tiltRad) * size.height
    val dx = offset(-totalWidth, totalWidth * 2f, progress)
    val dy = 0f
    val start = Offset(dx - (totalWidth / 2f), dy)

    return Brush.linearGradient(
      colorStops = shimmerColorStops,
      start = start,
      end = Offset(start.x + totalWidth, size.height),
    )
  }

  override fun alpha(
    progress: Float,
  ): Float = 1.0f

  private fun offset(start: Float, end: Float, percent: Float): Float {
    return start + (end - start) * percent
  }
}
