package com.clueteleporthelper;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SkillChallengeDataTest {

    @Test
    public void sherlockChallengeMatchesAndAddsReturnTrip() {
        SkillChallengeData.SkillChallenge sc = SkillChallengeData.getChallenge("Cut a yew log.");
        assertNotNull(sc);
        assertFalse(sc.charlie);

        List<TeleportData> before = sc.teleports(false);
        assertEquals(TeleportData.WOODCUTTING_GUILD, before.get(0));
        assertTrue(before.containsAll(SkillChallengeData.SHERLOCK_TELEPORTS));
        assertTrue(sc.note(false).endsWith("Then return to Sherlock"));

        assertEquals(SkillChallengeData.SHERLOCK_TELEPORTS, sc.teleports(true));
        assertTrue(sc.note(true).contains("Return to Sherlock"));
    }

    @Test
    public void matchingIgnoresCaseAndTriesEveryText() {
        assertNotNull(SkillChallengeData.getChallenge("", null, "cut a yew log."));
        assertNull(SkillChallengeData.getChallenge("Cut a magic log."));
        assertNull(SkillChallengeData.getChallenge((String) null));
    }

    @Test
    public void charlieUsesTheChatText() {
        // RuneLite leaves the scroll text empty for Charlie and keeps the request in the chat text.
        SkillChallengeData.SkillChallenge sc = SkillChallengeData.getChallenge(
                "", "I really need a cooked pike.", "i need to give charlie a cooked pike.");
        assertNotNull(sc);
        assertTrue(sc.charlie);
        assertEquals(SkillChallengeData.CHARLIE_TELEPORTS, sc.teleports(false));
        assertTrue(sc.note(false).contains("Charlie"));
    }

    @Test
    public void allChallengesAreFilledIn() {
        int sherlock = 0;
        int charlie = 0;
        for (SkillChallengeData.SkillChallenge sc : SkillChallengeData.all()) {
            if (sc.charlie) {
                charlie++;
            } else {
                sherlock++;
            }
            assertFalse(sc.note(false).contains("→"));
        }
        assertEquals(8, charlie);
        assertEquals(51, sherlock);
    }
}
