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

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.ui.graphics.Color

/**
 * Contains the default [PlaceholderHighlight] implementations for common placeholder effects.
 *
 * These defaults provide pre-configured highlight animations that can be used directly
 * with the [placeholder] modifier. Each default includes appropriate colors, animation
 * timing, and visual parameters for optimal loading indication.
 *
 * Example usage:
 * ```kotlin
 * // Using default shimmer effect
 * Text(
 *   text = "Loading...",
 *   modifier = Modifier.placeholder(
 *     visible = isLoading,
 *     highlight = PlaceholderDefaults.shimmer
 *   )
 * )
 *
 * // Using default fade effect
 * Image(
 *   painter = placeholderPainter,
 *   contentDescription = null,
 *   modifier = Modifier.placeholder(
 *     visible = isLoading,
 *     highlight = PlaceholderDefaults.fade
 *   )
 * )
 * ```
 *
 * @see shimmer for a sweeping gradient effect
 * @see fade for a simple opacity fade in/out
 * @see pulse for a pulsating color overlay
 * @see lightReveal for a liquid flowing light effect
 * @see circularReveal for an expanding circular reveal
 */
public object PlaceholderDefaults {

  /**
   * A shimmer effect that sweeps a gradient highlight across the placeholder.
   *
   * The shimmer creates a bright band that moves horizontally across the placeholder,
   * commonly used to indicate loading content in modern UIs.
   *
   * Default configuration:
   * - Highlight color: White with 50% opacity
   * - Animation duration: 1700ms with 200ms delay
   * - Repeat mode: Restart
   */
  public val shimmer: Shimmer = Shimmer(
    highlightColor = Color.White.copy(alpha = 0.5f),
    animationSpec = infiniteRepeatable(
      animation = tween(durationMillis = 1700, delayMillis = 200),
      repeatMode = RepeatMode.Restart,
    ),
  )

  /**
   * A fade effect that smoothly transitions the highlight opacity in and out.
   *
   * This creates a subtle breathing effect by fading the highlight color
   * over the placeholder content.
   *
   * Default configuration:
   * - Highlight color: Gray with 60% opacity
   * - Animation duration: 600ms with 200ms delay
   * - Repeat mode: Reverse (ping-pong effect)
   */
  public val fade: Fade = Fade(
    highlightColor = Color.Gray.copy(alpha = 0.6f),
    animationSpec = infiniteRepeatable(
      animation = tween(delayMillis = 200, durationMillis = 600),
      repeatMode = RepeatMode.Reverse,
    ),
  )

  /**
   * A pulse effect that rhythmically brightens and dims the highlight.
   *
   * The pulse creates a pulsating overlay that grows and shrinks in intensity,
   * providing a clear loading indication.
   *
   * Default configuration:
   * - Highlight color: Gray with 60% opacity
   * - Animation duration: 1500ms with 300ms delay
   * - Repeat mode: Reverse (ping-pong effect)
   */
  public val pulse: Pulse = Pulse(
    highlightColor = Color.Gray.copy(alpha = 0.6f),
    animationSpec = infiniteRepeatable(
      animation = tween(durationMillis = 1500, delayMillis = 300),
      repeatMode = RepeatMode.Reverse,
    ),
  )

  /**
   * A light reveal effect that creates a liquid, flowing shimmer.
   *
   * This effect simulates a wave of light moving across the placeholder,
   * providing an organic and high-quality loading indication.
   *
   * Default configuration:
   * - Highlight color: White (full intensity)
   * - Animation duration: 1500ms with 300ms delay
   * - Repeat mode: Reverse (ping-pong effect)
   */
  public val lightReveal: LightReveal = LightReveal(
    highlightColor = Color.White,
    animationSpec = infiniteRepeatable(
      animation = tween(durationMillis = 1500, delayMillis = 300),
      repeatMode = RepeatMode.Reverse,
    ),
  )

  /**
   * A circular reveal effect that expands from the center of the placeholder.
   *
   * This creates a ripple effect that radiates outward in a circular pattern,
   * providing a dynamic and eye-catching loading indication.
   *
   * Default configuration:
   * - Highlight color: White with 70% opacity
   * - Animation duration: 1500ms with 200ms delay
   * - Repeat mode: Reverse (ping-pong effect)
   */
  public val circularReveal: CircularReveal = CircularReveal(
    highlightColor = Color.White.copy(alpha = 0.7f),
    animationSpec = infiniteRepeatable(
      animation = tween(durationMillis = 1500, delayMillis = 200),
      repeatMode = RepeatMode.Reverse,
    ),
  )
}
