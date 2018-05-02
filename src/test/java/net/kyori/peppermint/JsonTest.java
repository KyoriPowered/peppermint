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

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class JsonTest {
  private static final int NUMBER_INT = ThreadLocalRandom.current().nextInt();
  private static final long NUMBER_LONG = ThreadLocalRandom.current().nextLong();
  private static final String STRING = UUID.randomUUID().toString();
  private JsonObject json;

  @BeforeAll
  void init() {
    this.json = new JsonObject();
    this.json.add("array", new JsonArray());
    this.json.add("object", new JsonObject());
    this.json.addProperty("primitive-boolean-false", false);
    this.json.addProperty("primitive-boolean-true", true);
    this.json.addProperty("primitive-number-int", NUMBER_INT);
    this.json.addProperty("primitive-number-long", NUMBER_LONG);
    this.json.addProperty("primitive-string", STRING);
  }

  @Test
  void testArray() {
    assertTrue(Json.isArray(this.json, "array"));
    assertFalse(Json.isArray(this.json, "object"));
    assertFalse(Json.isArray(this.json, "primitive-boolean-false"));
    assertFalse(Json.isArray(this.json, "primitive-number-int"));
    assertFalse(Json.isArray(this.json, "primitive-number-long"));
    assertFalse(Json.isArray(this.json, "primitive-string"));
  }

  @Test
  void testObject() {
    assertFalse(Json.isObject(this.json, "array"));
    assertTrue(Json.isObject(this.json, "object"));
    assertFalse(Json.isObject(this.json, "primitive-boolean-false"));
    assertFalse(Json.isObject(this.json, "primitive-number-int"));
    assertFalse(Json.isObject(this.json, "primitive-number-long"));
    assertFalse(Json.isObject(this.json, "primitive-string"));
  }

  @Test
  void testPrimitive() {
    assertFalse(Json.isPrimitive(this.json, "array"));
    assertFalse(Json.isPrimitive(this.json, "object"));
    assertTrue(Json.isPrimitive(this.json, "primitive-boolean-false"));
    assertTrue(Json.isPrimitive(this.json, "primitive-number-int"));
    assertTrue(Json.isPrimitive(this.json, "primitive-number-long"));
    assertTrue(Json.isPrimitive(this.json, "primitive-string"));
  }

  @Test
  void testNumber() {
    assertFalse(Json.isNumber(this.json, "array"));
    assertFalse(Json.isNumber(this.json, "object"));
    assertFalse(Json.isNumber(this.json, "primitive-boolean-false"));
    assertTrue(Json.isNumber(this.json, "primitive-number-int"));
    assertTrue(Json.isNumber(this.json, "primitive-number-long"));
    assertFalse(Json.isNumber(this.json, "primitive-string"));
  }

  @Test
  void testBoolean() {
    assertFalse(Json.isBoolean(this.json, "array"));
    assertFalse(Json.isBoolean(this.json, "object"));
    assertTrue(Json.isBoolean(this.json, "primitive-boolean-false"));
    assertFalse(Json.isBoolean(this.json, "primitive-number-int"));
    assertFalse(Json.isBoolean(this.json, "primitive-number-long"));
    assertFalse(Json.isBoolean(this.json, "primitive-string"));

    assertEquals(false, Json.needBoolean(this.json.get("primitive-boolean-false"), "primitive-boolean-false"));
    assertEquals(false, Json.needBoolean(this.json, "primitive-boolean-false"));
    assertEquals(true, Json.needBoolean(this.json.get("primitive-boolean-true"), "primitive-boolean-true"));
    assertEquals(true, Json.needBoolean(this.json, "primitive-boolean-true"));

    assertEquals(false, Json.getBoolean(this.json, "primitive-boolean-false", true));
    assertEquals(true, Json.getBoolean(this.json, "primitive-boolean-true", false));
    assertEquals(true, Json.getBoolean(this.json, "primitive-boolean-nope", true));
  }

  @Test
  void testInt() {
    assertEquals(NUMBER_INT, Json.needInt(this.json.get("primitive-number-int"), "primitive-number-int"));
    assertEquals(NUMBER_INT, Json.needInt(this.json, "primitive-number-int"));

    assertEquals(NUMBER_INT, Json.getInt(this.json, "primitive-number-int", 2));
    assertEquals(2, Json.getInt(this.json, "primitive-number-int_nope", 2));
  }

  @Test
  void testLong() {
    assertEquals(NUMBER_LONG, Json.needLong(this.json.get("primitive-number-long"), "primitive-number-long"));
    assertEquals(NUMBER_LONG, Json.needLong(this.json, "primitive-number-long"));

    assertEquals(NUMBER_LONG, Json.getLong(this.json, "primitive-number-long", 2));
    assertEquals(2, Json.getLong(this.json, "primitive-number-long_nope", 2));
  }

  @Test
  void testString() {
    assertFalse(Json.isString(this.json, "array"));
    assertFalse(Json.isString(this.json, "object"));
    assertFalse(Json.isString(this.json, "primitive-boolean-false"));
    assertFalse(Json.isString(this.json, "primitive-number-int"));
    assertFalse(Json.isString(this.json, "primitive-number-long"));
    assertTrue(Json.isString(this.json, "primitive-string"));

    assertEquals(STRING, Json.needString(this.json.get("primitive-string"), "primitive-string"));
    assertEquals(STRING, Json.needString(this.json, "primitive-string"));

    assertEquals(STRING, Json.getString(this.json, "primitive-string", "foo"));
    assertEquals("bar", Json.getString(this.json, "primitive-string_nope", "bar"));
  }
}
