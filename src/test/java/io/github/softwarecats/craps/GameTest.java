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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameTest {

    Game game;

    @Before
    public void setUp() {
        game = new Game();
    }

    @Test
    public void craps() {
    }

    @Test
    public void natural() {
    }

    @Test
    public void eleven() {
    }

    @Test
    public void point() {
        Assert.assertEquals(0, game.point);
        game.point(1);
        Assert.assertEquals(1, game.point);
        game.point(2);
        Assert.assertEquals(1, game.point);
    }

    @Test
    public void testToString() {
        Assert.assertEquals("Point Off", game.toString());
        game.point = 1;
        Assert.assertEquals("1", game.toString());
    }
}