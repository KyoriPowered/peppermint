/*
 * This file is part of peppermint, licensed under the MIT License.
 *
 * Copyright (c) 2018 KyoriPowered
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package net.kyori.peppermint;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * A collection of utilities for working with JSON elements.
 */
public interface Json {
  static boolean isArray(final @NonNull JsonObject object, final @NonNull String name) {
    return object.has(name) && object.get(name).isJsonArray();
  }

  static boolean isObject(final @NonNull JsonObject object, final @NonNull String name) {
    return object.has(name) && object.get(name).isJsonObject();
  }

  static boolean isPrimitive(final @NonNull JsonObject object, final @NonNull String name) {
    return object.has(name) && object.get(name).isJsonPrimitive();
  }

  static boolean isNumber(final @NonNull JsonObject object, final @NonNull String name) {
    return isPrimitive(object, name) && object.getAsJsonPrimitive(name).isNumber();
  }

  /*
   * booleans
   */

  static boolean isBoolean(final @NonNull JsonObject object, final @NonNull String name) {
    return isPrimitive(object, name) && object.getAsJsonPrimitive(name).isBoolean();
  }

  static boolean getBoolean(final @NonNull JsonElement element, final @NonNull String name) {
    if(element.isJsonPrimitive()) {
      return element.getAsBoolean();
    }
    throw new JsonSyntaxException("Expected " + name + " to be a boolean");
  }

  static boolean getBoolean(final @NonNull JsonObject object, final @NonNull String name) {
    if(object.has(name)) {
      return getBoolean(object.get(name), name);
    }
    throw new JsonSyntaxException("Missing " + name + ", expected to find a boolean");
  }

  static boolean getBoolean(final @NonNull JsonObject object, final @NonNull String name, final boolean defaultValue) {
    return object.has(name) ? getBoolean(object.get(name), name) : defaultValue;
  }

  /*
   * ints
   */

  static int getInt(final @NonNull JsonElement element, final @NonNull String name) {
    if(element.isJsonPrimitive()) {
      return element.getAsInt();
    }
    throw new JsonSyntaxException("Expected " + name + " to be an int");
  }

  static int getInt(final @NonNull JsonObject object, final @NonNull String name) {
    if(object.has(name)) {
      return getInt(object.get(name), name);
    }
    throw new JsonSyntaxException("Missing " + name + ", expected to find an int");
  }

  static int getInt(final @NonNull JsonObject object, final @NonNull String name, final int defaultValue) {
    return object.has(name) ? getInt(object.get(name), name) : defaultValue;
  }

  /*
   * longs
   */

  static long getLong(final @NonNull JsonElement element, final @NonNull String name) {
    if(element.isJsonPrimitive()) {
      return element.getAsLong();
    }
    throw new JsonSyntaxException("Expected " + name + " to be a long");
  }

  static long getLong(final @NonNull JsonObject object, final @NonNull String name) {
    if(object.has(name)) {
      return getLong(object.get(name), name);
    }
    throw new JsonSyntaxException("Missing " + name + ", expected to find a long");
  }

  static long getLong(final @NonNull JsonObject object, final @NonNull String name, final long defaultValue) {
    return object.has(name) ? getLong(object.get(name), name) : defaultValue;
  }

  /*
   * strings
   */

  static boolean isString(final @NonNull JsonObject object, final @NonNull String name) {
    return isPrimitive(object, name) && object.getAsJsonPrimitive(name).isString();
  }

  static @NonNull String getString(final @NonNull JsonElement element, final @NonNull String name) {
    if(element.isJsonPrimitive()) {
      return element.getAsString();
    }
    throw new JsonSyntaxException("Expected " + name + " to be a string");
  }

  static @NonNull String getString(final @NonNull JsonObject object, final @NonNull String name) {
    if(object.has(name)) {
      return getString(object.get(name), name);
    }
    throw new JsonSyntaxException("Missing " + name + ", expected to find a string");
  }

  static @NonNull String getString(final @NonNull JsonObject object, final @NonNull String name, final @NonNull String defaultValue) {
    return object.has(name) ? getString(object.get(name), name) : defaultValue;
  }
}
