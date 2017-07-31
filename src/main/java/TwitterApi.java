import twitter4j.*;


import java.util.List;

/**
 * created by nastane on 7/30/17.
 */
public class TwitterApi {


    public List<Status> getHomeTimeLine() {
        try {
            Twitter twitter = new TwitterFactory().getInstance();
            User user = twitter.verifyCredentials();
            List<Status> statuses = twitter.getHomeTimeline();
            System.out.println("Showing @" + user.getScreenName() + "'s home timeline.");
            for (Status status : statuses) {
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
            }
            return statuses;
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get timeline: " + te.getMessage());
            System.exit(-1);
        }
        return null;
    }

    public Status updateStatus(String status) throws TwitterException{
            Twitter twitter = new TwitterFactory().getInstance();
            User user = twitter.verifyCredentials();
            return twitter.updateStatus(status);
    }

    public Status destroyStatus(long statusId) {
        try {
            Twitter twitter = new TwitterFactory().getInstance();
            User user = twitter.verifyCredentials();
            return twitter.destroyStatus(statusId);

        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to Destroy Status: " + te.getMessage());
            System.exit(-1);
        }
        return null;
    }

    public Status getLastStatusForUser() {
        try {
            Twitter twitter = new TwitterFactory().getInstance();
            User user = twitter.verifyCredentials();
            return user.getStatus();
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get Last Status For User: " + te.getMessage());
            System.exit(-1);
        }
        return null;
    }

    public int getNumberOfStatusesForUser() {
        try {
            Twitter twitter = new TwitterFactory().getInstance();
            User user = twitter.verifyCredentials();
            return user.getStatusesCount();
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get Status Count: " + te.getMessage());
            System.exit(-1);
        }
        return 0;
    }

}
