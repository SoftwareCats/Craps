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

public class DiceFrequency {

    public static void main(String[] args) {
        Multiset<Integer> frequencies = HashMultiset.create();

        for (int dice1 = 1; dice1 <= 6; dice1++) {
            for (int dice2 = 1; dice2 <= 6; dice2++) {
                int n = dice1 + dice2;
                frequencies.add(n);
            }
        }

        System.out.println(frequencies);
    }
}
