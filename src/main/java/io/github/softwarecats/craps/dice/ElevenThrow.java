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

import io.github.softwarecats.craps.Game;
import io.github.softwarecats.craps.Outcome;

import java.util.Arrays;
import java.util.Collection;

/**
 * {@link ElevenThrow} is a subclass of {@link Throw} for the number, 11. This is special because 11 has one effect on a come-out
 * roll and a different effect on point rolls.
 */
public class ElevenThrow extends Throw {

    /**
     * Creates this {@link Throw}. The constraint is that diceOne + diceTwo = 11. If the constraint is not satisfied, simply
     * raise an {@link Exception}.
     * <p>
     * This uses the superclass constructor to add appropriate {@link Outcome} for a {@link Throw} of 11.
     *
     * @param diceOne the value of one die
     * @param diceTwo the value of the other die
     */
    public ElevenThrow(int diceOne, int diceTwo) {
        this(diceOne, diceTwo, new Outcome[]{});
    }

    /**
     * Creates this {@link Throw}. The constraint is that diceOne + diceTwo = 11. If the constraint is not satisfied, simply
     * raise an {@link Exception}.
     * <p>
     * This uses the superclass constructor to add appropriate {@link Outcome} for a {@link Throw} of 11.
     *
     * @param diceOne  the value of one die
     * @param diceTwo  the value of the other die
     * @param outcomes the various {@link Outcome}s for this {@link Throw}
     */
    public ElevenThrow(int diceOne, int diceTwo, Outcome... outcomes) {
        this(diceOne, diceTwo, Arrays.asList(outcomes.clone()));
    }

    /**
     * Creates this {@link Throw}. The constraint is that diceOne + diceTwo = 11. If the constraint is not satisfied, simply
     * raise an {@link Exception}.
     * <p>
     * This uses the superclass constructor to add appropriate {@link Outcome} for a {@link Throw} of 11.
     *
     * @param diceOne  the value of one die
     * @param diceTwo  the value of the other die
     * @param outcomes the various {@link Outcome}s for this {@link Throw}
     */
    public ElevenThrow(int diceOne, int diceTwo, Collection<Outcome> outcomes) {
        super(diceOne, diceTwo, outcomes);

        if (diceOne + diceTwo != 11) {
            throw new IllegalArgumentException("In a eleven throw, dice one and two must sum to 11");
        }
    }

    /**
     * Eleven is odd and never part of “hardways” bets. This method always returns false.
     *
     * @return {@link Boolean#FALSE}
     */
    @Override
    public boolean isHard() {
        return false;
    }

    /**
     * Calls the {@link Game#eleven()} method of a {@link Game}. This may change the game state and resolve bets.
     *
     * @param game the {@link Game} to be updated based on this throw
     */
    @Override
    public void updateGame(Game game) {
        game.eleven();
    }
}
