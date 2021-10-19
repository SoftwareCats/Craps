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

package io.github.softwarecats.craps.util;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import io.github.softwarecats.craps.Dice;
import io.github.softwarecats.craps.event.Throw;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

public class ThrowBuilderTest {

    Dice dice = new Dice();

    @Before
    public void setUp() {
        ThrowBuilder.buildThrows(dice);
    }

    @Test
    public void buildThrows() {
        // Make sure the distribution of sums are correct.
        Multiset<Integer> sums = HashMultiset.create();

        for (Map.Entry<Pair<Integer, Integer>, Throw> entry : dice.getAllThrows().entrySet()) {
            int expectedSum = entry.getKey().getLeft() + entry.getKey().getRight();
            int actualSum = entry.getValue().getSum();

            Assert.assertEquals(expectedSum, actualSum);

            sums.add(actualSum);
        }

        Assert.assertEquals(1, sums.count(2));
        Assert.assertEquals(2, sums.count(3));
        Assert.assertEquals(3, sums.count(4));
        Assert.assertEquals(4, sums.count(5));
        Assert.assertEquals(5, sums.count(6));
        Assert.assertEquals(6, sums.count(7));
        Assert.assertEquals(5, sums.count(8));
        Assert.assertEquals(4, sums.count(9));
        Assert.assertEquals(3, sums.count(10));
        Assert.assertEquals(2, sums.count(11));
        Assert.assertEquals(1, sums.count(12));
    }
}