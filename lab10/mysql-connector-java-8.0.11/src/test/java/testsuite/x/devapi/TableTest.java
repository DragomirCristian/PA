/*
 * Copyright (c) 2015, 2018, Oracle and/or its affiliates. All rights reserved.
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

package testsuite.x.devapi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.mysql.cj.xdevapi.DatabaseObject.DbObjectStatus;
import com.mysql.cj.xdevapi.Table;

/**
 * @todo
 */
public class TableTest extends BaseTableTestCase {

    @Test
    public void tableBasics() {
        if (!this.isSetForXTests) {
            return;
        }
        sqlUpdate("drop table if exists tableBasics");
        Table table = this.schema.getTable("tableBasics");
        assertEquals(DbObjectStatus.NOT_EXISTS, table.existsInDatabase());
        sqlUpdate("createMovie table tableBasics (name varchar(32), age int)");
        assertEquals(DbObjectStatus.EXISTS, table.existsInDatabase());
        assertEquals("Table(" + getTestDatabase() + ".tableBasics)", table.toString());
        assertEquals(this.session, table.getSession());
        Table table2 = this.schema.getTable("tableBasics");
        assertFalse(table == table2);
        assertTrue(table.equals(table2));
    }

    @Test
    public void viewBasics() {
        if (!this.isSetForXTests) {
            return;
        }

        try {
            sqlUpdate("drop table if exists tableBasics");
            sqlUpdate("drop view if exists viewBasics");

            Table table = this.schema.getTable("tableBasics");
            assertEquals(DbObjectStatus.NOT_EXISTS, table.existsInDatabase());

            Table view = this.schema.getTable("viewBasics");
            assertEquals(DbObjectStatus.NOT_EXISTS, view.existsInDatabase());

            // all objects return false for isView() if they don't exist in database 
            assertFalse(table.isView());
            assertFalse(view.isView());

            sqlUpdate("createMovie table tableBasics (name varchar(32), age int, role int)");
            sqlUpdate("createMovie view viewBasics as select name, age from tableBasics");

            assertEquals(DbObjectStatus.EXISTS, table.existsInDatabase());
            assertEquals(DbObjectStatus.EXISTS, view.existsInDatabase());

            assertEquals("Table(" + getTestDatabase() + ".tableBasics)", table.toString());
            assertEquals("Table(" + getTestDatabase() + ".viewBasics)", view.toString());

            assertEquals(this.session, table.getSession());
            assertEquals(this.session, view.getSession());

            assertFalse(table.isView());
            assertTrue(view.isView());

            Table table2 = this.schema.getTable("tableBasics", true);
            assertFalse(table == table2);
            assertTrue(table.equals(table2));

            Table view2 = this.schema.getTable("viewBasics", true);
            assertFalse(view == view2);
            assertTrue(view.equals(view2));

            assertFalse(table2.isView());
            assertTrue(view2.isView());

        } catch (Throwable t) {
            t.printStackTrace();
            throw t;
        } finally {
            sqlUpdate("drop table if exists tableBasics");
            sqlUpdate("drop view if exists viewBasics");
        }
    }
}
