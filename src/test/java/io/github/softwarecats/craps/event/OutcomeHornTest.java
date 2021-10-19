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

import org.apache.commons.lang3.math.Fraction;
import org.junit.Assert;
import org.junit.Test;

public class OutcomeHornTest {

    protected OutcomeHorn outcome = new OutcomeHorn(1);
    protected Throw throwWithSumTwo = new CrapsThrow(1, 1);
    protected Throw throwWithSumThree = new CrapsThrow(1, 2);

    @Test
    public void winAmount() {
        Assert.assertEquals(
                Fraction.getFraction(1),
                outcome.winAmount(Fraction.getFraction(4, 27), throwWithSumTwo));

        Assert.assertEquals(
                Fraction.getFraction(3),
                outcome.winAmount(Fraction.getFraction(1), throwWithSumThree));
    }

    @Test
    public void testWinAmount() {
        Assert.assertEquals(
                Fraction.getFraction(27),
                outcome.winAmount(4, throwWithSumTwo));

        Assert.assertEquals(
                Fraction.getFraction(3),
                outcome.winAmount(1, throwWithSumThree));
    }
}