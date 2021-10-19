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

import io.github.softwarecats.casino.event.Outcome;
import io.github.softwarecats.craps.Dice;
import io.github.softwarecats.craps.event.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The type Throw builder.
 */
public class ThrowBuilder {

    protected static final Set<Integer> CRAPS = Set.of(2, 3, 12);

    protected static final Set<Integer> POINT = Set.of(4, 5, 6, 8, 9, 10);

    protected static final Set<Integer> NATURAL = Set.of(7);

    protected static final Set<Integer> ELEVEN = Set.of(11);

    protected static final List<Outcome> PROPOSITIONS = List.of(
            new Outcome("2", 30, 1), // 0
            new Outcome("3", 15, 1), // 1
            new Outcome("7", 4, 1), // 2
            new Outcome("11", 15, 1), // 3
            new Outcome("12", 30, 1), // 4
            new Outcome("Any Craps", 7, 1), // 5
            new OutcomeHorn(27, 4), // (2 OR 12) 6
            new OutcomeHorn(3, 1), // (3 OR 11) 7
            new OutcomeField(2, 1), // (2 OR 12) 8
            new OutcomeField(1, 1) // (3, 4, 9, 10, 11) 9
    );

    /**
     * Creates the 8 one-roll {@link Outcome} instances (2, 3, 7, 11, 12, Field, Horn, Any Craps). It then creates each
     * of the 36 {@link Throw}s, each of which has the appropriate combination of {@link Outcome}s. The {@link Throw}s
     * are assigned to dice.
     *
     * @param dice the dice to assign the throws to
     */
    public static void buildThrows(Dice dice) {
        for (int diceOne = 1; diceOne < 7; diceOne++) {
            for (int diceTwo = 1; diceTwo < 7; diceTwo++) {
                int sum = diceOne + diceTwo;
                Set<Outcome> outcomes = new HashSet<>();

                // Horn
                switch (sum) {
                    case 2:
                    case 12:
                        outcomes.add(PROPOSITIONS.get(6));
                        break;
                    case 3:
                    case 11:
                        outcomes.add(PROPOSITIONS.get(7));
                        break;
                }

                // Field
                switch (sum) {
                    case 2:
                    case 12:
                        outcomes.add(PROPOSITIONS.get(8));
                        break;
                    case 3:
                    case 4:
                    case 9:
                    case 10:
                    case 11:
                        outcomes.add(PROPOSITIONS.get(9));
                        break;
                }

                // Number
                switch (sum) {
                    case 2:
                        outcomes.add(PROPOSITIONS.get(0));
                        break;
                    case 3:
                        outcomes.add(PROPOSITIONS.get(1));
                        break;
                    case 7:
                        outcomes.add(PROPOSITIONS.get(2));
                        break;
                    case 11:
                        outcomes.add(PROPOSITIONS.get(3));
                        break;
                    case 12:
                        outcomes.add(PROPOSITIONS.get(4));
                        break;
                }

                // Craps
                if (CRAPS.contains(sum)) {
                    outcomes.add(PROPOSITIONS.get(5));
                    dice.addThrow(new CrapsThrow(diceOne, diceTwo, outcomes));
                    continue;
                }

                // Point
                if (POINT.contains(sum)) {
                    dice.addThrow(new PointThrow(diceOne, diceTwo, outcomes));
                    continue;
                }

                // Natural
                if (NATURAL.contains(sum)) {
                    dice.addThrow(new NaturalThrow(diceOne, diceTwo, outcomes));
                    continue;
                }

                // Eleven
                if (ELEVEN.contains(sum)) {
                    dice.addThrow(new ElevenThrow(diceOne, diceTwo, outcomes));
                }
            }
        }
    }
}
