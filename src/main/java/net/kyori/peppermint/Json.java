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
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * A collection of utilities for working with JSON elements.
 */
public interface Json {
  /**
   * Tests if the element {@code name} in {@code json} is an array.
   *
   * @param json the json
   * @param name the name
   * @return {@code true} if the element {@code name} in {@code json} is an array, {@code false} otherwise
   */
  static boolean isArray(final @NonNull JsonObject json, final @NonNull String name) {
    return json.has(name) && json.get(name).isJsonArray();
  }

  /**
   * Tests if the element {@code name} in {@code json} is an object.
   *
   * @param json the json
   * @param name the name
   * @return {@code true} if the element {@code name} in {@code json} is an object, {@code false} otherwise
   */
  static boolean isObject(final @NonNull JsonObject json, final @NonNull String name) {
    return json.has(name) && json.get(name).isJsonObject();
  }

  /**
   * Tests if the element {@code name} in {@code json} is a primitive.
   *
   * @param json the json
   * @param name the name
   * @return {@code true} if the element {@code name} in {@code json} is a primitive, {@code false} otherwise
   */
  static boolean isPrimitive(final @NonNull JsonObject json, final @NonNull String name) {
    return json.has(name) && json.get(name).isJsonPrimitive();
  }

  /**
   * Tests if the element {@code name} in {@code json} is a number.
   *
   * @param json the json
   * @param name the name
   * @return {@code true} if the element {@code name} in {@code json} is a number, {@code false} otherwise
   */
  static boolean isNumber(final @NonNull JsonObject json, final @NonNull String name) {
    return isPrimitive(json, name) && json.getAsJsonPrimitive(name).isNumber();
  }

  /*
   * booleans
   */

  /**
   * Tests if the element {@code name} in {@code json} is a boolean.
   *
   * @param json the json
   * @param name the name
   * @return {@code true} if the element {@code name} in {@code json} is a boolean, {@code false} otherwise
   */
  static boolean isBoolean(final @NonNull JsonObject json, final @NonNull String name) {
    return isPrimitive(json, name) && json.getAsJsonPrimitive(name).isBoolean();
  }

  /**
   * Gets {@code json} as a boolean.
   *
   * @param json the json
   * @param name the name
   * @return a boolean
   */
  static boolean needBoolean(final @NonNull JsonElement json, final @NonNull String name) {
    if(json.isJsonPrimitive()) {
      return json.getAsBoolean();
    }
    throw new JsonSyntaxException("Expected " + name + " to be a boolean");
  }

  /**
   * Gets the element {@code name} in {@code json} as a boolean.
   *
   * @param json the json
   * @param name the name
   * @return a boolean
   */
  static boolean needBoolean(final @NonNull JsonObject json, final @NonNull String name) {
    if(json.has(name)) {
      return needBoolean(json.get(name), name);
    }
    throw new JsonSyntaxException("Missing " + name + ", expected to find a boolean");
  }

  /**
   * Gets the element {@code name} in {@code json} as a boolean.
   *
   * @param json the json
   * @param name the name
   * @param defaultValue the default value
   * @return a boolean
   */
  static boolean getBoolean(final @NonNull JsonObject json, final @NonNull String name, final boolean defaultValue) {
    return json.has(name) ? needBoolean(json.get(name), name) : defaultValue;
  }

  /*
   * ints
   */

  /**
   * Gets {@code json} as an int.
   *
   * @param json the json
   * @param name the name
   * @return an int
   */
  static int needInt(final @NonNull JsonElement json, final @NonNull String name) {
    if(json.isJsonPrimitive()) {
      return json.getAsInt();
    }
    throw new JsonSyntaxException("Expected " + name + " to be an int");
  }

  /**
   * Gets the element {@code name} in {@code json} as an int.
   *
   * @param json the json
   * @param name the name
   * @return an int
   */
  static int needInt(final @NonNull JsonObject json, final @NonNull String name) {
    if(json.has(name)) {
      return needInt(json.get(name), name);
    }
    throw new JsonSyntaxException("Missing " + name + ", expected to find an int");
  }

  /**
   * Gets the element {@code name} in {@code json} as an int.
   *
   * @param json the json
   * @param name the name
   * @param defaultValue the default value
   * @return an int
   */
  static int getInt(final @NonNull JsonObject json, final @NonNull String name, final int defaultValue) {
    return json.has(name) ? needInt(json.get(name), name) : defaultValue;
  }

  /*
   * longs
   */

  /**
   * Gets {@code json} as an int.
   *
   * @param json the json
   * @param name the name
   * @return an int
   */
  static long needLong(final @NonNull JsonElement json, final @NonNull String name) {
    if(json.isJsonPrimitive()) {
      return json.getAsLong();
    }
    throw new JsonSyntaxException("Expected " + name + " to be a long");
  }

  /**
   * Gets the element {@code name} in {@code json} as a long.
   *
   * @param json the json
   * @param name the name
   * @return a long
   */
  static long needLong(final @NonNull JsonObject json, final @NonNull String name) {
    if(json.has(name)) {
      return needLong(json.get(name), name);
    }
    throw new JsonSyntaxException("Missing " + name + ", expected to find a long");
  }

  /**
   * Gets the element {@code name} in {@code json} as a long.
   *
   * @param json the json
   * @param name the name
   * @param defaultValue the default value
   * @return a long
   */
  static long getLong(final @NonNull JsonObject json, final @NonNull String name, final long defaultValue) {
    return json.has(name) ? needLong(json.get(name), name) : defaultValue;
  }

  /*
   * strings
   */

  /**
   * Tests if the element {@code name} in {@code json} is a string.
   *
   * @param json the json
   * @param name the name
   * @return {@code true} if the element {@code name} in {@code json} is a string, {@code false} otherwise
   */
  static boolean isString(final @NonNull JsonObject json, final @NonNull String name) {
    return isPrimitive(json, name) && json.getAsJsonPrimitive(name).isString();
  }

  /**
   * Gets {@code json} as a string.
   *
   * @param json the json
   * @param name the name
   * @return a string
   */
  static @NonNull String needString(final @NonNull JsonElement json, final @NonNull String name) {
    if(json.isJsonPrimitive()) {
      return json.getAsString();
    }
    throw new JsonSyntaxException("Expected " + name + " to be a string");
  }

  /**
   * Gets the element {@code name} in {@code json} as a string.
   *
   * @param json the json
   * @param name the name
   * @return a string
   */
  static @NonNull String needString(final @NonNull JsonObject json, final @NonNull String name) {
    if(json.has(name)) {
      return needString(json.get(name), name);
    }
    throw new JsonSyntaxException("Missing " + name + ", expected to find a string");
  }

  /**
   * Gets the element {@code name} in {@code json} as a string.
   *
   * @param json the json
   * @param name the name
   * @param defaultValue the default value
   * @return a string
   */
  static /* @Nullable */ String getString(final @NonNull JsonObject json, final @NonNull String name, final @Nullable String defaultValue) {
    return json.has(name) ? needString(json.get(name), name) : defaultValue;
  }
}
