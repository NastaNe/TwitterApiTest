import org.junit.Before;
import org.junit.*;
import org.junit.runners.MethodSorters;
import twitter4j.Status;

import java.util.List;

import static org.junit.Assert.*;
import org.junit.rules.ExpectedException;
import twitter4j.TwitterException;

/**
 * created by nastane on 7/31/17.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApiTests {

    private TwitterApi twitterApi;
    private static String testStatus = "Test Update Status" + Math.random();

    @Rule
    public ExpectedException exception = ExpectedException.none();


    @Before
    public void initialize() {
        twitterApi = new TwitterApi();
    }

    @Test
    public void verifyHomeTimelineCreatedAt() {
        List<Status> homeTimeline = twitterApi.getHomeTimeLine();
        for (Status status : homeTimeline) {
            assertNotNull(status.getCreatedAt());
        }
    }

    @Test
    public void verifyHomeTimelineRetweetCount() {
        List<Status> homeTimeline = twitterApi.getHomeTimeLine();
        for (Status status : homeTimeline) {
            assertNotNull(status.getRetweetCount());
        }
    }

    @Test
    public void verifyHomeTimelineText() {
        List<Status> homeTimeline = twitterApi.getHomeTimeLine();
        for (Status status : homeTimeline) {
            assertNotNull(status.getText());
        }
    }

    @Test
    public void updateStatus() throws TwitterException{
        Status status = twitterApi.updateStatus(testStatus);
        assertEquals("Status is updated", twitterApi.getLastStatusForUser().getText(), status.getText());
    }

    @Test
    public void updateThrowsExceptionIfTryingToUpdateToTheSameStatus() throws TwitterException {
        exception.expect(TwitterException.class);
        exception.expectMessage("Status is a duplicate");
        twitterApi.updateStatus(testStatus);
    }

    @Test
    public void destroyStatus() {
        int countOfStatuses = twitterApi.getNumberOfStatusesForUser();
        Status homeTimeline = twitterApi.destroyStatus(twitterApi.getLastStatusForUser().getId());
        assertNotEquals(homeTimeline.getId(), twitterApi.getLastStatusForUser().getId());
        assertNotEquals(twitterApi.getNumberOfStatusesForUser(), countOfStatuses);
    }
}
