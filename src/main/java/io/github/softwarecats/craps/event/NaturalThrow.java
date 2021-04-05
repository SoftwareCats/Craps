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
 * {@link NaturalThrow} is a subclass of {@link Throw} for the natural number, 7.
 */
public class NaturalThrow extends Throw {

    /**
     * Creates this {@link Throw}. The constraint is that diceOne + diceTwo = 7. If the constraint is not satisfied, simply
     * raise an {@link Exception}.
     * <p>
     * This uses the superclass constructor to add appropriate {@link Outcome} for a {@link Throw} of 7.
     *
     * @param diceOne the value of one die
     * @param diceTwo the value of the other die
     */
    public NaturalThrow(int diceOne, int diceTwo) {
        this(diceOne, diceTwo, new Outcome[]{});
    }

    /**
     * Creates this {@link Throw}. The constraint is that diceOne + diceTwo = 7. If the constraint is not satisfied, simply
     * raise an {@link Exception}.
     * <p>
     * This uses the superclass constructor to add appropriate {@link Outcome} for a {@link Throw} of 7.
     *
     * @param diceOne  the value of one die
     * @param diceTwo  the value of the other die
     * @param outcomes the various {@link Outcome}s for this {@link Throw}
     */
    public NaturalThrow(int diceOne, int diceTwo, Outcome[] outcomes) {
        this(diceOne, diceTwo, Arrays.asList(outcomes.clone()));
    }

    /**
     * Creates this {@link Throw}. The constraint is that diceOne + diceTwo = 7. If the constraint is not satisfied, simply
     * raise an {@link Exception}.
     * <p>
     * This uses the superclass constructor to add appropriate {@link Outcome} for a {@link Throw} of 7.
     *
     * @param diceOne  the value of one die
     * @param diceTwo  the value of the other die
     * @param outcomes the various {@link Outcome}s for this {@link Throw}
     */
    public NaturalThrow(int diceOne, int diceTwo, Collection<Outcome> outcomes) {
        super(diceOne, diceTwo, outcomes);

        if (diceOne + diceTwo != 7) {
            throw new IllegalArgumentException("In a natural throw, dice one and two must sum to 7");
        }
    }

    /**
     * A natural 7 is odd, and can never be made “the hard way”. This method always returns false.
     *
     * @return {@link Boolean#FALSE}
     */
    @Override
    public boolean isHard() {
        return false;
    }

    /**
     * Calls the {@link Game#natural()} method of a {@link Game}. This may change the game state and resolve bets.
     *
     * @param game the {@link Game} to be updated based on this throw
     */
    @Override
    public void updateGame(Game game) {
        game.natural();
    }
}
