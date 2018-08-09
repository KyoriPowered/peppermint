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
import com.google.gson.JsonParser;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * A collection of utilities for reading and writing JSON.
 */
public interface JsonIO {
  /**
   * A shared parser.
   */
  JsonParser PARSER = new JsonParser();

  /**
   * Reads a JSON element from {@code path}.
   *
   * @param path the path
   * @return the json
   * @throws IOException if an exception occurred while parsing
   */
  static @NonNull JsonElement read(final @NonNull Path path) throws IOException {
    try(
      final InputStream is = Files.newInputStream(path);
      final InputStreamReader isr = new InputStreamReader(is)
    ) {
      return PARSER.parse(isr);
    }
  }

  /**
   * Reads a JSON object from {@code path}.
   *
   * @param path the path
   *    * @return the json
   *    * @throws IOException if an exception occurred while parsing
   */
  static @NonNull JsonObject readObject(final @NonNull Path path) throws IOException {
    return read(path).getAsJsonObject();
  }
}
