package net.ostis.confman.test.util;

import junit.framework.Assert;
import net.ostis.confman.model.datastore.util.IDGenerator;

import org.junit.Test;

public class IDGenerationTest {

    @Test
    public void testIDGeneration() {

        final IDGenerator gen = IDGenerator.getInstance();
        Assert.assertEquals(2, gen.nextId());
    }
}
