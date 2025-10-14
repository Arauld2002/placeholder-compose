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
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor

/**
 * A [PlaceholderHighlight] implementation that fades a solid color in and out.
 *
 * This creates a simple breathing effect by animating the opacity of a highlight color
 * over the placeholder. The fade effect is subtle and works well for most content types.
 *
 * Example usage:
 * ```kotlin
 * Box(
 *   modifier = Modifier
 *     .size(100.dp)
 *     .placeholder(
 *       visible = isLoading,
 *       highlight = Fade(
 *         highlightColor = Color.White.copy(alpha = 0.5f),
 *         animationSpec = infiniteRepeatable(
 *           animation = tween(durationMillis = 800),
 *           repeatMode = RepeatMode.Reverse
 *         )
 *       )
 *     )
 * )
 * ```
 *
 * @param highlightColor The color of the fade overlay
 * @param animationSpec The animation specification controlling the fade timing
 * @see PlaceholderDefaults.fade for a default configuration
 */
public data class Fade(
  private val highlightColor: Color,
  override val animationSpec: InfiniteRepeatableSpec<Float>,
) : PlaceholderHighlight {
  private val brush = SolidColor(highlightColor)

  /**
   * Return a [Brush] to draw for the given [progress] and [size].
   *
   * @param progress the current animated progress in the range of 0f..1f.
   * @param size The size of the current layout to draw in.
   */
  override fun brush(progress: Float, size: Size): Brush = brush

  /**
   * Return the desired alpha value used for drawing the [Brush] returned from [brush].
   *
   * @param progress the current animated progress in the range of 0f..1f.
   */
  override fun alpha(progress: Float): Float = progress
}
