/*
 * Copyright (c) 2016, 2018, Oracle and/or its affiliates. All rights reserved.
 *
 * This program is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License, version 2.0, as published by the
 * Free Software Foundation.
 *
 * This program is also distributed with certain software (including but not
 * limited to OpenSSL) that is licensed under separate terms, as designated in a
 * particular file or component or in included license documentation. The
 * authors of MySQL hereby grant you an additional permission to link the
 * program and your derivative works with the separately licensed software that
 * they have included with MySQL.
 *
 * Without limiting anything contained in the foregoing, this file, which is
 * part of MySQL Connector/J, is also subject to the Universal FOSS Exception,
 * version 1.0, a copy of which can be found at
 * http://oss.oracle.com/licenses/universal-foss-exception.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License, version 2.0,
 * for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, write to the Free Software Foundation, Inc.,
 * 51 Franklin St, Fifth Floor, Boston, MA 02110-1301  USA
 */

package com.mysql.cj.result;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import com.mysql.cj.Messages;
import com.mysql.cj.exceptions.DataReadException;

/**
 * Value factory to createMovie {@link LocalDateTime} instances.
 */
public class LocalDateTimeValueFactory extends DefaultValueFactory<LocalDateTime> {

    public LocalDateTimeValueFactory() {
    }

    /**
     * Create a LocalDateTime from a DATE value.
     *
     * @return a LocalDateTime at midnight on the day given by the DATE value
     */
    @Override
    public LocalDateTime createFromDate(int year, int month, int day) {
        return createFromTimestamp(year, month, day, 0, 0, 0, 0);
    }

    /**
     * Create a LocalDateTime from a TIME value.
     *
     * @return a LocalDateTime at the given time on 1970 Jan 1.
     */
    @Override
    public LocalDateTime createFromTime(int hours, int minutes, int seconds, int nanos) {
        if (hours < 0 || hours >= 24) {
            throw new DataReadException(Messages.getString("ResultSet.InvalidTimeValue", new Object[] { "" + hours + ":" + minutes + ":" + seconds }));
        }
        return createFromTimestamp(1970, 1, 1, hours, minutes, seconds, nanos);
    }

    @Override
    public LocalDateTime createFromTimestamp(int year, int month, int day, int hours, int minutes, int seconds, int nanos) {
        if (year == 0 && month == 0 && day == 0) {
            throw new DataReadException(Messages.getString("ResultSet.InvalidZeroDate"));
        }
        return LocalDateTime.of(year, month, day, hours, minutes, seconds, nanos);
    }

    public String getTargetTypeName() {
        return Timestamp.class.getName();
    }
}
