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

public class OutcomeField extends Outcome {

    public OutcomeField(int odds) {
        super("Field", odds);
    }

    public OutcomeField(int numerator, int denominator) {
        super("Field", numerator, denominator);
    }

    public OutcomeField(Fraction odds) {
        super("Field", odds);
    }

    @Override
    public Fraction winAmount(Fraction amount, RandomEvent event) {
        if (event instanceof Throw) {
            int sum = ((Throw) event).getSum();
            if (sum == 2 || sum == 12) {
                return Fraction.getFraction(2, 1).multiplyBy(amount);
            } else if (sum == 3 || sum == 4 || sum == 9 || sum == 10 || sum == 11) {
                return Fraction.ONE.multiplyBy(amount);
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
        return String.format("%s (1:1, 2:1)", this.NAME);
    }
}