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

import io.github.softwarecats.casino.event.Outcome;
import io.github.softwarecats.casino.event.RandomEvent;
import org.apache.commons.lang3.math.Fraction;

public class OutcomeHorn extends Outcome {

    public OutcomeHorn(int odds) {
        super("Horn", odds);
    }

    public OutcomeHorn(int numerator, int denominator) {
        super("Horn", numerator, denominator);
    }

    public OutcomeHorn(Fraction odds) {
        super("Horn", odds);
    }

    @Override
    public Fraction winAmount(Fraction amount, RandomEvent event) {
        if (event instanceof Throw) {
            int sum = ((Throw) event).getSum();
            if (sum == 2 || sum == 12) {
                return Fraction.getFraction(27, 4).multiplyBy(amount);
            } else if (sum == 3 || sum == 11) {
                return Fraction.getFraction(3, 1).multiplyBy(amount);
            }
        }
        return winAmount(amount);
    }

    @Override
    public Fraction winAmount(int amount, RandomEvent event) {
        return winAmount(Fraction.getFraction(amount), event);
    }

    @Override
    public String toString() {
        return String.format("%s (27:4, 3:1)", this.NAME);
    }
}