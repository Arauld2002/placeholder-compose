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

/**
 * Defines the visual highlight animation applied to a placeholder.
 *
 * A placeholder highlight provides animated visual effects that appear on top of
 * placeholder content to indicate loading state. Common implementations include
 * [Shimmer], [Fade], [Pulse], [CircularReveal], and [LightReveal].
 *
 * Implementations should define:
 * - How the highlight is drawn via [brush]
 * - The opacity of the highlight via [alpha]
 * - The animation timing via [animationSpec]
 *
 * Example usage:
 * ```kotlin
 * Box(
 *   modifier = Modifier
 *     .size(200.dp, 50.dp)
 *     .placeholder(
 *       visible = isLoading,
 *       highlight = PlaceholderDefaults.shimmer
 *     )
 * )
 * ```
 *
 * @see PlaceholderDefaults for default highlight implementations
 */
public interface PlaceholderHighlight {
  /**
   * The animation specification for the highlight effect.
   *
   * Defines the timing, easing, and repeat behavior of the highlight animation.
   * If null, the highlight will be static (no animation).
   */
  public val animationSpec: InfiniteRepeatableSpec<Float>?

  /**
   * Returns the brush to draw the highlight at the current animation progress.
   *
   * @param progress The current animation progress in the range 0.0 to 1.0
   * @param size The size of the placeholder being drawn
   * @return A [Brush] defining the highlight appearance
   */
  public fun brush(@FloatRange(from = 0.0, to = 1.0) progress: Float, size: Size): Brush

  /**
   * Returns the alpha (opacity) of the highlight at the current animation progress.
   *
   * This allows the highlight to fade in/out during the animation cycle.
   *
   * @param progress The current animation progress in the range 0.0 to 1.0
   * @return The alpha value in the range 0.0 (transparent) to 1.0 (opaque)
   */
  @FloatRange(from = 0.0, to = 1.0)
  public fun alpha(progress: Float): Float
}
