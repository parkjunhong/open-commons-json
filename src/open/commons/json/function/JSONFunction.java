/*
 * Copyright 2011 Park Jun-Hong_(fafanmama_at_naver_com)
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

/*
 *
 * This file is generated under this project, "open-commons-json".
 *
 * Date  : 2017. 10. 20. 오후 5:28:12
 *
 * Author: Park_Jun_Hong_(fafanmama_at_naver_com)
 * 
 */

package open.commons.json.function;

import java.util.Objects;

import code.org.codehaus.jettison.json.JSONException;

/**
 * Represents a function that accepts one argument and produces a result.
 *
 * <p>
 * This is a <a href="package-summary.html">functional interface</a> whose functional method is {@link #apply(Object)}.
 *
 * @param <T>
 *            the type of the input to the function
 * @param <R>
 *            the type of the result of the function
 *
 * @since 1.8
 */
@FunctionalInterface
public interface JSONFunction<T, R> {

    /**
     * Applies this function to the given argument.
     *
     * @param t
     *            the function argument
     * @return the function result
     */
    R apply(T t) throws JSONException;

    /**
     * Returns a composed function that first applies the {@code before} function to its input, and then applies this
     * function to the result. If evaluation of either function throws an exception, it is relayed to the caller of the
     * composed function.
     *
     * @param <V>
     *            the type of input to the {@code before} function, and to the composed function
     * @param before
     *            the function to apply before this function is applied
     * @return a composed function that first applies the {@code before} function and then applies this function
     * @throws NullPointerException
     *             if before is null
     *
     * @see #andThen(JSONFunction)
     */
    default <V> JSONFunction<V, R> compose(JSONFunction<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return (V v) -> apply(before.apply(v));
    }

    /**
     * Returns a composed function that first applies this function to its input, and then applies the {@code after}
     * function to the result. If evaluation of either function throws an exception, it is relayed to the caller of the
     * composed function.
     *
     * @param <V>
     *            the type of output of the {@code after} function, and of the composed function
     * @param after
     *            the function to apply after this function is applied
     * @return a composed function that first applies this function and then applies the {@code after} function
     * @throws NullPointerException
     *             if after is null
     *
     * @see #compose(JSONFunction)
     */
    default <V> JSONFunction<T, V> andThen(JSONFunction<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.apply(apply(t));
    }

    /**
     * Returns a function that always returns its input argument.
     *
     * @param <T>
     *            the type of the input and output objects to the function
     * @return a function that always returns its input argument
     */
    static <T> JSONFunction<T, T> identity() {
        return t -> t;
    }
}
