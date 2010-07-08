package gg.proto;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;


/**
 * User: zac
 * Date: 2010-jul-08
 * Time: 12:25:25
 * <p/>
 * Copyright © 2010 Martin Zachrison
 * <p/>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
public class EmailWrapperTest {

    private EmailWrapper dut;

    @BeforeTest
    public void beforeTest() throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream("email.proto-bin");
        dut = new EmailWrapper(is);
    }

    @Test
    public void testSend() throws Exception {
    }

    @Test
    public void testGetTo() throws Exception {
        List<String> tos = dut.getTo();
        assertEquals("Wrong number of to:s", 2, tos.size());
        assertEquals("Wrong first to", "martin.zachrison@gmail.com", tos.get(0));
        assertEquals("Wrong second to", "martin.zachrison@mobileme.com", tos.get(1));
    }

    @Test
    public void testGetFrom() throws Exception {
        assertEquals("zac@cyberzac.se", dut.getFrom());
    }

    @Test
    public void testGetBody() throws Exception {
        assertEquals("World", dut.getBody());
    }

    @Test
    public void testGetSubject() throws Exception {
        assertEquals("Hello", dut.getSubject());
    }
}
