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
import io.github.softwarecats.craps.Game;

import java.util.Arrays;
import java.util.Collection;

/**
 * {@link CrapsThrow} is a subclass of {@link Throw} for the craps numbers 2, 3 and 12.
 */
public class CrapsThrow extends Throw {

    /**
     * Creates this {@link Throw}. The constraint is that diceOne + diceTwo == 2, 3, or 12. If the constraint is not
     * satisfied, simply raise an {@link Exception}.
     * <p>
     * This uses the superclass constructor to add appropriate {@link Outcome}s for a throw of craps.
     *
     * @param diceOne the value of one die
     * @param diceTwo the value of the other die
     */
    public CrapsThrow(int diceOne, int diceTwo) {
        this(diceOne, diceTwo, new Outcome[]{});
    }

    /**
     * Creates this {@link Throw}. The constraint is that diceOne + diceTwo == 2, 3, or 12. If the constraint is not
     * satisfied, simply raise an {@link Exception}.
     * <p>
     * This uses the superclass constructor to add appropriate {@link Outcome}s for a throw of craps.
     *
     * @param diceOne  the value of one die
     * @param diceTwo  the value of the other die
     * @param outcomes the various {@link Outcome}s for this {@link Throw}
     */
    public CrapsThrow(int diceOne, int diceTwo, Outcome[] outcomes) {
        this(diceOne, diceTwo, Arrays.asList(outcomes.clone()));
    }

    /**
     * Creates this {@link Throw}. The constraint is that diceOne + diceTwo == 2, 3, or 12. If the constraint is not
     * satisfied, simply raise an {@link Exception}.
     * <p>
     * This uses the superclass constructor to add appropriate {@link Outcome}s for a throw of craps.
     *
     * @param diceOne  the value of one die
     * @param diceTwo  the value of the other die
     * @param outcomes the various {@link Outcome}s for this {@link Throw}
     */
    public CrapsThrow(int diceOne, int diceTwo, Collection<Outcome> outcomes) {
        super(diceOne, diceTwo, outcomes);

        int sum = diceOne + diceTwo;
        if (sum != 2 && sum != 3 && sum != 12) {
            throw new IllegalArgumentException("In a craps throw, dice one and two must sum to 2, 3, or 12.");
        }
    }

    /**
     * The craps numbers are never part of “hardways” bets. This method always returns false.
     *
     * @return {@link Boolean#FALSE}
     */
    @Override
    public boolean isHard() {
        return false;
    }

    /**
     * Calls the {@link Game#craps()} method of a {@link Game}. This may change the game state and resolve bets.
     *
     * @param game the {@link Game} to be updated based on this throw
     */
    @Override
    public void updateGame(Game game) {
        game.craps();
    }
}
