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

package io.github.softwarecats.craps.dice;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DiceTest {

    Dice dice;

    Random rng;

    Throw naturalThrow;
    Throw crapsThrow;
    Throw pointThrow;
    Throw elevenThrow;

    @Before
    public void setUp() {
        rng = Mockito.mock(Random.class);

        dice = new Dice(rng);

        naturalThrow = new NaturalThrow(1, 6);
        crapsThrow = new CrapsThrow(6, 6);
        elevenThrow = new ElevenThrow(5, 6);
        pointThrow = new PointThrow(2, 2);
    }

    @Test
    public void getThrow() {
        dice.addThrow(naturalThrow);

        Assert.assertEquals(naturalThrow, dice.getThrow(1, 6));
        Assert.assertEquals(naturalThrow, dice.getThrow(naturalThrow.getKey()));
    }

    @Test
    public void addThrow() {
        dice.addThrow(naturalThrow);
        dice.addThrow(crapsThrow);
        dice.addThrow(pointThrow);
        dice.addThrow(elevenThrow);

        Assert.assertEquals(naturalThrow, dice.getThrow(1, 6));
        Assert.assertEquals(crapsThrow, dice.getThrow(6, 6));
        Assert.assertEquals(elevenThrow, dice.getThrow(5, 6));
        Assert.assertEquals(pointThrow, dice.getThrow(2, 2));
    }

    @Test
    public void next() {
        dice.addThrow(naturalThrow);
        dice.addThrow(crapsThrow);
        dice.addThrow(pointThrow);
        dice.addThrow(elevenThrow);
        List<Throw> diceThrows = new ArrayList<>(dice.THROWS.values());

        Mockito.when(rng.nextInt(4)).thenReturn(0);
        Assert.assertEquals(diceThrows.get(0), dice.next());

        Mockito.when(rng.nextInt(4)).thenReturn(1);
        Assert.assertEquals(diceThrows.get(1), dice.next());

        Mockito.when(rng.nextInt(4)).thenReturn(2);
        Assert.assertEquals(diceThrows.get(2), dice.next());

        Mockito.when(rng.nextInt(4)).thenReturn(3);
        Assert.assertEquals(diceThrows.get(3), dice.next());
    }
}