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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PointThrowTest extends ThrowTest {

    @Override
    @Before
    public void setUp() {
        super.setUp();
        diceThrow = new PointThrow(2, 2);
    }

    @Override
    @Test
    public void isHard() {
        for (int i = 1; i < 7; i++) {
            for (int j = 1; j < 7; j++) {
                Throw diceThrow;
                try {
                    diceThrow = new PointThrow(i, j);

                    if (i == j) {
                        Assert.assertTrue(diceThrow.isHard());
                    } else {
                        Assert.assertFalse(diceThrow.isHard());
                    }
                } catch (Exception ignored) {
                    // Expected behaviour, should not allow creation of illegal throws
                }
            }
        }
    }

    @Test
    public void updateGame() {
        diceThrow.updateGame(game);
        Assert.assertEquals("point", methodCalled);
    }
}