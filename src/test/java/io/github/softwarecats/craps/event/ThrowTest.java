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

package io.github.softwarecats.craps.event;

import io.github.softwarecats.craps.Game;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ThrowTest {

    Game game;
    Throw diceThrow;
    String methodCalled;

    @Before
    public void setUp() {
        game = new Game() {
            @Override
            public void craps() {
                methodCalled = "craps";
            }

            @Override
            public void natural() {
                methodCalled = "natural";
            }

            @Override
            public void eleven() {
                methodCalled = "eleven";
            }

            @Override
            public void point(int point) {
                methodCalled = "point";
            }
        };
    }

    @Test
    public void isHard() {
        for (int i = 1; i < 7; i++) {
            for (int j = 1; j < 7; j++) {
                Throw diceThrow = new Throw(i, j) {
                    @Override
                    public void updateGame(Game game) {
                    }
                };

                if (i == j) {
                    Assert.assertTrue(diceThrow.isHard());
                } else {
                    Assert.assertFalse(diceThrow.isHard());
                }
            }
        }
    }

    @Test
    public void testToString() {
        for (int i = 1; i < 7; i++) {
            for (int j = 1; j < 7; j++) {
                Throw diceThrow = new Throw(i, j) {
                    @Override
                    public void updateGame(Game game) {
                    }
                };

                Assert.assertEquals(i + ", " + j, diceThrow.toString());
            }
        }
    }

    @Test
    public void getKey() {
        for (int i = 1; i < 7; i++) {
            for (int j = 1; j < 7; j++) {
                Throw diceThrow = new Throw(i, j) {
                    @Override
                    public void updateGame(Game game) {
                    }
                };

                Assert.assertEquals(Pair.of(i, j), diceThrow.getKey());
            }
        }
    }
}