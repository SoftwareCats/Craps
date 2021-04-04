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


import org.apache.commons.lang3.tuple.Pair;

import java.util.*;

/**
 * {@link Dice} contains the 36 individual throws of two dice, plus a random number generator. It can select a {@link Throw}
 * at random, simulating a throw of the Craps dice.
 */
public class Dice {

    /**
     * Generates the next random number, used to select a {@link Throw} from the {@link Dice#THROWS} collection.
     */
    protected final Random RNG;
    /**
     * This is a {@link Map} that associates a {@link Pair} with a {@link Throw}.
     */
    protected final Map<Pair<Integer, Integer>, Throw> THROWS;

    /**
     * Creates a new random number generator instance, and calls the other constructor.
     */
    public Dice() {
        this(new Random());
    }

    /**
     * Build the mapping of Throw instances.
     *
     * @param rng the random number generator to use
     */
    public Dice(Random rng) {
        RNG = rng;
        THROWS = new HashMap<>();
    }

    /**
     * While not needed by the application, unit tests may need a method to return a specific {@link Throw}
     * rather than a randomly selected {@link Throw}.
     * <p>
     * This method takes a particular combination of dice, locates (or creates) a {@link Pair}, and
     * returns the appropriate {@link Throw}.
     *
     * @param diceOne the value of one die
     * @param diceTwo the value of the other die
     * @return the {@link Throw}
     */
    public Throw getThrow(int diceOne, int diceTwo) {
        return THROWS.get(Pair.of(diceOne, diceTwo));
    }

    /**
     * While not needed by the application, unit tests may need a method to return a specific {@link Throw}
     * rather than a randomly selected {@link Throw}.
     * <p>
     * This method takes a particular combination of dice, represented by a {@link Pair}, and
     * returns the appropriate {@link Throw}.
     *
     * @param key the key
     * @return the throw
     */
    public Throw getThrow(Pair<Integer, Integer> key) {
        return THROWS.get(key);
    }

    /**
     * Adds the given {@link Throw} to the mapping maintained by this instance of {@link Dice}. The key for this
     * {@link Throw} is available from the {@link Throw#getKey()} method.
     *
     * @param diceThrow the {@link Throw} to add
     */
    public void addThrow(Throw diceThrow) {
        THROWS.put(diceThrow.getKey(), diceThrow);
    }

    /**
     * Returns a randomly selected {@link Throw}.
     *
     * @return the randomly selected {@link Throw}
     */
    public Throw next() {
        List<Pair<Integer, Integer>> keys = new ArrayList<>(THROWS.keySet());
        int index = RNG.nextInt(keys.size());
        return THROWS.get(keys.get(index));
    }
}
