/*
 * Copyright (C) 2017 Lukoh Nam, goForer
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.goforer.goforerarchblueprint.domain.logic.sort.comparator;

import com.goforer.goforerarchblueprint.repository.model.data.entity.Repo;

import java.util.Comparator;

public class RepoNameComparator implements Comparator<Repo> {
    public RepoNameComparator() {
    }

    @Override
    public int compare(Repo repo1, Repo repo2) {
        String name1 = repo1.getName().toUpperCase();
        String name2 = repo2.getName().toUpperCase();

        //ascending order
        return name1.compareTo(name2);

    }
}
