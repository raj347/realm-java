/*
 * Copyright 2016 Realm Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.realm;

import java.util.Collection;
import java.util.Date;

import io.realm.RealmObject;
import io.realm.RealmQuery;

/**
 * {@code RealmCollection} is the root of the collection hierarchy that Realm supports. It defines operations on data
 * collections and the behavior that they will have in all implementations of {@code RealmCollection}s.
 *
 * @param <E> type of {@link RealmObject} stored in the collection.
 */
public interface RealmCollection<E extends RealmObject> extends Collection<E> {

    /**
     * Returns a {@link RealmQuery}, which can be used to query for specific objects from this collection.
     *
     * @return a RealmQuery object.
     * @throws IllegalStateException if the Realm instance has been closed or queries are not otherwise available.
     * @see io.realm.RealmQuery
     */
    RealmQuery<E> where();

    /**
     * Finds the minimum value of a field.
     *
     * @param fieldName the field to look for a minimum on. Only number fields are supported.
     * @return if no objects exist or they all have {@code null} as the value for the given field, {@code null} will be
     * returned. Otherwise the minimum value is returned. When determining the minimum value, objects with {@code null}
     * values are ignored.
     * @throws java.lang.IllegalArgumentException if the field is not a number type.
     * @throws java.lang.IllegalStateException if the Realm has been closed or called from an incorrect thread.
     */
    Number min(String fieldName);

    /**
     * Finds the maximum value of a field.
     *
     * @param fieldName the field to look for a maximum on. Only number fields are supported.
     * @return if no objects exist or they all have {@code null} as the value for the given field, {@code null} will be
     * returned. Otherwise the maximum value is returned. When determining the maximum value, objects with {@code null}
     * values are ignored.
     * @throws java.lang.IllegalArgumentException if the field is not a number type.
     * @throws java.lang.IllegalStateException if the Realm has been closed or called from an incorrect thread.
     */
    Number max(String fieldName);

    /**
     * Calculates the sum of a given field.
     *
     * @param fieldName the field to sum. Only number fields are supported.
     * @return the sum. If no objects exist or they all have {@code null} as the value for the given field, {@code 0}
     * will be returned. When computing the sum, objects with {@code null} values are ignored.
     * @throws java.lang.IllegalArgumentException if the field is not a number type.
     * @throws java.lang.IllegalStateException if the Realm has been closed or called from an incorrect thread.
     */
    Number sum(String fieldName);

    /**
     * Returns the average of a given field.
     *
     * @param fieldName the field to calculate average on. Only number fields are supported.
     * @return the average for the given field amongst objects in query results. This will be of type double for all
     * types of number fields. If no objects exist or they all have {@code null} as the value for the given field,
     * {@code 0} will be returned. When computing the average, objects with {@code null} values are ignored.
     * @throws java.lang.IllegalArgumentException if the field is not a number type.
     * @throws java.lang.IllegalStateException if the Realm has been closed or called from an incorrect thread.
     */
    double average(String fieldName);

    /**
     * Finds the maximum date.
     *
     * @param fieldName the field to look for the maximum date. If fieldName is not of Date type, an exception is
     *                  thrown.
     * @return if no objects exist or they all have {@code null} as the value for the given date field, {@code null}
     * will be returned. Otherwise the maximum date is returned. When determining the maximum date, objects with
     * {@code null} values are ignored.
     * @throws java.lang.IllegalArgumentException if fieldName is not a Date field.
     * @throws java.lang.IllegalStateException if the Realm has been closed or called from an incorrect thread.
     */
    Date maxDate(String fieldName);

    /**
     * Finds the minimum date.
     *
     * @param fieldName the field to look for the minimum date. If fieldName is not of Date type, an exception is
     *                  thrown.
     * @return if no objects exist or they all have {@code null} as the value for the given date field, {@code null}
     * will be returned. Otherwise the minimum date is returned. When determining the minimum date, objects with
     * {@code null} values are ignored.
     * @throws java.lang.IllegalArgumentException if fieldName is not a Date field.
     * @throws java.lang.IllegalStateException if the Realm has been closed or called from an incorrect thread.
     */
    Date minDate(String fieldName);

    /**
     * This deletes all objects in the collection from the underlying Realm as well as from the collection.
     *
     * @throws IllegalStateException if the corresponding Realm is closed or in an incorrect thread.
     * @return {@code true} if objects was deleted, {@code false} otherwise.
     * @throws java.lang.IllegalStateException if the Realm has been closed or called from an incorrect thread.
     */
    boolean deleteAllFromRealm();

    /**
     * Checks if a collection has finished loading its data yet.
     *
     * @return {@code true} if data has loaded and is available {@code false} if they are still loading.
     */
    boolean isLoaded();

    /**
     * Blocks the collection until all data are available.
     *
     * @return {@code true} if the data could be successfully loaded, {@code false} otherwise.
     */
    boolean load();

    /**
     * Checks if the collection is still valid to use e.g. the {@link io.realm.Realm} instance hasn't
     * been closed.
     *
     * @return {@code true} if still valid to use, {@code false} otherwise.
     */
    boolean isValid();

    /**
     * Returns the Realm instance backing this collection.
     *
     * @return the {@link BaseRealm} backing this collection or {@code null} if it is an un-managed collection.
     */
    BaseRealm getRealm();

}