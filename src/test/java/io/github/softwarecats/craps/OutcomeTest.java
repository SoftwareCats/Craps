/*
 * Copyright © Bowen Wu 2021
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.softwarecats.craps;

import org.apache.commons.lang3.math.Fraction;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OutcomeTest {

    Outcome outcome1;
    Outcome outcome2;
    Outcome outcome3;

    @Before
    public void setUp() {
        outcome1 = new Outcome("A", 1);
        outcome2 = new Outcome("B", 1, 2);
        outcome3 = new Outcome("C", Fraction.getFraction(2, 3));
    }

    @Test
    public void winAmount() {
        assertEquals(Fraction.getFraction(2), outcome1.winAmount(Fraction.getFraction(2)));
        assertEquals(Fraction.getFraction(1), outcome2.winAmount(Fraction.getFraction(2)));
        assertEquals(Fraction.getFraction(2), outcome3.winAmount(Fraction.getFraction(3)));
    }

    @Test
    public void testToString() {
        assertEquals("A (1:1)", outcome1.toString());
        assertEquals("B (1:2)", outcome2.toString());
        assertEquals("C (2:3)", outcome3.toString());
    }
}