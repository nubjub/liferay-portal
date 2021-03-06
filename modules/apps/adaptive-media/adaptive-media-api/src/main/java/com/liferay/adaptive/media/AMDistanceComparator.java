/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.adaptive.media;

import java.util.Comparator;

/**
 * Compares two values, returning a long value representing the distance between
 * them. The meaning of this distance is dependent on the kind of value.
 *
 * @author Adolfo Pérez
 * @review
 */
@FunctionalInterface
public interface AMDistanceComparator<T> {

	/**
	 * Compare the two given values, returning a long value representing how far
	 * they are from each other. The meaning of this distance is dependent on
	 * the attribute kind.
	 *
	 * @param  value1 the first value
	 * @param  value2 the second value
	 * @return The distance between the two values
	 * @review
	 */
	public long compare(T value1, T value2);

	/**
	 * Return a comparator that is equivalent to this AMDistanceComparator.
	 * Implementations of this interface must use saturated arithmetic,
	 * guaranteeing the following condition:
	 *
	 * <code>
	 *     if amDistanceComparator.compare(a, b> < 0 then
	 *         amDistanceComparator.toComparator().compare(a, b) < 0
	 *
	 *     if amDistanceComparator.compare(a, b) > 0 then
	 *         amDistanceComparator.toComparator().compare(a, b) > 0
	 *
	 *     if amDistanceComparator.compare(a, b) = 0 then
	 *         amDistanceComparator.toComparator().compare(a, b) = 0
	 * </code>
	 *
	 * @return A {@link Comparator} equivalent to this AMDistanceComparator
	 * @review
	 */
	public default Comparator<T> toComparator() {
		return (value1, value2) -> (int)Math.max(
			Integer.MIN_VALUE,
			Math.min(Integer.MAX_VALUE, compare(value1, value2)));
	}

}