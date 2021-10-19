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
import io.github.softwarecats.craps.Game;
import lombok.Getter;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.Collection;

/**
 * {@link Throw} is the superclass for the various throws of the dice. Each subclass is a different grouping of the
 * numbers, based on the rules for Craps.
 */
public abstract class Throw extends RandomEvent {

    /**
     * One of the two die values, from 1 to 6.
     */
    @Getter
    protected int diceOne;

    /**
     * The other of the two die values, from 1 to 6.
     */
    @Getter
    protected int diceTwo;

    /**
     * Creates this {@link Throw}, {@link Outcome}s can be added later.
     *
     * @param diceOne the value of one die
     * @param diceTwo the value of the other die
     */
    public Throw(int diceOne, int diceTwo) {
        this(diceOne, diceTwo, new Outcome[]{});
    }

    /**
     * Creates this {@link Throw}, and associates the given Stream of {@link Outcome}s that are winning propositions.
     *
     * @param diceOne  the value of one die
     * @param diceTwo  the value of the other die
     * @param outcomes the various {@link Outcome}s for this {@link Throw}
     */
    public Throw(int diceOne, int diceTwo, Outcome[] outcomes) {
        this(diceOne, diceTwo, Arrays.asList(outcomes.clone()));
    }

    /**
     * Creates this {@link Throw}, and associates the given {@link Collection} of {@link Outcome}s that are winning
     * propositions.
     *
     * @param diceOne  the value of one die
     * @param diceTwo  the value of the other die
     * @param outcomes the various {@link Outcome}s for this {@link Throw}
     */
    public Throw(int diceOne, int diceTwo, Collection<? extends Outcome> outcomes) {
        super(outcomes);
        this.diceOne = diceOne;
        this.diceTwo = diceTwo;
    }

    /**
     * Returns true if diceOne is equal to diceTwo. This helps determine if hardways bets have been won or lost.
     *
     * @return true if diceOne is equal to diceTwo
     */
    public boolean isHard() {
        return diceOne == diceTwo;
    }

    /**
     * Calls one of the {@link Game} state change methods: {@link Game#craps()}, {@link Game#natural()}, {@link
     * Game#eleven()}, {@link Game#point(int)}. This may change the game state and resolve bets.
     *
     * @param game the {@link Game} to be updated based on this throw
     */
    public abstract void updateGame(Game game);

    /**
     * An easy-to-read {@link String} output method is also very handy. This should return a {@link String}
     * representation of the dice. A form that looks like "1,2" works nicely.
     *
     * @return a {@link String} representation of the dice
     */
    @Override
    public String toString() {
        return String.format("%d, %d", diceOne, diceTwo);
    }

    /**
     * Gets the key representation of the {@link Throw}
     *
     * @return the {@link Pair} that is a key for this {@link Throw}
     */
    public Pair<Integer, Integer> getKey() {
        return Pair.of(diceOne, diceTwo);
    }

    public int getSum() {
        return diceOne + diceTwo;
    }
}
